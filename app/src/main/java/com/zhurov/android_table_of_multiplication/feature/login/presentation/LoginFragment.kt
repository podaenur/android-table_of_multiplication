package com.zhurov.android_table_of_multiplication.feature.login.presentation

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.zhurov.android_table_of_multiplication.R
import com.zhurov.android_table_of_multiplication.components.extension.android.view.hideKeyboard
import com.zhurov.android_table_of_multiplication.components.extension.android.view.openKeyboard
import com.zhurov.android_table_of_multiplication.components.extension.androidx.fragment.app.findViewById
import com.zhurov.android_table_of_multiplication.di.loginViewModel

class LoginFragment : Fragment(R.layout.fragment_login) {
    private val container: ConstraintLayout by findViewById(R.id.login_container)
    private val textInput: TextInputEditText by findViewById(R.id.text_input)
    private val startButton: MaterialButton by findViewById(R.id.start_button)
    private val viewModel by loginViewModel

    override fun onResume() {
        super.onResume()
        textInput.openKeyboard()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        container.setOnClickListener {
            it.hideKeyboard()
        }

        startButton.setOnClickListener {
            container.hideKeyboard()
            viewModel.openSettingsScreen()
        }
    }

    override fun onStop() {
        container.hideKeyboard()
        super.onStop()
    }

    companion object {
        fun instance() = LoginFragment()
    }
}