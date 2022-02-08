package com.zhurov.android_table_of_multiplication.components.game_engine.equation

import java.math.BigDecimal

abstract class Equation(
    val leftExpression: BigDecimal,
    val rightExpression: BigDecimal,
    protected val result: BigDecimal
) {
    abstract val equationWithoutAnswer: String

    fun compareWithResult(number: String): Boolean = BigDecimal(number).setScale(SCALE)
        .compareTo(result) == SUCCESS_COMPARE_RESULT

    companion object {
        const val SCALE = 1
        const val SUCCESS_COMPARE_RESULT = 0
    }
}