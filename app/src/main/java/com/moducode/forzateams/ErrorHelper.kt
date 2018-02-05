package com.moducode.forzateams

import android.util.MalformedJsonException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Jay on 2018-02-03.
**/


/**
* We can use this function to return different string resources depending on the error
* the user receives
*/
fun getMessage(t: Throwable): Int {
    return when (t) {
        is MalformedJsonException -> R.string.error_parse_json
        is ConnectException -> R.string.error_connection
        is SocketTimeoutException -> R.string.error_connection
        else -> R.string.error_fetching_teams
    }
}


/**
 * Use this function to return false on exceptions we DON'T
 * want our observable to keep retrying for
 */
fun retryOnError(t : Throwable) : Boolean{
    return when(t){
        is SocketTimeoutException -> true
        is ConnectException -> true
        is UnknownHostException -> true
        else -> false
    }
}

