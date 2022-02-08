package com.zhurov.android_table_of_multiplication.feature.game_result.domain.arguments

import android.os.Parcelable
import com.zhurov.android_table_of_multiplication.components.game_engine.GameResult
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResultArguments(
    val gameResult: GameResult
) : Parcelable