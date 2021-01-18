package com.datly.newsapp.data

import com.datly.newsapp.data.network.NewsService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NewsRepositoryFactory {

    fun buildNewsRepository(): NewsRepository {
        val newsApi = createRestApi().create(NewsService::class.java)
        return NewsRepository(newsApi)
    }

    private fun createRestApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NewsService.URL_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}