package com.burak.chatipia.network

import com.burak.chatipia.data.remote.Messages
import retrofit2.http.GET

/**
 * Created by mburak on 8.04.2021.
 */
interface ApiService {
    @GET("chatdata-example.json")
    suspend fun getMessages(): Messages
}