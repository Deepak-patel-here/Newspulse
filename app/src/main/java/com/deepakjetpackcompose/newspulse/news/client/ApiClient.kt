package com.deepakjetpackcompose.newspulse.news.client

import com.deepakjetpackcompose.newspulse.news.model.NewsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get

import io.ktor.client.statement.HttpResponse

import io.ktor.serialization.kotlinx.json.json

import kotlinx.serialization.json.Json

object ApiClient {

    const val BASE_URL ="https://api.currentsapi.services/v1/latest-news"
    const val API_KEY = "Add your own api key to run i use the current api key"
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(json = Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun fetchNews(): NewsResponse? {
        return try {
            val response: HttpResponse = client.get("https://api.currentsapi.services/v1/latest-news") {
                url {
                    parameters.append("apiKey", API_KEY)
                }
            }
            if (response.status.value == 200) {
                response.body<NewsResponse>()
            } else {
                println("Error: ${response.status}")
                null
            }
        } catch (e: Exception) {
            println("Exception: ${e.localizedMessage}")
            null
        }
    }

    suspend fun fetchByCategory(category:String): NewsResponse?{
        return try {
            val response: HttpResponse = client.get("https://api.currentsapi.services/v1/latest-news") {
                url {
                    parameters.append("apiKey", API_KEY)
                    parameters.append("category", category)
                }
            }
            if (response.status.value == 200) {
                response.body<NewsResponse>()
            } else {
                println("Error: ${response.status}")
                null
            }
        } catch (e: Exception) {
            println("Exception: ${e.localizedMessage}")
            null
        }
    }
}



//suspend fun main() {
//    val newsResponse = ApiClient.fetchByCategory(category = "technology")
//    newsResponse?.news?.forEach {
//        println("Title: ${it.title}")
//        println("Description: ${it.description}")
//        println("URL: ${it.url}")
//        println("Published: ${it.published}")
//
//        println("-----")
//    }
//}