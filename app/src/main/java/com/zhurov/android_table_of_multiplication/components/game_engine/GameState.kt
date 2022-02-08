package com.zhurov.android_table_of_multiplication.components.game_engine

import com.zhurov.android_table_of_multiplication.components.game_engine.equation.Equation

sealed class GameState {
    data class CalculateEquation(
        val equation: Equation
    ) : GameState()

    object Finish : GameState()
}