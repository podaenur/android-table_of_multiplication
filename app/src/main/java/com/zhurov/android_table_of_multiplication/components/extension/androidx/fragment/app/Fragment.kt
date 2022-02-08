package com.zhurov.android_table_of_multiplication.components.extension.androidx.fragment.app

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

fun <T> Fragment.findViewById(@IdRes id: Int) = lazy {
    requireNotNull(
        view?.findViewById(id) as? T
    )
}

fun <T> Fragment.getParcelable(key: String) = lazy {
    requireNotNull(
        arguments?.getParcelable(key) as? T
    )
}