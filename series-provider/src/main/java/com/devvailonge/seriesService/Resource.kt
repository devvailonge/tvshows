package com.devvailonge.seriesService

data class Resource<out T>(val status: Status, val data: T?, val message: Throwable?) {

    enum class Status {
        SUCCESS,
        ERROR
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(throwable: Throwable, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, throwable)
        }
    }
}