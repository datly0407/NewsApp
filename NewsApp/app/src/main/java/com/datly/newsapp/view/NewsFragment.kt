package com.datly.newsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.datly.newsapp.R
import com.datly.newsapp.data.model.News
import com.datly.newsapp.data.model.Result.Status.SUCCESS
import com.datly.newsapp.data.model.Result.Status.LOADING
import com.datly.newsapp.data.model.Result.Status.FAILED
import com.datly.newsapp.network.Network
import com.datly.newsapp.network.NetworkStatus.NO_CONNECTION
import com.datly.newsapp.network.NetworkStatus.CONNECTED
import com.datly.newsapp.util.ScreenUtilImpl
import com.datly.newsapp.view.adapter.NewsScreenAdapter
import com.datly.newsapp.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment: Fragment() {

    private val newsViewModel: NewsViewModel by viewModel()
    private val networkUtil: Network by inject()
    private lateinit var adapter: NewsScreenAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onResume() {
        super.onResume()
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
                        news_screen_progress_bar.visibility = View.INVISIBLE
                        fail_connection_message.visibility = View.INVISIBLE
                    }
                    FAILED -> {
                        when (networkUtil.isNetworkConnected(requireActivity())) {
                            CONNECTED -> {
                                news_screen_progress_bar.visibility = View.VISIBLE
                                fail_connection_message.visibility = View.INVISIBLE
                                Toast.makeText(context, result.message, Toast.LENGTH_LONG).show()
                            }

                            NO_CONNECTION -> {
                                news_screen_progress_bar.visibility = View.INVISIBLE
                                fail_connection_message.visibility = View.VISIBLE
                            }
                        }
                    }
                    LOADING -> {
                        news_screen_progress_bar.visibility = View.VISIBLE
                    }
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