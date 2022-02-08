package com.zhurov.android_table_of_multiplication.feature.login.presentation

import androidx.lifecycle.ViewModel
import com.zhurov.android_table_of_multiplication.components.navigation.Navigator
import com.zhurov.android_table_of_multiplication.feature.login.navigation.LoginToSettingsCommand

class LoginViewModel(
    private val navigator: Navigator
) : ViewModel() {

    fun openSettingsScreen() = navigator.navigate(LoginToSettingsCommand())
}