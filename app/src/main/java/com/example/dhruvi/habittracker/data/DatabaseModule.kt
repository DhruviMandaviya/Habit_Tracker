package com.example.dhruvi.habittracker.data

import android.content.Context
import androidx.room.Room
import com.example.dhruvi.habittracker.data.dao.TaskDao
import com.example.dhruvi.habittracker.data.entities.Converters
import com.example.dhruvi.habittracker.data.repositories.TaskRepository
import com.example.dhruvi.habittracker.data.repositories.TaskRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext ctx: Context): AppDatabase {
        return Room.databaseBuilder(ctx, AppDatabase::class.java, "habit_task_db")
            .addTypeConverter(Converters()) // if using Room 2.5+ with addTypeConverter
            .fallbackToDestructiveMigration() // optional during dev
            .build()
    }

    @Provides
    fun provideTaskDao(db: AppDatabase): TaskDao = db.taskDao()

    @Singleton
    @Provides
    fun provideTaskRepository(dao: TaskDao): TaskRepository = TaskRepositoryImpl(dao)
}