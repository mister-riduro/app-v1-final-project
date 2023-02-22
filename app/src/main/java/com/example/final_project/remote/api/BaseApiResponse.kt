package com.example.final_project.remote.api

import com.example.final_project.remote.network.NetworkResult
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return error()
        } catch (e: Exception) {
            return error()
        }
    }
    private fun <T> error(): NetworkResult<T> =
        NetworkResult.Error()
}