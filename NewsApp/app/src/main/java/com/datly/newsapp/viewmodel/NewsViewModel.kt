package com.datly.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.datly.newsapp.data.NewsRepository
import com.datly.newsapp.data.model.Result
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class NewsViewModel(
    private val newsRepository: NewsRepository
): ViewModel() {

    fun fetchDataSource() = liveData(Dispatchers.IO) {
        emit(Result.loading(data = null))
        try {
            emit(Result.success(
                data = newsRepository.fetchDataSource().data
                    .filter { category -> category.type.equals("section", ignoreCase = true) }
                    .map { item -> item.items }
                    .flatten()
            ))
        } catch (exception: Exception) {
            emit(Result.failed(data = null, message = exception.message ?: "Error Loading Data!!!!"))
        }
    }
}