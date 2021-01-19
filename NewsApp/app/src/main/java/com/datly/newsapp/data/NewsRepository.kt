package com.datly.newsapp.data

import com.datly.newsapp.data.endpoint.ApiService

class NewsRepository(private val apiService: ApiService) {

    suspend fun fetchDataSource() = apiService.fetchNewsDataSource()
}