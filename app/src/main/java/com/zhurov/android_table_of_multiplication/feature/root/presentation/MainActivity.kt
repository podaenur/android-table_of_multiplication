package com.zhurov.android_table_of_multiplication.feature.root.presentation

import android.os.Bundle
import com.zhurov.android_table_of_multiplication.R
import com.zhurov.android_table_of_multiplication.components.navigation.NavigationActionProvider
import com.zhurov.android_table_of_multiplication.components.navigation.base.activity.NavigationActivity
import com.zhurov.android_table_of_multiplication.components.navigation.base.viewmodel.NavigationActivityViewModel
import com.zhurov.android_table_of_multiplication.di.mainActivity
import com.zhurov.android_table_of_multiplication.di.mainViewModel
import com.zhurov.android_table_of_multiplication.di.navigationProvider

class MainActivity : NavigationActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity = this
        super.onCreate(savedInstanceState)
    }

    override val fragmentContainerId: Int = R.id.main_container

    override val navigationActionProvider: NavigationActionProvider by lazy {
        navigationProvider
    }

    override val viewModel: NavigationActivityViewModel by lazy {
        mainViewModel
    }
}