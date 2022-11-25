package com.example.omegacalendar.data

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
//add args to  event model as needed to set what is seen in a view model MUST REFLECT CHANGES
// IN VIEWMODEL FACTORY
class EventViewModel(
    private val dao: EventDao,
    //private val month: Int,
    cal: Calendar = GregorianCalendar.getInstance()
): ViewModel() {
    // val _ = dao.query() to send that to the recycler view.

    private val _uiState = MutableStateFlow(MonthUiState(yrNum = cal.get(Calendar.YEAR), mnNum = cal.get(Calendar.MONTH)))
    val uiState: StateFlow<MonthUiState> = _uiState.asStateFlow()

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
    fun prevMonthButton(){
        var m = _uiState.value.mnNum
        var y = _uiState.value.yrNum

        m--
        if(m == -1){
            m = 11
            --y
        }
        _uiState.update { currentState ->
            currentState.copy(
                mnNum = m,
                yrNum = y
            )
        }
    }
    fun nextMonthButton(){
        var m = _uiState.value.mnNum
        var y = _uiState.value.yrNum

        m++
        if(m == 12){
            m = 0
            ++y
        }
        _uiState.update { currentState ->
            currentState.copy(
                mnNum = m,
                yrNum = y
            )
        }
    }
}