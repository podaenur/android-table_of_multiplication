package com.zhurov.android_table_of_multiplication.components.game_engine.motivation

import androidx.annotation.StringRes
import com.zhurov.android_table_of_multiplication.R

enum class Motivation(
    @StringRes
    val stringId: Int
) {
    BEST(
        stringId = R.string.best_motivation
    ),
    GOOD(
        stringId = R.string.good_motivation
    ),
    BAD(
        stringId = R.string.bad_motivation
    )

}