package com.bluehabit.budgetku.utils

import com.bluehabit.budgetku.model.BaseResponse
import io.ktor.client.call.*
import io.ktor.client.statement.*

suspend inline fun <reified T> safeApiCall(call: () -> HttpResponse): Response<BaseResponse<T>> {
    return try {

        val response = call.invoke()
        if (response.status.value in 200..209) {
            val data = response.body<BaseResponse<T>>()
            Response.Result(data)
        } else {
            val data = response.body<BaseResponse<List<Any>>>()
            Response.Error(data.message, data.code)
        }

    } catch (e: Exception) {
        Response.Error(e.message.orEmpty(), 0)
    }
}