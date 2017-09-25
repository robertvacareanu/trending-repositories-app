package com.vacareanu.robert.trendinggithub

import android.app.Activity
import android.widget.Toast

/**
 * Created by robert on 9/25/17.
 */
fun Activity.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}