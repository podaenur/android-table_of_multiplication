package com.zhurov.android_table_of_multiplication.components.game_engine.equation

import java.math.BigDecimal

class MultiplicationEquation(
    leftExpression: BigDecimal,
    rightExpression: BigDecimal
) : Equation(
    leftExpression = leftExpression,
    rightExpression = rightExpression,
    result = leftExpression.multiply(rightExpression).setScale(SCALE)
) {
    override val equationWithoutAnswer: String =
        "${leftExpression.toInt()} x ${rightExpression.toInt()}"
}