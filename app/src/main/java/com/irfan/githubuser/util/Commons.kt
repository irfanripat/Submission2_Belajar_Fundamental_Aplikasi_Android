package com.irfan.githubuser.util

import android.view.View

object Commons {
    fun View.hide() {
        visibility = View.INVISIBLE
    }

    fun View.show() {
        visibility = View.VISIBLE
    }
}