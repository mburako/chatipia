package com.burak.chatipia.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.burak.chatipia.ui.MainViewModel
import com.burak.chatipia.repository.MessagesRepository


/**
 * Created by mburak on 12.04.2021.
 */
class ViewModelFactory(private val repository: MessagesRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Class name is not found")
    }
}