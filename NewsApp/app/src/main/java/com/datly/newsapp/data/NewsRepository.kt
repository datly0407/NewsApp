package com.datly.newsapp.data

import android.util.Log
import com.datly.newsapp.data.model.Source
import com.datly.newsapp.data.network.NewsService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsRepository(private val newsService: NewsService) {

    fun fetchNewsDataSource(): Observable<Source> {
        return Observable.create { emitter ->
            newsService.fetchNewsDataSource()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.body() != null) {
                        emitter.onNext(it.body() as Source)
                    }
                }, {
                    it.printStackTrace()
                })

        }
    }
}