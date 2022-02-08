package com.zhurov.android_table_of_multiplication.components.game_engine

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class GameType: Parcelable {
    @Parcelize
    data class SingleMultiplication(val leftExpression: Int) : GameType()
    @Parcelize
    data class SingleDivision(val rightExpression: Int) : GameType()
    @Parcelize
    data class SingleMultiplicationDivision(val expression: Int) : GameType()
    @Parcelize
    object FullMultiplication : GameType()
    @Parcelize
    object FullDivision : GameType()
    @Parcelize
    object FullMultiplicationDivision : GameType()
}