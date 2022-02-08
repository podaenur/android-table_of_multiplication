package com.zhurov.android_table_of_multiplication.feature.login.domain.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhurov.android_table_of_multiplication.components.navigation.Navigator
import com.zhurov.android_table_of_multiplication.feature.login.presentation.LoginViewModel

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(
    private val navigator: Navigator
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == LoginViewModel::class.java)
        return LoginViewModel(navigator) as T
    }
}