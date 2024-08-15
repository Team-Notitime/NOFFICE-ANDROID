package com.easyhz.noffice.core.network.adapter

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResultCallAdapter(
    private val responseType: Type,
    private val gson: Gson,
): CallAdapter<Type, Call<Result<Type>>> {
    override fun responseType(): Type = responseType
    override fun adapt(call: Call<Type>): Call<Result<Type>> = ResultCall(call, gson)
}
