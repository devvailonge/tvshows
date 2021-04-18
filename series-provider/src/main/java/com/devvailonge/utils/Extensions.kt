package com.devvailonge.utils

import com.devvailonge.seriesService.Resource
import timber.log.Timber

suspend fun <T> runCatchingResource(call: suspend () -> T): Resource<T> = try {
    Resource.success(call.invoke())
} catch (exception: Throwable) {
    Resource.error(exception)
}

suspend fun <T, A> cacheStrategy(
    databaseQuery: suspend () -> T,
    networkCall: suspend () -> A,
    saveCallResult: suspend (A) -> Unit
): T {
    try {
        val responseStatus = networkCall.invoke()
        saveCallResult(responseStatus)
    } catch (e: Exception) {
        Timber.e(e)
    }
    return databaseQuery.invoke()
}