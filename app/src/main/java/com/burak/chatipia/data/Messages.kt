package com.burak.chatipia.data

import com.squareup.moshi.Json


/**
 * Created by mburak on 8.04.2021.
 */
data class Messages(@field:Json(name = "messages") val messages: List<Message>) {
}