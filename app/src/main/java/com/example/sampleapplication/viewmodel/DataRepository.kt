package com.example.sampleapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.sampleapplication.model.DetailsData
import com.example.sampleapplication.network.DataService
import com.example.sampleapplication.network.Resource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DataRepository {

    private val baseUrl = "https://mocki.io/v1/"

    private var apiService: DataService? = null
    private var apiResponseLiveData: MutableLiveData<Resource<List<DetailsData>>>? = null

    init {
        dataRepository()
    }

    private fun dataRepository() {
        apiResponseLiveData = MutableLiveData()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        apiService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DataService::class.java)
    }

    fun getDescriptionData() {
        apiResponseLiveData?.value = Resource.loading()

        apiService?.dataApi()?.enqueue(object : Callback<List<DetailsData>> {
            override fun onResponse(
                call: retrofit2.Call<List<DetailsData>>,
                response: Response<List<DetailsData>>
            ) {
                apiResponseLiveData?.value =
                    Resource.success(response.body(), response.message())
            }

            override fun onFailure(
                call: retrofit2.Call<List<DetailsData>>,
                t: Throwable
            ) {
                apiResponseLiveData?.value = Resource.error(t.localizedMessage)
            }

        })
    }

    fun getBirthdayList() {
        apiResponseLiveData?.value = Resource.loading()
        apiService?.listApi()?.enqueue(object : Callback<List<DetailsData>> {
            override fun onResponse(
                call: retrofit2.Call<List<DetailsData>>,
                response: Response<List<DetailsData>>
            ) {
                apiResponseLiveData?.value =
                    Resource.success(response.body(), response.message())
            }

            override fun onFailure(call: retrofit2.Call<List<DetailsData>>, t: Throwable) {
                apiResponseLiveData?.value = Resource.error(t.localizedMessage)
            }

        })
    }

    fun getMutableList(): MutableLiveData<Resource<List<DetailsData>>>? {
        return apiResponseLiveData
    }
}