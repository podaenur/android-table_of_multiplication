package com.zhurov.android_table_of_multiplication.components.navigation.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.zhurov.android_table_of_multiplication.components.navigation.NavigationActionProvider
import com.zhurov.android_table_of_multiplication.components.navigation.Source
import com.zhurov.android_table_of_multiplication.components.navigation.base.viewmodel.NavigationActivityViewModel

abstract class NavigationActivity(@LayoutRes layoutRes: Int) : AppCompatActivity(layoutRes) {

    protected abstract val navigationActionProvider: NavigationActionProvider

    protected abstract val viewModel: NavigationActivityViewModel

    protected abstract val fragmentContainerId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationActionProvider.bind(
            source = Source.ActivityScreen(this, fragmentContainerId),
            lifecycleOwner = this
        )
    }

    override fun onBackPressed() = viewModel.navigateToBack()

    fun finalBackPressed() = super.onBackPressed()
}