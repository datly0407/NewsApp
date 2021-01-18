package com.datly.newsapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.datly.newsapp.data.NewsRepository
import com.datly.newsapp.data.model.Source

class NewsViewModel(private val newsRepository: NewsRepository): ViewModel() {

    private val _sourceLiveData: MutableLiveData<Source> = MutableLiveData()
    val sourceLiveData: LiveData<Source> = _sourceLiveData

    @SuppressLint("CheckResult")
    fun getSource() {
        newsRepository
            .fetchNewsDataSource()
            .subscribe( {
                _sourceLiveData.postValue(it)
            }, {throwable->
                throwable.printStackTrace()
            })
    }
}