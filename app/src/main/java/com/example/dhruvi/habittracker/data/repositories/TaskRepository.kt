package com.example.dhruvi.habittracker.data.repositories

import com.example.dhruvi.habittracker.data.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insertTask(task: TaskEntity): Long
    suspend fun updateTask(task: TaskEntity)
    suspend fun deleteTask(task: TaskEntity)
    fun getAllTasks(): Flow<List<TaskEntity>>
    fun getTasksForDate(date: String): Flow<List<TaskEntity>>
    suspend fun getTaskById(id: Int): TaskEntity?
}