package com.example.todosappmongodb.utils

import android.view.View

object ViewExtensionFunctions
{

    fun View.show()
    {
        this.visibility = View.VISIBLE
    }

    fun View.hide()
    {
        this.visibility = View.GONE
    }


} // object closed