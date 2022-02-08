package com.zhurov.android_table_of_multiplication.feature.settings.domain.config

sealed class ExpressionConfig {
    data class Single(
        val expression: Int = 2
    ) : ExpressionConfig()

    object All : ExpressionConfig()
}