package com.zhurov.android_table_of_multiplication.components.navigation

import androidx.lifecycle.ViewModel

class NavigationViewModel(
    val navigator: Navigator,
    val navigatorProvider: NavigationActionProvider
) : ViewModel()