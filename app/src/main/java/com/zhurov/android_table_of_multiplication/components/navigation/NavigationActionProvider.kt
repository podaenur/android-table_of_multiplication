package com.zhurov.android_table_of_multiplication.components.navigation

import androidx.lifecycle.LifecycleOwner

interface NavigationActionProvider {

    fun bind(source: Source, lifecycleOwner: LifecycleOwner)
}