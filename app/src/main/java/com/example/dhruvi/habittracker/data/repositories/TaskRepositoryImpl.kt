package com.example.dhruvi.habittracker.data.repositories

import com.example.dhruvi.habittracker.data.dao.TaskDao
import com.example.dhruvi.habittracker.data.entities.TaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {

    override suspend fun insertTask(task: TaskEntity): Long = taskDao.insertTask(task)

    override suspend fun updateTask(task: TaskEntity) = taskDao.updateTask(task)

    override suspend fun deleteTask(task: TaskEntity) = taskDao.deleteTask(task)

    override fun getAllTasks(): Flow<List<TaskEntity>> = taskDao.getAllTasksFlow()

    override fun getTasksForDate(date: String): Flow<List<TaskEntity>> =
        taskDao.getTasksForDate(date)

    override suspend fun getTaskById(id: Int): TaskEntity? = taskDao.getTaskById(id)
}