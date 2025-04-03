package com.deepakjetpackcompose.newspulse.news.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
    val status: String,
    val news: List<Articles>
)
