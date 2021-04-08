package com.burak.chatipia.network

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Created by mburak on 8.04.2021.
 */
class NetworkManager {
    companion object {
        fun getRetrofit(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://media.meditopia.com/files/") // chatdata-example.json
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}