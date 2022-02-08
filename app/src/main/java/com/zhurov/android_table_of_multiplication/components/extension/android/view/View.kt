package com.zhurov.android_table_of_multiplication.components.extension.android.view

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard() {
    clearFocus()
    val inputMethodManager = context
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun View.openKeyboard() {
    requestFocus()
    val inputMethodManager = context
        .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.RESULT_SHOWN)
}