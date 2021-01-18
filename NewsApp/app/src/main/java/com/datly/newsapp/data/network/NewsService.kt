package com.datly.newsapp.data.network

import com.datly.newsapp.data.model.Source
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {

    companion object {
        const val URL_LINK = "https://s3.amazonaws.com/shrekendpoint/"
    }

    @GET("news.json")
    fun fetchNewsDataSource(): Observable<Response<Source>>
}