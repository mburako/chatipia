package com.burak.chatipia.data.remote

import androidx.room.Entity
import com.squareup.moshi.Json


/**
 * Created by mburak on 8.04.2021.
 */
data class User(@field:Json(name = "id") val id: String,
                @field:Json(name = "avatarURL") val avatarURL: String,
                @field:Json(name = "nickname") val nickname: String) {
}