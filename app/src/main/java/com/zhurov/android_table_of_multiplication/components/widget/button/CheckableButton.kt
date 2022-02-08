package com.zhurov.android_table_of_multiplication.components.widget.button

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable
import androidx.appcompat.widget.AppCompatButton

class CheckableButton : AppCompatButton, Checkable {

    constructor(context: Context) : super(context)

    constructor(
        context: Context,
        attrs: AttributeSet
    ) : super(context, attrs)

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleRes: Int
    ) : super(context, attrs, defStyleRes)

    private var isCheckedState = false

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState: IntArray = super.onCreateDrawableState(extraSpace + 1)
        if (isChecked()) mergeDrawableStates(drawableState, CHECKED_STATE_SET)
        return drawableState
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        invalidate()
    }

    override fun performClick(): Boolean {
        toggle()
        return super.performClick()
    }

    override fun toggle() = setChecked(isCheckedState.not())

    override fun isChecked() = isCheckedState

    override fun setChecked(checked: Boolean) {
        if (isCheckedState == checked) return
        isCheckedState = checked
        refreshDrawableState()
    }

    companion object {
        private val CHECKED_STATE_SET = intArrayOf(android.R.attr.state_checked)
    }
}