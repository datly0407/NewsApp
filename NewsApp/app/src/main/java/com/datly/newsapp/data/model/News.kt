package com.datly.newsapp.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class News(
    val tease: String,
    val headline: String,
    val summary: String
)

@JsonClass(generateAdapter = true)
data class Category(
    val type: String,
    val items: List<News>
)

@JsonClass(generateAdapter = true)
data class Source(
    val data: List<Category>
)