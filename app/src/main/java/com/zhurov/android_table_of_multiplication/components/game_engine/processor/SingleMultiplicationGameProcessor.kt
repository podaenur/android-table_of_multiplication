package com.zhurov.android_table_of_multiplication.components.game_engine.processor

import com.zhurov.android_table_of_multiplication.components.game_engine.GameType
import com.zhurov.android_table_of_multiplication.components.game_engine.equation.Equation
import com.zhurov.android_table_of_multiplication.components.game_engine.equation.MultiplicationEquation
import java.math.BigDecimal

class SingleMultiplicationGameProcessor(
    private val gameType: GameType.SingleMultiplication
) : GameProcessor(gameType) {

    private var lastEquation = ""

    override var queueEquation: MutableList<Equation> = mutableListOf<Equation>().apply {
        for (i in countQuestion downTo 1)
            add(getRandomMultiplicationEquation())
    }

    private tailrec fun getRandomMultiplicationEquation(): MultiplicationEquation {
        val equation = MultiplicationEquation(
            leftExpression = BigDecimal(gameType.leftExpression),
            rightExpression = BigDecimal(expressions.random())
        )
        return if (equation.equationWithoutAnswer != lastEquation) {
            lastEquation = equation.equationWithoutAnswer
            equation
        } else getRandomMultiplicationEquation()
    }
}