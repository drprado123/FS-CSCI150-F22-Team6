package com.example.omegacalendar

import android.content.Context
import android.icu.util.GregorianCalendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.omegacalendar.data.AppDatabase
import com.example.omegacalendar.data.EventViewModel
import com.example.omegacalendar.data.EventViewModelFactory
import com.example.omegacalendar.ui.MonthComponent
import com.example.omegacalendar.ui.theme.OmegaCalendarTheme
//import com.example.omegacalendar.data.Event
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import com.example.omegacalendar.data.AppDatabase

class MainActivity : ComponentActivity() {
    networkrequest("hello this is a main test")
    private lateinit var viewModel : EventViewModel
    init {
        instance = this
    }

    companion object {
        private var instance: MainActivity? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val context: Context = MainActivity.applicationContext()
        val dao = AppDatabase.getInstance(application).eventDao()
        //factory used to instantiate and pass dao to view model
        val factory = EventViewModelFactory(dao)
        super.onCreate(savedInstanceState)
        setContent {
            OmegaCalendarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //val rightNow = GregorianCalendar.getInstance()

                    OmegaCalendarApp(viewModel = ViewModelProvider(this, factory).get(EventViewModel::class.java))//MonthComponent()//(rightNow as GregorianCalendar)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OmegaCalendarTheme {

    }
}

fun networkrequest(info: String){ //this is the network request function it has to exist here in the main or it wont function
    //val template = "Hello you have event {$Event.description}, at {$Event.time}"
    val imageloc = "https://assets.stickpng.com/images/587185d57b7f6103e35c6cc7.png"
    val requestModel = RequestModel("OmegaCalendarBot", info,imageloc)

    val response = ServiceBuilder.buildService(ApiInterface::class.java)
    response.sendReq(requestModel).enqueue(
        object : Callback<ResponseModel> {
            override fun onResponse(
                call: Call<ResponseModel>,
                response: Response<ResponseModel>
            ) {
                return
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                return
            }

        }
    )
}