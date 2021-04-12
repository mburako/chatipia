package com.burak.chatipia.data.remote

import com.burak.chatipia.data.local.LocalMessages
import com.squareup.moshi.Json


/**
 * Created by mburak on 8.04.2021.
 */
data class Message(@field:Json(name = "id") val id: String,
                   @field:Json(name = "text") val text: String?,
                   @field:Json(name = "timestamp") val timestamp: Long,
                   @field:Json(name = "user") val user: User) {

    fun convertToLocalMessage(owner: String): LocalMessages {
        return LocalMessages(id = id, text = text, timestamp = timestamp, username = user.nickname, avatarURL = user.avatarURL, ownerName = owner)
    }
}