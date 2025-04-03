package com.deepakjetpackcompose.newspulse.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [News::class], version = 1, exportSchema = false)
abstract class NewsDb: RoomDatabase() {
    abstract fun getNewsDao(): NewsDao

    companion object{
        @Volatile
        private var INSTANCE: NewsDb?=null
        fun getDb(context: Context): NewsDb{
            return INSTANCE?: synchronized(this) {
                val instance=Room.databaseBuilder(context = context.applicationContext, NewsDb::class.java,"news_database").build()
                INSTANCE=instance
                instance
            }
        }
    }
}