package com.burak.chatipia.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.burak.chatipia.data.local.LocalMessages
import com.burak.chatipia.data.local.MessagesDao


/**
 * Created by mburak on 11.04.2021.
 */
@Database(entities = [LocalMessages::class], version = 5)
abstract class AppDatabase: RoomDatabase() {
    abstract fun messagesDao(): MessagesDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "messages")
                .fallbackToDestructiveMigration()
                .build()
    }
}