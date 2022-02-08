package com.zhurov.android_table_of_multiplication.components.game_engine

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class GameResult(
    val correctAnswers: Int,
    val mistakes: Int,
    val time: Long
) : Parcelable