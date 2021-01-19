package com.datly.newsapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.datly.newsapp.R
import com.datly.newsapp.data.NewsRepositoryFactory
import com.datly.newsapp.data.model.News
import com.datly.newsapp.data.model.Result.Status.SUCCESS
import com.datly.newsapp.data.model.Result.Status.LOADING
import com.datly.newsapp.data.model.Result.Status.FAILED
import com.datly.newsapp.util.ScreenUtilImpl
import com.datly.newsapp.view.adapter.NewsScreenAdapter
import com.datly.newsapp.viewmodel.NewsViewModel
import com.datly.newsapp.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment: Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var adapter: NewsScreenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsViewModel = ViewModelProvider(this,
            ViewModelFactory(NewsRepositoryFactory.buildNewsRepository()))
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

    private fun setUpView() {
        val layoutManager: GridLayoutManager = when (ScreenUtilImpl.getInstance().isTablet(requireActivity())) {
            true -> GridLayoutManager(context, 3)
            else -> GridLayoutManager(context, 2)
        }
        rv_news.layoutManager = layoutManager
        adapter = NewsScreenAdapter(arrayListOf())
        rv_news.adapter = adapter

        newsViewModel.fetchDataSource().observe(requireActivity(), Observer {
            it?.let { result ->
                when (result.status) {
                    SUCCESS -> {
                        result.data?.let { newsList -> retrieveNewsList(newsList) }
                    }
                    FAILED -> {
                        Log.d("DLTEST", "${result.message}")
                    }
                    LOADING -> {}
                }
            }
        })
    }

    private fun retrieveNewsList(newsList: List<News>) {

        adapter.apply {
            addNewsList(newsList)
            notifyDataSetChanged()
        }
    }
}