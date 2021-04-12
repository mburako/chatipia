package com.burak.chatipia.data.local

import androidx.lifecycle.LiveData
import androidx.room.*


/**
 * Created by mburak on 11.04.2021.
 */
@Dao
interface MessagesDao {
    @Query("SELECT * FROM localmessages")
    fun getAll(): LiveData<List<LocalMessages>>

    @Query("SELECT * FROM localmessages WHERE ownerName = :nick ORDER BY timestamp ASC")
    fun loadAllByIds(nick: String): LiveData<List<LocalMessages>>

    @Insert
    suspend fun insert(vararg messages: LocalMessages)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(messages: List<LocalMessages>)

    @Delete
    suspend fun delete(message: LocalMessages)
}