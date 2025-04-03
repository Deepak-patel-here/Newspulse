package com.deepakjetpackcompose.newspulse.news.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Articles(
    val id: String,
    val title: String,
    val description: String,
    val url: String,
    val author: String? = null,
    val image: String? = null,
    val language: String,
    val category: List<String>,
    val published: String
)
