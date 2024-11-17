package com.syfuzzaman.test_project_gozayaan.data.utils

import com.syfuzzaman.test_project_gozayaan.data.model.Error
import com.syfuzzaman.test_project_gozayaan.data.model.Resource
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.CancellationException

suspend fun <T> resultFromResponse(networkCall: suspend () -> T): Resource<T> =
    try {
        val response = networkCall.invoke()
        Resource.Success(response)
    } catch (e:Exception){
        Resource.Failure(getError(e))
    }


fun getError(e: Exception): Error {
    e.printStackTrace()
    when (e) {
        is HttpException -> {
            return Error(e.code(), e.message().ifBlank { "Unknown error occurred" })
        }
        is SocketTimeoutException -> {
            return Error(-1, "Timeout")
        }
        is IOException -> {
            return Error(-1, "Unable to connect server.")
        }
        is CancellationException -> {
            return Error(-1, "The task is cancelled")
        }
        else -> {
            return Error(-1, "Unknown error occurred")
        }
    }
}