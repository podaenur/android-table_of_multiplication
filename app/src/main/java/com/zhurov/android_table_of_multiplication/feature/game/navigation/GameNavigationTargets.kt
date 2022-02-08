package com.zhurov.android_table_of_multiplication.feature.game.navigation

import com.zhurov.android_table_of_multiplication.components.navigation.TargetScreen
import com.zhurov.android_table_of_multiplication.components.navigation.command.ActivityNavigationCommand
import com.zhurov.android_table_of_multiplication.feature.game_result.domain.arguments.GameResultArguments
import com.zhurov.android_table_of_multiplication.feature.game_result.presentation.GameResultFragment
import com.zhurov.android_table_of_multiplication.feature.login.presentation.LoginFragment

class GameToLoginCommand : ActivityNavigationCommand.ReplaceFragment(
    sourceScreen = TargetScreen.FragmentScreen(
        LoginFragment.instance()
    )
)

data class GameToGameResultCommand(
    private val gameArguments: GameResultArguments
) : ActivityNavigationCommand.ReplaceFragment(
    sourceScreen = TargetScreen.FragmentScreen(
        GameResultFragment.instance(gameArguments)
    )
)