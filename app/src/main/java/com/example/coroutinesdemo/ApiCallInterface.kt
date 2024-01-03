package com.example.coroutinesdemo

import com.example.coroutinesdemo.WebConstants.Companion.POSTS
import retrofit2.http.GET
import retrofit2.Response

interface ApiCallInterface {

    @GET(POSTS)
    suspend fun getData(): List<DataItems>

}