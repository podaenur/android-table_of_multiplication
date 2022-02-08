package com.zhurov.android_table_of_multiplication.components.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class NavigationViewModelFactory(
    private val navigator: Navigator,
    private val navigatorProvider: NavigationActionProvider
) : ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == NavigationViewModel::class.java)
        return NavigationViewModel(navigator, navigatorProvider) as T
    }
}