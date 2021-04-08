package com.burak.chatipia.data

import com.squareup.moshi.Json


/**
 * Created by mburak on 8.04.2021.
 */
data class Message(@field:Json(name = "id") val name: String,
                   @field:Json(name = "text") val text: String?,
                   @field:Json(name = "timestamp") val timestamp: Long?,
                   @field:Json(name = "user") val user: User?) {
}