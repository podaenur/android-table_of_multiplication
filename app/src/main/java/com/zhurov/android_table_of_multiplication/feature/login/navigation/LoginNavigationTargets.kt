package com.zhurov.android_table_of_multiplication.feature.login.navigation

import com.zhurov.android_table_of_multiplication.components.navigation.command.ActivityNavigationCommand
import com.zhurov.android_table_of_multiplication.components.navigation.TargetScreen
import com.zhurov.android_table_of_multiplication.feature.settings.presentation.SettingsFragment

class LoginToSettingsCommand : ActivityNavigationCommand.NavigateToFragment(
    sourceScreen = TargetScreen.FragmentScreen(
        SettingsFragment.instance()
    )
)