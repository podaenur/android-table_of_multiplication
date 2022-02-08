package com.zhurov.android_table_of_multiplication.feature.game.domain.arguments

import android.os.Parcelable
import com.zhurov.android_table_of_multiplication.components.game_engine.GameType
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameArguments(
    val gameType: GameType
) : Parcelable