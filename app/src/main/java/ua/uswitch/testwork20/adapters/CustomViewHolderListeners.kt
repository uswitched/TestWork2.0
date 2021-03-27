package ua.uswitch.testwork20.adapters

import android.view.View

interface CustomHandler {
    fun onClick(position: Int)

    fun onLongClickListener(position: Int, view: View): Boolean
}