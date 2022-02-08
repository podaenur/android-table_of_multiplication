package com.zhurov.android_table_of_multiplication.feature.game_result.navigation

import com.zhurov.android_table_of_multiplication.components.navigation.TargetScreen
import com.zhurov.android_table_of_multiplication.components.navigation.command.ActivityNavigationCommand
import com.zhurov.android_table_of_multiplication.feature.login.presentation.LoginFragment

class GameResultToLoginCommand : ActivityNavigationCommand.ReplaceFragmentWithCleanup(
    sourceScreen = TargetScreen.FragmentScreen(
        LoginFragment.instance()
    )
)