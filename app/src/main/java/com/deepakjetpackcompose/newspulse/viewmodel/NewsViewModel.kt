package com.deepakjetpackcompose.newspulse.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deepakjetpackcompose.newspulse.database.News
import com.deepakjetpackcompose.newspulse.database.NewsDb
import com.deepakjetpackcompose.newspulse.news.client.ApiClient
import com.deepakjetpackcompose.newspulse.news.model.NewsResponse
import com.google.android.gms.common.api.Api
import io.ktor.utils.io.printStack
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class NewsViewModel(application: Application): AndroidViewModel(application) {
    private val newsDao= NewsDb.getDb(application).getNewsDao()
    val news: LiveData<List<News>>  =newsDao.getAllNews()

    private val _categoryList= MutableLiveData<List<News>>()
    val categoryList: LiveData<List<News>> = _categoryList

    private val _isLoading= MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>  =_isLoading

    fun fetchAllNews(){
        _isLoading.value=true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.fetchNews()
                response?.let { apiResponse ->
                    val article = apiResponse.news.map {
                        News(
                            id = it.title.hashCode(),
                            title = it.title,
                            description = it.description,
                            imgUrl = it.image,
                            published = it.published

                        )
                    }
                    newsDao.insertArticle(article)

                }
            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                _isLoading.postValue(false)
            }

        }

    }

    fun fetchNewsCategory(category: String){
        viewModelScope.launch (Dispatchers.IO){
            try {
                val response= ApiClient.fetchByCategory(category = category)
                Log.d("API_RESPONSE", "Fetched Data: $response")
                response?.let { apiResponse->
                    val categoryArticle=apiResponse.news.map {
                        News(
                            id = it.title.hashCode(),
                            title = it.title,
                            description = it.description,
                            imgUrl = it.image,
                            published = it.published
                        )
                    }
                    _categoryList.postValue(categoryArticle)
                }

            }catch (e: Exception){
                e.printStackTrace()
            }finally {
                _isLoading.postValue(false)
            }
        }

    }

}

