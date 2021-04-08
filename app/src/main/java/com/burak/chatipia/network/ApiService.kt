package com.burak.chatipia.network

import com.burak.chatipia.data.Message
import com.burak.chatipia.data.Messages
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
/**
 * Created by mburak on 8.04.2021.
 */
interface ApiService {
    @GET("chatdata-example.json")
    fun getMessages(): Call<Messages>
}