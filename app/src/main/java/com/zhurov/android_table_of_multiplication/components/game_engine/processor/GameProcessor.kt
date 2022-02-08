package com.zhurov.android_table_of_multiplication.components.game_engine.processor

import com.zhurov.android_table_of_multiplication.components.game_engine.GameType
import com.zhurov.android_table_of_multiplication.components.game_engine.equation.Equation

abstract class GameProcessor(gameType: GameType) {
    val countQuestion = when(gameType) {
        is GameType.SingleMultiplication,
        is GameType.SingleDivision,
        is GameType.FullMultiplication,
        is GameType.FullDivision -> 8
        is GameType.SingleMultiplicationDivision,
        is GameType.FullMultiplicationDivision -> 16
    }
    protected val expressions = 2L..9L

    protected abstract var queueEquation: MutableList<Equation>

    val nextEquation: Equation get() = queueEquation.removeFirst()
}