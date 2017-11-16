package com.vacareanu.robert.trendinggithub

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.vacareanu.robert.trendinggithub.model.Repository
import java.util.*


fun Context.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

var created = 0
fun randomRepo(): Repository {
    val random = Random()
    val result = Repository()

    with(result) {
        url = "URL${com.vacareanu.robert.trendinggithub.created}"
        name = "Name${com.vacareanu.robert.trendinggithub.created}"
        created = Date(System.currentTimeMillis() - random.nextLong() % 123456)
        updated = Date()
        forks = random.nextInt() % 123
        stars = random.nextInt() % 1234
        languages = mutableListOf("A", "B", "C")
        size = random.nextInt() % 1234
        isFavorite = false
    }

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

fun <T> transformationsfilter(source: LiveData<T>, tester: (T?) -> Boolean): LiveData<T> {
    val result = MediatorLiveData<T>()
    result.addSource(source) { item: T? ->
        if (tester(item)) result.value = item
    }

    return result
}

fun <T> transformationTakeFirst(source: LiveData<List<T>>, tester: (T?) -> Boolean): LiveData<T> {
    val result = MediatorLiveData<T>()

    result.addSource(source) { t: List<T>? ->
        result.value = t?.first(tester)
    }

    return result
}

fun <T> transformationTakeNth(source: LiveData<List<T>>, n: Int): LiveData<T> {
    val result = MediatorLiveData<T>()

    result.addSource(source) { t: List<T>? ->
        Log.v("Utils", "Try to add")
        t?.let { Log.v("Utils", "Added ${t[n].toString()}"); result.value = t[n] }
    }

    return result
}