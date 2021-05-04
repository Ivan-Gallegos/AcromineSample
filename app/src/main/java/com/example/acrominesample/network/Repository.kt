package com.example.acrominesample.network

import com.example.acrominesample.models.ResponseItem
import retrofit2.Call

class Repository {

    fun callSf(abbreviation: String): Call<List<ResponseItem>> {
        return AcromineApi.acromineApi.callSf(abbreviation)
    }
}