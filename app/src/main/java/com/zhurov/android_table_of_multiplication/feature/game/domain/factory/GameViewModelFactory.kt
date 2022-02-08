package com.zhurov.android_table_of_multiplication.feature.game.domain.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhurov.android_table_of_multiplication.components.navigation.Navigator
import com.zhurov.android_table_of_multiplication.feature.game.domain.arguments.GameArguments
import com.zhurov.android_table_of_multiplication.feature.game.presentation.GameViewModel

@Suppress("UNCHECKED_CAST")
class GameViewModelFactory(
    private val navigator: Navigator,
    private val gameArguments: GameArguments
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == GameViewModel::class.java)
        return GameViewModel(
            navigator,
            gameArguments
        ) as T
    }
}