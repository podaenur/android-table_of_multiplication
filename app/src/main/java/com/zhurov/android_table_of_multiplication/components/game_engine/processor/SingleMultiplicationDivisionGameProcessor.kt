package com.zhurov.android_table_of_multiplication.components.game_engine.processor

import com.zhurov.android_table_of_multiplication.components.game_engine.GameType
import com.zhurov.android_table_of_multiplication.components.game_engine.equation.DivisionEquation
import com.zhurov.android_table_of_multiplication.components.game_engine.equation.Equation
import com.zhurov.android_table_of_multiplication.components.game_engine.equation.MultiplicationEquation
import java.math.BigDecimal

class SingleMultiplicationDivisionGameProcessor(
    private val gameType: GameType.SingleMultiplicationDivision
) : GameProcessor(gameType) {

    private var lastDivisionEquation = ""
    private var lastMultiplicationEquation = ""

    override var queueEquation: MutableList<Equation> = mutableListOf<Equation>().apply {
        var toggleEquationType = false
        for (i in countQuestion downTo 1) {
            if (toggleEquationType) add(getRandomDivisionEquation())
            else add(getRandomMultiplicationEquation())
            toggleEquationType = toggleEquationType.not()
        }
    }

    private tailrec fun getRandomMultiplicationEquation(): MultiplicationEquation {
        val equation = MultiplicationEquation(
            leftExpression = BigDecimal(gameType.expression),
            rightExpression = BigDecimal(expressions.random())
        )
        return if (equation.equationWithoutAnswer != lastMultiplicationEquation) {
            lastMultiplicationEquation = equation.equationWithoutAnswer
            equation
        } else getRandomMultiplicationEquation()
    }

    private tailrec fun getRandomDivisionEquation(): DivisionEquation {
        val equation = DivisionEquation(
            leftExpression = BigDecimal(expressions.random()),
            rightExpression = BigDecimal(gameType.expression)
        )
        return if (equation.equationWithoutAnswer != lastDivisionEquation) {
            lastDivisionEquation = equation.equationWithoutAnswer
            equation
        } else getRandomDivisionEquation()
    }
}