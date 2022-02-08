package com.zhurov.android_table_of_multiplication.feature.settings.presentation

import androidx.constraintlayout.helper.widget.Flow
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.zhurov.android_table_of_multiplication.R
import com.zhurov.android_table_of_multiplication.components.extension.androidx.fragment.app.findViewById
import com.zhurov.android_table_of_multiplication.components.widget.button.CheckableButton
import com.zhurov.android_table_of_multiplication.di.settingsViewModel
import com.zhurov.android_table_of_multiplication.feature.settings.domain.config.GameOperationConfig
import com.zhurov.android_table_of_multiplication.feature.settings.domain.config.ExpressionConfig
import com.zhurov.android_table_of_multiplication.feature.settings.domain.screen.SettingsScreenParameters

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private val viewModel by settingsViewModel
    private val multiplicationCheckbox: CheckableButton by findViewById(R.id.multiplication_checkbox)
    private val divisionCheckbox: CheckableButton by findViewById(R.id.division_checkbox)
    private val flowNumbers: Flow by findViewById(R.id.flow_numbers)
    private val expressionAll: MaterialButton by findViewById(R.id.expression_all)
    private val gameOperationConfig
        get() = GameOperationConfig.getConfigByBooleans(
            isMultiplication = multiplicationCheckbox.isChecked,
            isDivision = divisionCheckbox.isChecked
        )

    override fun onResume() {
        super.onResume()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        flowNumbers.referencedIds.forEachIndexed { index, viewId ->
            val expression = FIRST_LEFT_EXPRESSION + index
            val buttonExpression: MaterialButton by findViewById(viewId)
            buttonExpression.setOnClickListener {
                viewModel.startGameWithConfigs(
                    SettingsScreenParameters(
                        gameOperationConfig = gameOperationConfig,
                        expressionConfig = ExpressionConfig.Single(
                            expression = expression
                        )
                    )
                )
            }
        }

        expressionAll.setOnClickListener {
            viewModel.startGameWithConfigs(
                SettingsScreenParameters(
                    gameOperationConfig = gameOperationConfig,
                    expressionConfig = ExpressionConfig.All
                )
            )
        }
    }

    companion object {
        private const val FIRST_LEFT_EXPRESSION = 2

        fun instance() = SettingsFragment()
    }
}