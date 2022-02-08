package com.zhurov.android_table_of_multiplication.feature.settings.navigation

import com.zhurov.android_table_of_multiplication.components.navigation.command.ActivityNavigationCommand
import com.zhurov.android_table_of_multiplication.components.navigation.TargetScreen
import com.zhurov.android_table_of_multiplication.feature.game.domain.arguments.GameArguments
import com.zhurov.android_table_of_multiplication.feature.game.presentation.GameFragment

class SettingsToGameCommand(
    gameArguments: GameArguments
) : ActivityNavigationCommand.ReplaceFragmentWithCleanup(
    sourceScreen = TargetScreen.FragmentScreen(
        GameFragment.instance(gameArguments)
    )
)