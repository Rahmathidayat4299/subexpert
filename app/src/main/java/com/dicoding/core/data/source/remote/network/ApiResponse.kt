package com.dicoding.core.data.source.remote.network

/**
 * Created by Rahmat Hidayat on 07/01/2023.
 */
sealed class ApiResponse<out T> {
    data class Success<out T> (val data: T): ApiResponse<T>()
    data class Error(val errorMessage: String?): ApiResponse<Nothing>()
}