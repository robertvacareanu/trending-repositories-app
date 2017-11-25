package com.vacareanu.robert.trendinggithub.network

import android.arch.lifecycle.LiveData
import android.util.Log
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


class LiveDataCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*, *>? {

        if (getRawType(returnType) != LiveData::class.java) {
            Log.v("CallAdapterFactory", "Got: ${getRawType(returnType).simpleName} instead of LiveData")
            return null
        }

        val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = getRawType(observableType)
        if (rawObservableType != ApiResponse::class.java) {
            Log.v("CallAdapterFactory", "Got: ${rawObservableType.simpleName} instead of ApiResponse")
            throw IllegalArgumentException("type must be a resource")
        }

        if (observableType !is ParameterizedType) {
            throw IllegalArgumentException("resource must be parameterized")
        }

        val bodyType = getParameterUpperBound(0, observableType)
        return LiveDataCallAdapter<Any>(bodyType)

    }

}