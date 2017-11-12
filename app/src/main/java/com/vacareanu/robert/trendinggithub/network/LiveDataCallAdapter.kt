package com.vacareanu.robert.trendinggithub.network

import android.arch.lifecycle.LiveData
import com.vacareanu.robert.trendinggithub.model.Repository
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A retrofit adapter for converting the call into a LiveData of ApiResponse
 */
class LiveDataCallAdapter<R>(val responseType: Type): CallAdapter<R, LiveData<ApiResponse<R>>> {
    override fun responseType(): Type = responseType


    override fun adapt(call: Call<R>?): LiveData<ApiResponse<R>> {
        return object: LiveData<ApiResponse<R>>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if(started.compareAndSet(false, true)) {
                    call?.enqueue(object: Callback<R> {
                        override fun onResponse(call: Call<R>?, response: Response<R>?) {
                            postValue(ApiResponse(response!!))
                        }

                        override fun onFailure(call: Call<R>?, t: Throwable?) {
                            postValue(ApiResponse(t!!))
                        }
                    })
                }
            }
        }
    }


}