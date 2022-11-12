package com.example.omegacalendar.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
//add args to  event model as needed to set what is seen in a view model MUST REFLECT CHANGES
// IN VIEWMODEL FACTORY
class EventViewModel(private val dao: EventDao, private val month: Int): ViewModel() {
    // val _ = dao.query() to send that to the recycler view.
    val events = dao.getEvents()
    fun getEventsByMonth(month: Int): LiveData<List<Event>> {
        return dao.getMonths(month)
    }
    fun insertEvent(event: Event)=viewModelScope.launch{
        dao.insertEvent(event)
    }
    fun deleteEvent(event: Event)=viewModelScope.launch{
        dao.deleteEvent(event)
    }
    fun updateEvent(event: Event)=viewModelScope.launch{
        dao.updateEvent(event)
    }
}