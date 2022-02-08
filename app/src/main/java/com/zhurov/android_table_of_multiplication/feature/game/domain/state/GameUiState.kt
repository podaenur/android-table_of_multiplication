package com.zhurov.android_table_of_multiplication.feature.game.domain.state

sealed class GameUiState {
    object Empty : GameUiState()

    data class MovingToNextEquation(
        val equation: String
    ) : GameUiState()

    object Finish : GameUiState()
}
