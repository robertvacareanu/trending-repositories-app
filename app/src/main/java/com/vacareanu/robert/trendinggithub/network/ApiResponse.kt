package com.vacareanu.robert.trendinggithub.network

import retrofit2.Response

class ApiResponse<T> {
    val code: Int
    val body: T?
    val errorMessage: String?
    constructor(response: Response<T>) {
        code = response.code()
        if(response.isSuccessful) {
            body = response.body()!!
            errorMessage = null
        } else {
            body = null
            errorMessage = response.errorBody()?.string()
        }
    }

    constructor(error: Throwable) {
        code = 500
        body=null
        this.errorMessage = error.message
    }
}