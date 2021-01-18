package com.datly.newsapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.datly.newsapp.R
import com.datly.newsapp.data.NewsRepositoryFactory
import com.datly.newsapp.data.model.Source
import com.datly.newsapp.data.network.NewsService
import com.datly.newsapp.data.network.NewsService.Companion.URL_LINK
import com.datly.newsapp.viewmodel.NewsViewModel
import com.datly.newsapp.viewmodel.NewsViewModelFactory
import kotlinx.android.synthetic.main.fragment_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsFragment: Fragment() {

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel = ViewModelProvider(this, NewsViewModelFactory(NewsRepositoryFactory.buildNewsRepository()))
            .get(NewsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    override fun onResume() {
        super.onResume()
        observeDataSource()
    }

    override fun onPause() {
        super.onPause()
        removeObserver()
    }

    private fun setUpView() {
        newsViewModel.getSource()
    }

    private fun observeDataSource() {
        newsViewModel.sourceLiveData.observe(this, Observer {
            fetchDataSource(it)
        })
    }

    private fun removeObserver() {
        newsViewModel.sourceLiveData.removeObservers(this)
    }

    private fun fetchDataSource(dataSource: Source) {
        dataSource.data
            .filter { item -> item.type.equals("section", ignoreCase = true) }
            .forEach { text_view.append(it.type)
                        text_view.append(System.lineSeparator()) }
    }

}