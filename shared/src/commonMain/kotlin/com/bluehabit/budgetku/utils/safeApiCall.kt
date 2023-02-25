package com.bluehabit.budgetku.utils

import com.bluehabit.budgetku.model.BaseResponse
import io.ktor.client.call.*
import io.ktor.client.network.sockets.*
import io.ktor.client.statement.*
import kotlinx.serialization.SerializationException

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

    } catch (e: SerializationException) {
        Response.Error(e.message.orEmpty(), 0)
    } catch (e: SocketTimeoutException) {
        Response.Error("Timeout")
    } catch (e: ConnectTimeoutException) {
        Response.Error("Timeout")
    }
}
suspend inline fun <reified T> safeApiCall( onSaveToken: (token:String) -> Unit = {},call: () -> HttpResponse): Response<BaseResponse<T>> {
    return try {

        val response = call.invoke()
        if (response.status.value in 200..209) {
            val data = response.body<BaseResponse<T>>()
            if(data.token.isNotEmpty()){
                onSaveToken(data.token)
            }
            Response.Result(data)
        } else {
            val data = response.body<BaseResponse<List<Any>>>()
            Response.Error(data.message, data.code)
        }

    } catch (e: SerializationException) {
        Response.Error(e.message.orEmpty(), 0)
    } catch (e: SocketTimeoutException) {
        Response.Error("Timeout")
    } catch (e: ConnectTimeoutException) {
        Response.Error("Timeout")
    }
}


