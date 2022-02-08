package com.zhurov.android_table_of_multiplication.components.navigation

import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.zhurov.android_table_of_multiplication.components.navigation.base.activity.NavigationActivity

sealed class Source {
    data class ActivityScreen(
        val activity: NavigationActivity,
        val containerId: Int = 0,
        val fragmentManager: FragmentManager = activity.supportFragmentManager
    ) : Source()

    data class FragmentScreen(
        val activity: FragmentActivity,
        @LayoutRes val containerId: Int,
        val fragmentManager: FragmentManager = activity.supportFragmentManager
    ) : Source()
}