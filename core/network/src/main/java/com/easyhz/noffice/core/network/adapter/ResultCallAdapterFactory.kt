package com.easyhz.noffice.core.network.adapter

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject

class ResultCallAdapterFactory @Inject constructor(
    private val gson: Gson
): CallAdapter.Factory() {

    companion object {
        fun create(gson: Gson) = ResultCallAdapterFactory(gson = gson)
    }

    override fun get(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if(getRawType(type) != Call::class.java) return null

        val wrapperType = getParameterUpperBound(0, type as ParameterizedType)

        if (getRawType(wrapperType) != Result::class.java) return null

        val responseType = getParameterUpperBound(0, wrapperType as ParameterizedType)
        return ResultCallAdapter(responseType, gson)
    }
}