package com.example.omegacalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.omegacalendar.ui.theme.OmegaCalendarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OmegaCalendarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OmegaCalendarTheme {
        Greeting("Android")
    }
<<<<<<< Updated upstream
=======
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
>>>>>>> Stashed changes
}