package com.perusudroid.androidassignment.data.model

class NetworkState(status: Status, msg: String) {

    var status: Status? = status
    var msg: String? = msg

    enum class Status {
        LOADING,
        LOADED, // New
        EMPTY, // New
        FAILED,
        NO_LOAD_MORE
    }

    companion object {

        fun loading(): NetworkState {
            return NetworkState(Status.LOADING, "")
        }

        fun loaded(): NetworkState {
            return NetworkState(Status.LOADED, "")
        }

        fun empty(msg: String): NetworkState {
            return NetworkState(Status.EMPTY, msg)
        }

        fun error(msg: String): NetworkState {
            return NetworkState(Status.FAILED, msg)
        }
    }
}
