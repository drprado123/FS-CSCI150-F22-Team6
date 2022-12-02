package com.example.omegacalendar.ui.NetworkStuffs

import com.example.omegacalendar.data.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


fun networkrequest(info: Event) { //this is the network request function it has to exist here in the main or it wont function
    val imageloc = "https://assets.stickpng.com/images/587185d57b7f6103e35c6cc7.png"
    val requestModel = RequestModel("OmegaCalendarBot", info.desc, imageloc)

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