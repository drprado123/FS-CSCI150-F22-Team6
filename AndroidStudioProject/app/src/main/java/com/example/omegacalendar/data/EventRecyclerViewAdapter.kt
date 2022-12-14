package com.example.omegacalendar.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.omegacalendar.R
// This was used for testing before app components were integrated, Anthony didn't know compose so he wrote this to setup a dynamic list that reflects changes.
class EventRecyclerViewAdapter(): RecyclerView.Adapter<EventViewHolder>() {
    private val eventList = ArrayList<Event>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item, parent, false)
        return EventViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
    fun setList(events: List<Event>){
        eventList.clear()
        eventList.addAll(events)

    }

}
class EventViewHolder(private val view: View):RecyclerView.ViewHolder(view){
    fun bind(event: Event){
        val dateTv = view.findViewById<TextView>(R.id.tvDate)
        val descTv = view.findViewById<TextView>(R.id.tvDesc)
        dateTv.text = event.month.toString() + ' ' + '/' + ' ' + event.day.toString() + ' '+ '/' + ' ' + event.year.toString()
        descTv.text = event.desc
        // add onclick event listener08
    }
}
