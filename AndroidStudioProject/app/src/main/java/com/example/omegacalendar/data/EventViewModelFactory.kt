package com.example.omegacalendar.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
// type casting a viewmodel to turn it into the one we used in the app, takes DAO as input to be used in the viewmodel
class EventViewModelFactory(
    private val dao: EventDao
):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EventViewModel::class.java)){
            return EventViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}
