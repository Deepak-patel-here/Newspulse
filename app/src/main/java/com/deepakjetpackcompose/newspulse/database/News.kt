package com.deepakjetpackcompose.newspulse.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class News(
    @PrimaryKey
    val id: Int,
    val title:String,
    val description:String,
    val published:String,
    val imgUrl:String?
)
