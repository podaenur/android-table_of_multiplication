package com.zhurov.android_table_of_multiplication.feature.game.presentation

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.zhurov.android_table_of_multiplication.R
import com.zhurov.android_table_of_multiplication.components.extension.android.view.hideKeyboard
import com.zhurov.android_table_of_multiplication.components.extension.android.view.openKeyboard
import com.zhurov.android_table_of_multiplication.components.extension.androidx.fragment.app.findViewById
import com.zhurov.android_table_of_multiplication.components.extension.androidx.fragment.app.getParcelable
import com.zhurov.android_table_of_multiplication.components.widget.dialog.ConfirmActionDialogFragment
import com.zhurov.android_table_of_multiplication.di.gameViewModel
import com.zhurov.android_table_of_multiplication.feature.game.domain.arguments.GameArguments
import com.zhurov.android_table_of_multiplication.feature.game.domain.state.GameUiState
import kotlin.properties.Delegates

class GameFragment : Fragment(R.layout.fragment_game) {
    private val screenArguments: GameArguments by getParcelable(ARGUMENTS_KEY)
    private val stopwatch: Chronometer by lazy {
        requireNotNull(
            view?.findViewById(R.id.stopwatch_game)
        )
    }
    private val container: ConstraintLayout by findViewById(R.id.game_container)
    private val cancelButton: MaterialTextView by findViewById(R.id.cancel_button)
    private val progressIndicator: LinearProgressIndicator by findViewById(R.id.progress_indicator)
    private val equation: MaterialTextView by findViewById(R.id.equation)
    private val answerButton: MaterialButton by findViewById(R.id.answer_button)
    private val textInput: TextInputEditText by findViewById(R.id.text_input)
    private var viewModel: GameViewModel by Delegates.notNull()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = gameViewModel(screenArguments)
    }

    override fun onResume() {
        super.onResume()
        textInput.openKeyboard()
        setOnClickListeners()
        setupUiState()
        setStopwatch()
    }

    private fun setOnClickListeners() {
        container.setOnClickListener {
            it.hideKeyboard()
        }
        cancelButton.setOnClickListener {
            ConfirmActionDialogFragment.instance(
                title = R.string.end_game_question,
                message = R.string.end_game_message,
                positiveButtonText = R.string.exit_game,
                negativeButtonText = R.string.continue_game,
                positiveAction = viewModel::openLoginScreen
            ).show(childFragmentManager, CONFIRM_ACTION_DIALOG_TAG)
        }

        answerButton.setOnClickListener {
            progressIndicator.progress = progressIndicator.progress.inc()
            val editableText = textInput.text?.toString()
           //  textInput.text?.ifEmpty { EMPTY_ANSWER }.toString().toInt()

            viewModel.nextEquation(
                answer = editableText,
                progress = progressIndicator.progress
            )
        }
    }

    private fun setupUiState() = viewModel.uiState.observe(this) { state ->
        when (state) {
            GameUiState.Empty -> {
                progressIndicator.max = viewModel.maxEquation
                equation.text = viewModel.startGameEquation()
            }
            is GameUiState.MovingToNextEquation -> {
                equation.text = state.equation
                textInput.text?.clear()
            }
            GameUiState.Finish -> {
                stopwatch.stop()
                container.hideKeyboard()
                val elapsedMilliseconds: Long = SystemClock.elapsedRealtime() - stopwatch.base
                viewModel.getGameResult(elapsedMilliseconds)

            }
        }
    }

    private fun setStopwatch() = with(stopwatch) {
        base = SystemClock.elapsedRealtime()
        start()
    }

    companion object {
        private const val ARGUMENTS_KEY = "Game fragment arguments key"
        private const val CONFIRM_ACTION_DIALOG_TAG = "Game ConfirmActionDialogFragment tag"

        fun instance(gameArguments: GameArguments) = GameFragment().apply {
            arguments = bundleOf(
                ARGUMENTS_KEY to gameArguments
            )
        }
    }
}