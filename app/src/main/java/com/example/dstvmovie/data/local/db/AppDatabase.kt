package com.example.dstvmovie.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dstvmovie.data.local.dao.MovieDao
import com.example.dstvmovie.data.model.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}