package com.zhurov.android_table_of_multiplication.components.navigation.base.viewmodel

import androidx.lifecycle.ViewModel
import com.zhurov.android_table_of_multiplication.components.navigation.Navigator
import com.zhurov.android_table_of_multiplication.components.navigation.command.ActivityNavigationCommand

open class NavigationActivityViewModel(
    protected open val navigator: Navigator
) : ViewModel() {

    fun navigateToBack() = navigator.navigate(ActivityNavigationCommand.Back)
}