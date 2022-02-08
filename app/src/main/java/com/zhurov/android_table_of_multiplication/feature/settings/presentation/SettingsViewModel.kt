package com.zhurov.android_table_of_multiplication.feature.settings.presentation

import androidx.lifecycle.ViewModel
import com.zhurov.android_table_of_multiplication.components.game_engine.GameType
import com.zhurov.android_table_of_multiplication.components.navigation.Navigator
import com.zhurov.android_table_of_multiplication.feature.game.domain.arguments.GameArguments
import com.zhurov.android_table_of_multiplication.feature.settings.domain.config.GameOperationConfig
import com.zhurov.android_table_of_multiplication.feature.settings.domain.config.ExpressionConfig
import com.zhurov.android_table_of_multiplication.feature.settings.domain.screen.SettingsScreenParameters
import com.zhurov.android_table_of_multiplication.feature.settings.navigation.SettingsToGameCommand

class SettingsViewModel(
    private val navigator: Navigator
) : ViewModel() {
    private fun openGameScreen(gameType: GameType) = navigator.navigate(
        SettingsToGameCommand(
            gameArguments = GameArguments(gameType)
        )
    )

    private fun getGameType(settingsScreenParameters: SettingsScreenParameters): GameType =
        with(settingsScreenParameters) {
            when (settingsScreenParameters.gameOperationConfig) {
                GameOperationConfig.MULTIPLICATION ->
                    if (expressionConfig is ExpressionConfig.Single)
                        GameType.SingleMultiplication(
                            leftExpression = expressionConfig.expression
                        )
                    else GameType.FullMultiplication

                GameOperationConfig.DIVISION ->
                    if (expressionConfig is ExpressionConfig.Single)
                        GameType.SingleDivision(
                            rightExpression = expressionConfig.expression
                        )
                    else GameType.FullDivision

                GameOperationConfig.MULTIPLICATION_AND_DIVISION ->
                    if (expressionConfig is ExpressionConfig.Single)
                        GameType.SingleMultiplicationDivision(
                            expression = expressionConfig.expression
                        )
                    else GameType.FullMultiplicationDivision
            }

        }

    fun startGameWithConfigs(
        settingsScreenParameters: SettingsScreenParameters
    ) = openGameScreen(
        gameType = getGameType(settingsScreenParameters)
    )
}