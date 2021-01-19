package com.datly.newsapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.datly.newsapp.R
import com.datly.newsapp.data.model.News
import com.squareup.picasso.Picasso

class NewsScreenAdapter(private val newsList: ArrayList<News>)
    : RecyclerView.Adapter<NewsScreenAdapter.NewsViewHolder>() {

    override fun getItemCount() = newsList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.news_screen_item, parent, false)
        return NewsViewHolder(itemView)
    }

    override fun getItemViewType(position: Int) = position
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addNewsList(newsList: List<News>) {
        this.newsList.apply {
            clear()
            addAll(newsList)
        }
    }

    inner class NewsViewHolder(
        view: View
    ): RecyclerView.ViewHolder(view) {
        lateinit var newsImage: ImageView
        lateinit var newsHeadline: TextView

        fun onBind(position: Int) {
            newsImage = itemView.findViewById(R.id.news_image)
            newsHeadline = itemView.findViewById(R.id.news_headline)

            Picasso.get().load(newsList[position].tease).into(newsImage)
            newsHeadline.text = newsList[position].headline
        }
    }
}