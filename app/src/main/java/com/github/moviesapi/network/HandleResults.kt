package com.github.moviesapi.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers


fun <T> resultLiveDataWithoutDB(
    networkCall: suspend () -> Result<T>
): LiveData<Result<T>> = liveData(Dispatchers.IO) {
    emit(Result.loading())
    val response = networkCall.invoke()
    if (response.status == Result.Status.SUCCESS) {
        emit(Result.success(response.data))
    } else if (response.status == Result.Status.ERROR) {
        emit(Result.error(response.message!!))
    }
}