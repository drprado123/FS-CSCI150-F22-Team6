package com.example.omegacalendar.data


import com.example.omegacalendar.R
import com.example.omegacalendar.model.Events
import com.example.omegacalendar.model.Time

class Datasource() {
    fun loadAffirmations(): List<Events>{
        return listOf<Events>(
            Events(R.string.Event1, R.string.Time1, R.string.Mon, R.string.PM),
            Events(R.string.Event2, R.string.Time2, R.string.Tue, R.string.AM),
            Events(R.string.Event3, R.string.Time3, R.string.Wed, R.string.PM),
            Events(R.string.Event4, R.string.Time4, R.string.Thu, R.string.PM),
            Events(R.string.Event5, R.string.Time5, R.string.Fri, R.string.PM),
            Events(R.string.Event6, R.string.Time6, R.string.Sat, R.string.AM),
            Events(R.string.Event7, R.string.Time7, R.string.Sun, R.string.PM),
            Events(R.string.Event8, R.string.Time8, R.string.Mon, R.string.AM),
            Events(R.string.Event9, R.string.Time9, R.string.Tue, R.string.AM),
            Events(R.string.Event10, R.string.Time10, R.string.Tue, R.string.PM),
            Events(R.string.Event11, R.string.Time11, R.string.Tue, R.string.PM)
        )
    }

}