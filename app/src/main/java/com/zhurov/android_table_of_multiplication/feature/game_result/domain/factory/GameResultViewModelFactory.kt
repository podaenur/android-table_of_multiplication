package com.zhurov.android_table_of_multiplication.feature.game_result.domain.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhurov.android_table_of_multiplication.components.navigation.Navigator
import com.zhurov.android_table_of_multiplication.feature.game_result.presentation.GameResultViewModel

@Suppress("UNCHECKED_CAST")
class GameResultViewModelFactory(
    private val navigator: Navigator
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == GameResultViewModel::class.java)
        return GameResultViewModel(navigator) as T
    }
}