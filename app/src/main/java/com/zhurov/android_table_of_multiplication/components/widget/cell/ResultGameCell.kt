package com.zhurov.android_table_of_multiplication.components.widget.cell

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.android.material.textview.MaterialTextView
import com.zhurov.android_table_of_multiplication.R

class ResultGameCell : LinearLayoutCompat {
    private val titleView: MaterialTextView by lazy {
        requireNotNull(
            findViewById(R.id.title)
        )
    }
    private val valueView: MaterialTextView by lazy {
        requireNotNull(
            findViewById(R.id.value)
        )
    }



    constructor(context: Context): super(context) {
        initView()
    }

    constructor(
        context: Context,
        attributeSet: AttributeSet
    ) : super(context, attributeSet) {
        initView()
        initAttributes(attributeSet)
    }

    constructor(
        context: Context,
        attributeSet: AttributeSet,
        defStyleRes: Int
    ) : super(context, attributeSet, defStyleRes) {
        initView()
        initAttributes(attributeSet)
    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.widget_result_game_cell, this)
    }

    private fun initAttributes(attributeSet: AttributeSet) {
        val typedArray = context.theme.obtainStyledAttributes(
                attributeSet,
        R.styleable.ResultGameCell,
        0, 0
        )

        try {
            val titleAttr = typedArray.getString(R.styleable.ResultGameCell_title) ?: ""
            val valueAttr = typedArray.getString(R.styleable.ResultGameCell_value) ?: ""
            title = titleAttr
            value = valueAttr
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            typedArray.recycle()
        }
    }

    var title: String
        get() = titleView.text.toString()
        set(value) {
            titleView.text = value
        }

    var value: String
        get() = valueView.text.toString()
        set(value) {
            valueView.text = value
        }

}