package com.moducode.forzateams

import com.google.gson.stream.MalformedJsonException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Jay on 2018-02-04.
 */

/**
 * Use this function to return false on exceptions we DON'T
 * want our observable to keep retrying for
 */
fun map(t : Throwable) : Boolean{
    return when(t){
        is SocketTimeoutException -> true
        is ConnectException -> true
        is UnknownHostException -> true
        else -> false
    }
}