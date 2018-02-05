package com.moducode.forzateams

import android.util.MalformedJsonException
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * Created by Jay on 2018-02-03.
 * We can use this class to return different string resources depending on the error
 * the user receives
 */

object ErrorHelper {

    fun getMessage(t: Throwable): Int {
        return when (t) {
            is MalformedJsonException -> R.string.error_parse_json
            is ConnectException -> R.string.error_connection
            is SocketTimeoutException -> R.string.error_connection
            else -> R.string.error_fetching_teams
        }
    }

}
