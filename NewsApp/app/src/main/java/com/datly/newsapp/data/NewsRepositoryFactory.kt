package com.datly.newsapp.data

import com.datly.newsapp.data.endpoint.ApiService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NewsRepositoryFactory {

    fun buildNewsRepository(): NewsRepository {
        val apiService = createRestApi().create(ApiService::class.java)
        return NewsRepository(apiService)
    }

    private fun createRestApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiService.URL_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}