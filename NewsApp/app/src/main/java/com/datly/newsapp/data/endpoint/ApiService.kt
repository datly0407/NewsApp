package com.datly.newsapp.data.endpoint

import com.datly.newsapp.data.model.Source
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val URL_LINK = "https://s3.amazonaws.com/shrekendpoint/"
    }

    @GET("news.json")
    suspend fun fetchNewsDataSource(): Source
}