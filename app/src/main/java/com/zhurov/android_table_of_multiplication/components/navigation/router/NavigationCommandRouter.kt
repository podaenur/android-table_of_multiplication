package com.zhurov.android_table_of_multiplication.components.navigation.router

import androidx.fragment.app.FragmentManager
import com.zhurov.android_table_of_multiplication.components.navigation.NavigationTarget
import com.zhurov.android_table_of_multiplication.components.navigation.Source
import com.zhurov.android_table_of_multiplication.components.navigation.TargetScreen
import com.zhurov.android_table_of_multiplication.components.navigation.command.ActivityNavigationCommand
import com.zhurov.android_table_of_multiplication.components.navigation.command.ActivityNavigationCommand.*

class NavigationCommandRouter(source: Source.ActivityScreen) {
    private val activity = source.activity
    private val containerId = source.containerId
    private val fragmentManager = source.fragmentManager

    private val transactionStack = mutableListOf<String>()

    fun handleNavigationTarget(target: NavigationTarget) = when (target) {
        is ActivityNavigationCommand -> commitNavigation(target)
        else -> throw Exception("Not found Command")
    }

    private fun commitNavigation(target: ActivityNavigationCommand) {
        copyTransactionStack()

        when (target) {
            is NavigateToActivity -> startActivity(target.screen as TargetScreen.ActivityScreen)
            is NavigateToFragment ->
                commitFragment(target.screen as TargetScreen.FragmentScreen)
            is ReplaceActivity -> {
                startActivity(target.screen as TargetScreen.ActivityScreen)
                activity.finish()
            }
            is ReplaceFragment -> {
                if (transactionStack.isNotEmpty()) {
                    fragmentManager.popBackStack()
                    transactionStack.removeAt(transactionStack.lastIndex)
                    commitFragment(
                        sourceScreen = target.screen as TargetScreen.FragmentScreen,
                        isReplace = true
                    )
                } else commitFragment(
                    sourceScreen = target.screen as TargetScreen.FragmentScreen,
                    addToBackStack = false,
                    isReplace = true
                )
            }
            is ReplaceFragmentWithCleanup -> {
                commitFragmentWitClearStack(
                    sourceScreen = target.screen as TargetScreen.FragmentScreen,
                )
            }
            Back ->
                if (transactionStack.isNotEmpty()) {
                    fragmentManager.popBackStack()
                    transactionStack.removeAt(transactionStack.lastIndex)
                } else activity.finalBackPressed()
        }
    }

    private fun copyTransactionStack() {
        transactionStack.clear()
        for (i in 0 until fragmentManager.backStackEntryCount) {
            transactionStack.add(fragmentManager.getBackStackEntryAt(i).name!!)
        }
    }

    private fun commitFragment(
        sourceScreen: TargetScreen.FragmentScreen,
        addToBackStack: Boolean = true,
        isReplace: Boolean = false
    ) {
        val fragment = sourceScreen.fragment
        val tag = sourceScreen.fragmentTag
        val transaction = fragmentManager.beginTransaction()
        transaction.setReorderingAllowed(true)
        if (isReplace) transaction.replace(containerId, fragment, tag)
        else transaction.add(containerId, fragment, tag)
        if (addToBackStack) {
            transaction.addToBackStack(tag)
            transactionStack.add(tag)
        }
        transaction.commit()
    }

    private fun commitFragmentWitClearStack(
        sourceScreen: TargetScreen.FragmentScreen
    ) {
        val fragment = sourceScreen.fragment
        val tag = sourceScreen.fragmentTag
        val transaction = fragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(containerId, fragment, tag)
        transaction.commit()
        clearBackStack()
    }

    private fun clearBackStack() = with(fragmentManager) {
        if (backStackEntryCount > 0) {
            val first = getBackStackEntryAt(0)
            popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    private fun startActivity(sourceScreen: TargetScreen.ActivityScreen) {
        val activityIntent = sourceScreen.intentCreator(activity)
        activity.startActivity(activityIntent)
    }
}