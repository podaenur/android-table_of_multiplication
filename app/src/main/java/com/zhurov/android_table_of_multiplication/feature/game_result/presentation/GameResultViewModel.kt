package com.zhurov.android_table_of_multiplication.feature.game_result.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import com.zhurov.android_table_of_multiplication.components.game_engine.motivation.Motivation
import com.zhurov.android_table_of_multiplication.components.navigation.Navigator
import com.zhurov.android_table_of_multiplication.feature.game_result.navigation.GameResultToLoginCommand
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
class GameResultViewModel(
    private val navigator: Navigator
) : ViewModel() {

    fun openLoginScreen() = navigator.navigate(GameResultToLoginCommand())

    fun getFormattedTime(time: Long): String {
        val date = Date(time)
        return requireNotNull(
            if (time >= HOUR_MILLISECONDS) formatWihHours.format(date)
            else formatWithMinutes.format(date)
        ).toString()
    }

    fun getMotivationStringId(
        correctAnswers: Int,
        mistakeAnswers: Int
    ): Int = if (correctAnswers != 0) {
        val correctAnswerPercent = (correctAnswers + mistakeAnswers) / correctAnswers
        when {
            correctAnswerPercent >= BEST_COUNT_ANSWER -> Motivation.BEST
            correctAnswerPercent >= GOOD_COUNT_ANSWER -> Motivation.GOOD
            else -> Motivation.BAD
        }.stringId
    } else Motivation.BAD.stringId

    companion object {
        private val formatWithMinutes = SimpleDateFormat("mm:ss")
        private val formatWihHours = SimpleDateFormat("h:mm:ss")
        private const val HOUR_MILLISECONDS = 3_600_000
        private const val BEST_COUNT_ANSWER = 0.9
        private const val GOOD_COUNT_ANSWER = 0.5
    }
}