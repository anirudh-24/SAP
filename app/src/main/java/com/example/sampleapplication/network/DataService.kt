package com.example.sampleapplication.network

import com.example.sampleapplication.model.DetailsData
import retrofit2.Call
import retrofit2.http.GET

interface DataService {
    @GET("99066355-8f5e-4c9d-b400-d5bdf26911b6")
    fun dataApi(): Call<List<DetailsData>>

    @GET("bbac0c66-3f2f-41b2-b3a6-acbae212513e")
    fun listApi(): Call<List<DetailsData>>
}