package com.zhurov.android_table_of_multiplication.components.navigation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment

sealed class TargetScreen {
    data class ActivityScreen(
        val intentCreator: (context: Context) -> Intent
    ) : TargetScreen()

    data class FragmentScreen(
        val fragment: Fragment,
        val fragmentTag: String = fragment::class.java.simpleName
    ) : TargetScreen()

    object BackScreen : TargetScreen()
}