package com.example.sampleapplication.network

data class Resource<out T>(val status: Status, val message: String?, val data: T?) {

    companion object {
        fun <T> success(data: T? = null, message: String): Resource<T> {
            return Resource(Status.SUCCESS, message, data)
        }

        fun <T> error(message: String): Resource<T> {
            return Resource(Status.ERROR, message, null)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }
}
