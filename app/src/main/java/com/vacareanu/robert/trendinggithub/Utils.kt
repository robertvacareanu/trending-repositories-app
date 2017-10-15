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
//    val result = Repository("URL$created",
//            "Name$created",
//            "Description$created",
//            Date(System.currentTimeMillis() - random.nextLong() % 123456),
//            Date(),
//            random.nextInt() % 12345,
//            mutableListOf("A", "B", "C"),
//            random.nextInt() % 1234,
//            random.nextInt() % 123
//    )
    val result = Repository()
    result.url = "URL$created"
    result.name = "Name$created"
    result.created = Date(System.currentTimeMillis() - random.nextLong() % 123456)
    result.updated = Date()
    result.forks = random.nextInt() % 123
    result.stars = random.nextInt() % 1234
    result.languages = mutableListOf("A", "B", "C")
    result.size = random.nextInt() % 1234

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