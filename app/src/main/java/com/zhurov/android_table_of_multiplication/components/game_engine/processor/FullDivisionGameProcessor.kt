package com.zhurov.android_table_of_multiplication.components.game_engine.processor

import com.zhurov.android_table_of_multiplication.components.game_engine.GameType
import com.zhurov.android_table_of_multiplication.components.game_engine.equation.DivisionEquation
import com.zhurov.android_table_of_multiplication.components.game_engine.equation.Equation
import java.math.BigDecimal

class FullDivisionGameProcessor(
    gameType: GameType.FullDivision
) : GameProcessor(gameType) {
    private var lastEquation = ""

    override var queueEquation: MutableList<Equation> = mutableListOf<Equation>().apply {
        for (i in countQuestion downTo 1)
            add(getRandomDivisionEquation())
    }

    private tailrec fun getRandomDivisionEquation(): DivisionEquation {
        val equation = DivisionEquation(
            leftExpression = BigDecimal(expressions.random()),
            rightExpression = BigDecimal(expressions.random())
        )
        return if (equation.equationWithoutAnswer != lastEquation) {
            lastEquation = equation.equationWithoutAnswer
            equation
        } else getRandomDivisionEquation()
    }
}