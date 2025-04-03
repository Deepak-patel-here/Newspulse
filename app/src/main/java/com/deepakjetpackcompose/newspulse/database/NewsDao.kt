package com.deepakjetpackcompose.newspulse.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {


    @Insert
    suspend fun insertArticle(news: List<News>);

    @Query("SELECT * FROM news_table ORDER BY published DESC")
    fun getAllNews(): LiveData<List<News>>

    @Query("DELETE FROM news_table")
    suspend fun clearArticles()

}