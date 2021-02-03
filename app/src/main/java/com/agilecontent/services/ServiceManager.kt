package com.agilecontent.services

import com.agilecontent.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object ServiceManager {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_SERVICE_URL)
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

}