package com.zhurov.android_table_of_multiplication.components.navigation

import androidx.lifecycle.LifecycleOwner
import com.zhurov.android_table_of_multiplication.components.navigation.router.NavigationCommandRouter
import com.zhurov.android_table_of_multiplication.components.lifecycle.MutableSingleLiveEvent

class NavigatorImpl : Navigator, NavigationActionProvider {
    private var router: NavigationCommandRouter? = null
    private val navigationEvent = MutableSingleLiveEvent<NavigationTarget>()

    override fun navigate(target: NavigationTarget) {
        navigationEvent.sendEvent(target)
    }

    override fun bind(source: Source, lifecycleOwner: LifecycleOwner) {
        router = setupRouter(source)
        navigationEvent.observe(lifecycleOwner) {
            router!!.handleNavigationTarget(it)
        }
    }

    private fun setupRouter(source: Source) =
        if (source is Source.ActivityScreen) NavigationCommandRouter(source)
        else throw Exception("Not found Router")
}