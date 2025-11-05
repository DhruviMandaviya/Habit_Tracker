package com.example.dhruvi.habittracker.domain.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dhruvi.habittracker.data.entities.TaskEntity
import com.example.dhruvi.habittracker.data.repositories.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _tasks = MutableStateFlow<List<TaskEntity>>(emptyList())
    val tasks: StateFlow<List<TaskEntity>> = _tasks.asStateFlow()

    private val _selectedDate = MutableStateFlow<String?>(null)
    val selectedDate: StateFlow<String?> = _selectedDate.asStateFlow()

    init {
        // Observe all tasks by default
        observeAllTasks()
    }

    fun setDate(date: String) {
        _selectedDate.value = date
        observeTasksForDate(date)
    }

    private fun observeAllTasks() {
        viewModelScope.launch {
            repository.getAllTasks().collect { list ->
                _tasks.value = list
            }
        }
    }

    private fun observeTasksForDate(date: String) {
        viewModelScope.launch {
            repository.getTasksForDate(date).collect { list ->
                _tasks.value = list
            }
        }
    }

    fun insertTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }

    fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}