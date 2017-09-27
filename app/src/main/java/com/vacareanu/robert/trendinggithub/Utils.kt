package com.vacareanu.robert.trendinggithub

import android.app.Activity
import android.widget.Toast
import com.vacareanu.robert.trendinggithub.model.Repository
import java.util.*

/**
 * Created by robert on 9/25/17.
 */
fun Activity.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
var created = 0
fun randomRepo() : Repository {
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