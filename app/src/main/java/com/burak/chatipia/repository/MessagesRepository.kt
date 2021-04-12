package com.burak.chatipia.repository

import com.burak.chatipia.data.local.LocalMessages
import com.burak.chatipia.data.local.MessagesDao
import com.burak.chatipia.data.remote.Message
import com.burak.chatipia.data.remote.Messages
import com.burak.chatipia.network.NetworkManager


/**
 * Created by mburak on 11.04.2021.
 */
class MessagesRepository(private val messagesDao: MessagesDao, private val ownerName: String) {
    suspend fun getMessagesRemote() = NetworkManager.getRetrofit().getMessages()

    suspend fun getMessagesLocal() = messagesDao.loadAllByIds(ownerName)

    suspend fun saveMessages(messages: Messages) {
        val list = messages.messages
        val locals = convertLocalMessages(list)
        messagesDao.insertAll(locals)
    }


    private fun convertLocalMessages(messages: List<Message>): List<LocalMessages> {
        val list = mutableListOf<LocalMessages>()

       for (message in messages) {
           list.add(message.convertToLocalMessage(ownerName))
       }

        return list
    }


}