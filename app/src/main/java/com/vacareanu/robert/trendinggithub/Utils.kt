package com.vacareanu.robert.trendinggithub

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vacareanu.robert.trendinggithub.model.Repository
import java.util.*

/**
 * Created by robert on 9/25/17.
 */
fun Context.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

var created = 0
fun randomRepo(): Repository {
    val random = Random()
    val result = Repository("URL$created",
            "Name$created",
            "Description$created",
            Date(System.currentTimeMillis() - random.nextLong() % 123456),
            Date(),
            random.nextInt() % 12345,
            mutableListOf("A", "B", "C"),
            random.nextInt() % 1234,
            random.nextInt() % 123
    )


    created++
    return result
}

fun printEveryViewInside(view: View, parent: View) {
    Log.v("RVCL", "${view::class.java.simpleName}, ${view.x}, ${view.y}, ${view.translationX}, ${view.translationY} inside ${parent::class.java.simpleName}")
    if (view is ViewGroup) {
        val c = view.childCount
        for (a in 0 until c) {
            printEveryViewInside(view.getChildAt(a), view)
        }
    }
}