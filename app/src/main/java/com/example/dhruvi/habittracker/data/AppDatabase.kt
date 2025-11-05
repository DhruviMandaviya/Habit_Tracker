package com.example.dhruvi.habittracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dhruvi.habittracker.data.dao.TaskDao
import com.example.dhruvi.habittracker.data.entities.Converters
import com.example.dhruvi.habittracker.data.entities.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}