package com.zhurov.android_table_of_multiplication.feature.settings.domain.config

enum class GameOperationConfig {
    MULTIPLICATION,
    DIVISION,
    MULTIPLICATION_AND_DIVISION;

    companion object {
        fun getConfigByBooleans(isMultiplication: Boolean, isDivision: Boolean) = when {
            isMultiplication and isDivision -> MULTIPLICATION_AND_DIVISION
            isDivision -> DIVISION
            else -> MULTIPLICATION
        }
    }
}