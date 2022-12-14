package com.example.omegacalendar.ui.NetworkStuffs

import com.example.omegacalendar.data.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun networkrequest(info: Event) { //this is the network request function it has to exist here in the main or it wont function
    val imageloc = "https://assets.stickpng.com/images/587185d57b7f6103e35c6cc7.png"
    val templateStatement = "Hello, ${info.desc} is starting at ${timeconverter(info)}. Don't miss it!"
    val requestModel = RequestModel("OmegaCalendarBot", templateStatement, imageloc)

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

fun timeconverter(event: Event): String{
    var time = event.startHour
    time /= 100
    var response = "temp"
    if ( time > 12){ response = " ${time-12} PM"}
    else {response = "${time} AM"}
    return response

}