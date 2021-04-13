package com.burak.chatipia.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.burak.chatipia.repository.MessagesRepository
import kotlinx.coroutines.Dispatchers


/**
 * Created by mburak on 12.04.2021.
 */
class MainViewModel(private val repository: MessagesRepository): ViewModel() {
    val messages =  liveData(Dispatchers.IO) {
        val source = repository.getMessagesLocal()
        emitSource(source)

        try {
            val messages = repository.getMessagesRemote()
            repository.saveMessages(messages)
        } catch (e: Exception) {
            // Do nothing just ignore the results from remote
            e.printStackTrace()
        }
    }
}