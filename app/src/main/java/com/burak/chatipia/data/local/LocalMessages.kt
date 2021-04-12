package com.burak.chatipia.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


/**
 * Created by mburak on 11.04.2021.
 */
@Entity(indices = [Index(value = ["id", "ownerName"], unique = true)])
data class LocalMessages(@PrimaryKey (autoGenerate = true) val messageId: Long = 0,
                         @ColumnInfo(name = "id") val id: String,
                         val text: String?,
                         val timestamp: Long,
                         val username: String,
                         val avatarURL: String,
                         val ownerName: String) {
}