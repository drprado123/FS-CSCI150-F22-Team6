package com.example.omegacalendar

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("/api/webhooks/1042640071779680327/nfJauDqQ8BWP5iFIckpXrcBqqH_y50EE2VQdEfVLBONy4-SgN4IqKY8OL0tTNj8GgHlc")
    fun sendReq(@Body requestModel: RequestModel) : Call<ResponseModel>
}