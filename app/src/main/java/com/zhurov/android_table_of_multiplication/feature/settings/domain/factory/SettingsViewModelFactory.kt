package com.zhurov.android_table_of_multiplication.feature.settings.domain.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zhurov.android_table_of_multiplication.components.navigation.Navigator
import com.zhurov.android_table_of_multiplication.feature.settings.presentation.SettingsViewModel

@Suppress("UNCHECKED_CAST")
class SettingsViewModelFactory(
    private val navigator: Navigator
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == SettingsViewModel::class.java)
        return SettingsViewModel(navigator) as T
    }
}