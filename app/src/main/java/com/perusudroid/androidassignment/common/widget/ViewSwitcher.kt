package com.perusudroid.androidassignment.common.widget

import android.content.Context
import android.util.AttributeSet

import com.perusudroid.androidassignment.R


class ViewSwitcher : android.widget.ViewSwitcher {

    constructor(paramContext: Context) : super(paramContext) {
        init()
    }

    constructor(paramContext: Context, paramAttributeSet: AttributeSet) : super(
        paramContext,
        paramAttributeSet
    ) {
        init()
    }

    private fun init() {
        setInAnimation(context, R.anim.fast_fade_in)
        setOutAnimation(context, R.anim.fast_fade_out)
    }

    fun setChildVisible() {
        if (displayedChild != 1) {
            displayedChild = 1
        }
    }

    fun setParentVisible() {
        if (displayedChild != 0) {
            displayedChild = 0
        }
    }
}
