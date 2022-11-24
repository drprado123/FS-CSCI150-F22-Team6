package com.example.omegacalendar.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class EventViewModelFactory(
    private val dao: EventDao,
    private val month: Int
):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EventViewModel::class.java)){
            return EventViewModel(dao, /*month*/) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}