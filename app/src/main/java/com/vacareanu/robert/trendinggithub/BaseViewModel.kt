package com.vacareanu.robert.trendinggithub

import android.arch.lifecycle.ViewModel
import android.util.Log


abstract class BaseViewModel(val tag: String) : ViewModel() {
    fun logV(message: String) {
        Log.v(tag, message)
    }
}