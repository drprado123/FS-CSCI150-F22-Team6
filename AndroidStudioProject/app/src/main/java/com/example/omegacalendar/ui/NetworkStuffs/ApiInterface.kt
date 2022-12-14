package com.example.omegacalendar.ui.NetworkStuffs

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/api/webhooks/1042645972729204776/cj9-zSz_sazT-WT_STxjz5T9apTwHhNkvs7nvHX97VL0PJKgvcqXqy7YutyckmsOaPOb")
    fun sendReq(@Body requestModel: RequestModel) : Call<ResponseModel>
}
//https://discord.com/api/webhooks/1042645972729204776/cj9-zSz_sazT-WT_STxjz5T9apTwHhNkvs7nvHX97VL0PJKgvcqXqy7YutyckmsOaPOb