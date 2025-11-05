package com.example.dhruvi.habittracker.presentation.tasks

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.dhruvi.habittracker.data.entities.FrequencyType
import com.example.dhruvi.habittracker.data.entities.TaskEntity
import com.example.dhruvi.habittracker.domain.viewModel.TaskViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    viewModel: TaskViewModel = hiltViewModel(),
    onTaskSaved: () -> Unit
) {
    val context = LocalContext.current

    var name by remember { mutableStateOf(TextFieldValue("")) }
    var selectedFrequency by remember { mutableStateOf(FrequencyType.DAILY) }
    var durationMinutes by remember { mutableStateOf(15) }
    var startTime by remember { mutableStateOf("12:30") }
    var endTime by remember { mutableStateOf("12:45") }

    val calendar = Calendar.getInstance()

    fun showTimePicker(isStart: Boolean) {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(context, { _, h, m ->
            val formatted = String.format("%02d:%02d", h, m)
            if (isStart) startTime = formatted else endTime = formatted
        }, hour, minute, false).show()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("New Task") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val task = TaskEntity(
                    name = name.text,
                    frequency = selectedFrequency,
                    durationMinutes = durationMinutes,
                    startTime = startTime,
                    endTime = endTime,
                    startDate = "2025-10-28"
                )
                viewModel.insertTask(task)
                onTaskSaved()
            }) {
                Text("✓")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text("Task name:")
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("e.g. Short walk outside") },
                modifier = Modifier.fillMaxWidth()
            )

            Text("How often?")
            FrequencySelector(
                selected = selectedFrequency,
                onSelected = { selectedFrequency = it }
            )

            Text("How long?")
            DurationSelector(
                selectedMinutes = durationMinutes,
                onSelected = { durationMinutes = it }
            )

            Text("When?")
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(onClick = { showTimePicker(true) }) {
                    Text("Start: $startTime")
                }
                OutlinedButton(onClick = { showTimePicker(false) }) {
                    Text("End: $endTime")
                }
            }
        }
    }
}

@Composable
fun FrequencySelector(selected: FrequencyType, onSelected: (FrequencyType) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        FrequencyType.values().forEach { type ->
            FilterChip(
                selected = selected == type,
                onClick = { onSelected(type) },
                label = { Text(type.name.lowercase().replaceFirstChar { it.uppercase() }) }
            )
        }
    }
}

@Composable
fun DurationSelector(selectedMinutes: Int, onSelected: (Int) -> Unit) {
    val options = listOf(1, 15, 30, 45, 60)
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        options.forEach { minutes ->
            FilterChip(
                selected = selectedMinutes == minutes,
                onClick = { onSelected(minutes) },
                label = { Text("${minutes}m") }
            )
        }
    }
}