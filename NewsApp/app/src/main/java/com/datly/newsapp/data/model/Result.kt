package com.datly.newsapp.data.model

data class Result<out T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    enum class Status{
        SUCCESS, FAILED, LOADING
    }

    companion object {
        fun <T> success(data: T): Result<T> = Result(Status.SUCCESS, data, null)
        fun <T> failed(data: T? = null, message: String) = Result(Status.FAILED, data, message)
        fun <T> loading(data: T? = null) = Result(Status.LOADING, data, null)
    }
}