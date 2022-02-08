package com.zhurov.android_table_of_multiplication.feature.game.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zhurov.android_table_of_multiplication.components.game_engine.GameEngine
import com.zhurov.android_table_of_multiplication.components.game_engine.GameState
import com.zhurov.android_table_of_multiplication.components.navigation.Navigator
import com.zhurov.android_table_of_multiplication.feature.game.domain.arguments.GameArguments
import com.zhurov.android_table_of_multiplication.feature.game.domain.state.GameUiState
import com.zhurov.android_table_of_multiplication.feature.game.navigation.GameToGameResultCommand
import com.zhurov.android_table_of_multiplication.feature.game.navigation.GameToLoginCommand
import com.zhurov.android_table_of_multiplication.feature.game_result.domain.arguments.GameResultArguments

class GameViewModel(
    private val navigator: Navigator,
    gameArguments: GameArguments
) : ViewModel() {
    private val gameEngine = GameEngine(gameArguments.gameType)
    val maxEquation = gameEngine.countQuestion
    fun startGameEquation() = gameEngine.nextEquation.equationWithoutAnswer

    private val _uiState = MutableLiveData<GameUiState>(GameUiState.Empty)
    val uiState = _uiState as LiveData<GameUiState>

    fun nextEquation(answer: String?, progress: Int) {
        val formattedAnswer = filterAnswer(answer)
        gameEngine.checkAnswer(formattedAnswer)
        val gameState = gameEngine.nextEquationOrFinish(progress)
        _uiState.value = when (gameState) {
            is GameState.CalculateEquation -> GameUiState.MovingToNextEquation(
                equation = gameState.equation.equationWithoutAnswer
            )
            is GameState.Finish -> GameUiState.Finish
        }
    }

    private fun filterAnswer(answer: String?) = if (
        answer != null && answer.isNotEmpty() && answer.matches(Regex("-?\\d+(\\.\\d+)?"))
    ) answer
    else EMPTY_ANSWER

    fun getGameResult(time: Long) {
        val result = gameEngine.getGameResult(time)
        openGameResultScreen(
            GameResultArguments(result)
        )
    }

    fun openLoginScreen() = navigator.navigate(GameToLoginCommand())

    private fun openGameResultScreen(arguments: GameResultArguments) = navigator.navigate(
        GameToGameResultCommand(arguments)
    )

    companion object {
        private const val EMPTY_ANSWER = "-1"
    }
}