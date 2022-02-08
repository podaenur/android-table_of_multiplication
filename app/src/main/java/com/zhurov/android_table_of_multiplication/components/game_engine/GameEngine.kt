package com.zhurov.android_table_of_multiplication.components.game_engine

import com.zhurov.android_table_of_multiplication.components.game_engine.equation.Equation
import com.zhurov.android_table_of_multiplication.components.game_engine.processor.FullDivisionGameProcessor
import com.zhurov.android_table_of_multiplication.components.game_engine.processor.FullMultiplicationDivisionGameProcessor
import com.zhurov.android_table_of_multiplication.components.game_engine.processor.FullMultiplicationGameProcessor
import com.zhurov.android_table_of_multiplication.components.game_engine.processor.GameProcessor
import com.zhurov.android_table_of_multiplication.components.game_engine.processor.SingleDivisionGameProcessor
import com.zhurov.android_table_of_multiplication.components.game_engine.processor.SingleMultiplicationDivisionGameProcessor
import com.zhurov.android_table_of_multiplication.components.game_engine.processor.SingleMultiplicationGameProcessor
import kotlin.properties.Delegates

class GameEngine(
    gameType: GameType
) {
    private var mistakes = 0
    private var correctAnswer = 0
    private val gameProcessor: GameProcessor = when (gameType) {
        is GameType.SingleMultiplication -> SingleMultiplicationGameProcessor(gameType)
        is GameType.SingleDivision -> SingleDivisionGameProcessor(gameType)
        is GameType.FullMultiplication -> FullMultiplicationGameProcessor(gameType)
        is GameType.FullDivision -> FullDivisionGameProcessor(gameType)
        is GameType.SingleMultiplicationDivision ->
            SingleMultiplicationDivisionGameProcessor(gameType)
        is GameType.FullMultiplicationDivision -> FullMultiplicationDivisionGameProcessor(gameType)
    }
    private var currentEquation: Equation by Delegates.notNull()
    val nextEquation: Equation
        get() {
            val equation = gameProcessor.nextEquation
            currentEquation = equation
            return currentEquation
        }
    val countQuestion get() = gameProcessor.countQuestion

    fun nextEquationOrFinish(progress: Int) =
        if (progress == gameProcessor.countQuestion) GameState.Finish
        else GameState.CalculateEquation(nextEquation)

    fun getGameResult(time: Long) = GameResult(
        correctAnswers = correctAnswer,
        mistakes = mistakes,
        time = time
    )

    fun checkAnswer(answer: String) =
        if (currentEquation.compareWithResult(answer))
            correctAnswer++
        else mistakes++
}