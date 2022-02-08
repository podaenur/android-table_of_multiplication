package com.zhurov.android_table_of_multiplication.components.navigation.command

import com.zhurov.android_table_of_multiplication.components.navigation.NavigationTarget
import com.zhurov.android_table_of_multiplication.components.navigation.TargetScreen

sealed class ActivityNavigationCommand(sourceScreen: TargetScreen) :
    NavigationTarget(sourceScreen) {
    abstract class NavigateToActivity(sourceScreen: TargetScreen.ActivityScreen) :
        ActivityNavigationCommand(sourceScreen)

    abstract class NavigateToFragment(sourceScreen: TargetScreen.FragmentScreen) :
        ActivityNavigationCommand(sourceScreen)

    abstract class ReplaceActivity(sourceScreen: TargetScreen.ActivityScreen) :
        ActivityNavigationCommand(sourceScreen)

    abstract class ReplaceFragment(sourceScreen: TargetScreen.FragmentScreen) :
        ActivityNavigationCommand(sourceScreen)

    abstract class ReplaceFragmentWithCleanup(sourceScreen: TargetScreen.FragmentScreen) :
        ActivityNavigationCommand(sourceScreen)

    object Back :
        ActivityNavigationCommand(TargetScreen.BackScreen)
}