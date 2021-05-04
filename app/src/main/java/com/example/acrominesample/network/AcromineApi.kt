package com.example.acrominesample.network

import com.example.acrominesample.models.ResponseItem
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AcromineApi {

    @GET("/software/acromine/dictionary.py")
    fun callSf(@Query("sf") abbreviation: String?): Call<List<ResponseItem>>

    companion object {
        private var BASE_URL = "http://www.nactem.ac.uk"

        private val okHttpClient by lazy {
            val clientBuilder = OkHttpClient.Builder()
            val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(interceptor)
            clientBuilder.build()
        }

        val acromineApi: AcromineApi by lazy {
            Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build().create(AcromineApi::class.java)
        }
    }
}