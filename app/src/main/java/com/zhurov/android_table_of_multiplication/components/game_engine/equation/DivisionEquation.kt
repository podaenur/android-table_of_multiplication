package com.zhurov.android_table_of_multiplication.components.game_engine.equation

import java.math.BigDecimal
import java.math.RoundingMode

class DivisionEquation(
    leftExpression: BigDecimal,
    rightExpression: BigDecimal,
) : Equation(
    leftExpression = leftExpression,
    rightExpression = rightExpression,
    result = leftExpression.divide(rightExpression, SCALE, RoundingMode.DOWN).stripTrailingZeros()
) {
    override val equationWithoutAnswer: String =
        "${leftExpression.toInt()} : ${rightExpression.toInt()}"
}