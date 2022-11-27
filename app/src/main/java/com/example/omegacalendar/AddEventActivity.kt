package com.example.omegacalendar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.omegacalendar.data.*

class AddEventActivity : AppCompatActivity() {
    //initialize data to pull from the activity xml
    private lateinit var monthText : EditText
    private lateinit var dayText : EditText
    private lateinit var yearText : EditText
    private lateinit var descText : EditText
    private lateinit var startText : EditText
    private lateinit var endText : EditText
    private lateinit var insertBtn : Button
    // initialize viewmodel to insert, delete, update, and view events from DAO
    private lateinit var viewModel : EventViewModel
    private lateinit var viewModel2 : EventViewModel
    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var eventRecyclerView2: RecyclerView
    private lateinit var adapter1: EventRecyclerViewAdapter
    private lateinit var adapter2: EventRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_event_activity)

        monthText = findViewById(R.id.monthTv)
        dayText = findViewById(R.id.dayTv)
        yearText = findViewById(R.id.yearTv)
        descText = findViewById(R.id.descTv)
        startText = findViewById(R.id.startTv)
        endText = findViewById(R.id.endTv)
        insertBtn = findViewById(R.id.add_event)

        eventRecyclerView = findViewById(R.id.rvEvent)
        eventRecyclerView2 = findViewById(R.id.rvEvent2)
        // use instance of application to get the current dao
        val dao = AppDatabase.getInstance(application).eventDao()
        //factory used to instantiate and pass dao to view model
        val factory = EventViewModelFactory(dao, 12)
        val factory2 = EventViewModelFactory(AppDatabase.getInstance(application).eventDao(), 8)

        viewModel = ViewModelProvider(this,factory).get(EventViewModel::class.java)
        viewModel2 = ViewModelProvider(this,factory2).get(EventViewModel::class.java)

        insertBtn.setOnClickListener {
            saveEvent()
            clearInput()
        }
        //initRecyclerView(eventRecyclerView, viewModel)
        // initRecyclerView(eventRecyclerView2, viewModel2)
        eventRecyclerView.layoutManager =  LinearLayoutManager(this)
        adapter1 = EventRecyclerViewAdapter()
        eventRecyclerView.adapter = adapter1
        displayEventsList(viewModel,adapter1, 12)

        eventRecyclerView2.layoutManager =  LinearLayoutManager(this)
        adapter2 = EventRecyclerViewAdapter()
        eventRecyclerView2.adapter = adapter2
        displayEventsList(viewModel2,adapter2, 8)
    }
    private fun saveEvent() { //create and store an event taken from data input
        val month = monthText.text.toString().toInt()
        val day = dayText.text.toString().toInt()
        val year = yearText.text.toString().toInt()
        val desc = descText.text.toString()
        val start = startText.text.toString().toInt()
        val end = endText.text.toString().toInt()
        val startMin = 0
        val endMin = 0
        val calNam = "main";
        val event = Event(0,year, month,day,desc, start, startMin, end, endMin, calNam);

        viewModel.insertEvent(event)

    }
    private fun clearInput() {
        monthText.setText("")
        dayText.setText("")
        yearText.setText("")
        descText.setText("")
        startText.setText("")
        endText.setText("")

    }
    private fun initRecyclerView(rcView: RecyclerView, vModel: EventViewModel, mon: Int){ //still figuring this out
        //eventRecyclerView.layoutManager = LinearLayoutManager(this)
        rcView.layoutManager = LinearLayoutManager(this)
        val adapter = EventRecyclerViewAdapter()
        rcView.adapter = adapter

        displayEventsList(vModel, adapter, mon)
    }
    private fun displayEventsList(vModel: EventViewModel, adapter: EventRecyclerViewAdapter, mon: Int){
        // can set what events appear in the recycler view by calling the function or entire list
        vModel.getEventsByMonth(mon).observe(this,{
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }
}