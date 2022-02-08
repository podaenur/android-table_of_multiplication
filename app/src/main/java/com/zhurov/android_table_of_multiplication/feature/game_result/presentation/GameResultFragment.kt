package com.zhurov.android_table_of_multiplication.feature.game_result.presentation

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.zhurov.android_table_of_multiplication.R
import com.zhurov.android_table_of_multiplication.components.extension.androidx.fragment.app.findViewById
import com.zhurov.android_table_of_multiplication.components.extension.androidx.fragment.app.getParcelable
import com.zhurov.android_table_of_multiplication.components.widget.cell.ResultGameCell
import com.zhurov.android_table_of_multiplication.components.widget.dialog.ConfirmActionDialogFragment
import com.zhurov.android_table_of_multiplication.di.gameResultViewModel
import com.zhurov.android_table_of_multiplication.feature.game_result.domain.arguments.GameResultArguments

class GameResultFragment : Fragment(R.layout.fragment_game_result) {
    private val screenArguments: GameResultArguments by getParcelable(ARGUMENTS_KEY)
    private val cancelButton: MaterialTextView by findViewById(R.id.cancel_button)
    private val finishGameButton: MaterialButton by findViewById(R.id.finish_game_button)
    private val correctAnswerCell: ResultGameCell by findViewById(R.id.correct_answer)
    private val mistakeAnswerCell: ResultGameCell by findViewById(R.id.mistake_answer)
    private val timeCell: ResultGameCell by findViewById(R.id.time)
    private val motivation: MaterialTextView by findViewById(R.id.motivation)
    private val viewModel: GameResultViewModel by gameResultViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupGameResults()
    }

    private fun setupGameResults() = with(screenArguments.gameResult) {
        correctAnswerCell.value = correctAnswers.toString()
        mistakeAnswerCell.value = mistakes.toString()
        timeCell.value = viewModel.getFormattedTime(time)
        motivation.text = getString(
            viewModel.getMotivationStringId(
                correctAnswers = correctAnswers,
                mistakeAnswers = mistakes
            )
        )
    }

    override fun onResume() {
        super.onResume()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        cancelButton.setOnClickListener {
            ConfirmActionDialogFragment.instance(
                title = R.string.end_game_question,
                message = R.string.end_game_message,
                positiveButtonText = R.string.exit_game,
                negativeButtonText = R.string.continue_game,
                positiveAction = viewModel::openLoginScreen
            ).show(childFragmentManager, CONFIRM_ACTION_DIALOG_TAG)
        }

        finishGameButton.setOnClickListener {
            viewModel.openLoginScreen()
        }
    }

    companion object {
        private const val ARGUMENTS_KEY = "GameResultFragment arguments key"
        private const val CONFIRM_ACTION_DIALOG_TAG = "Result Game ConfirmActionDialogFragment tag"

        fun instance(
            gameResultArguments: GameResultArguments
        ) = GameResultFragment().apply {
            arguments = bundleOf(
                ARGUMENTS_KEY to gameResultArguments
            )
        }
    }
}