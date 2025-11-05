package com.example.dhruvi.habittracker.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.LocalTime

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val description: String? = null,

    val frequency: FrequencyType = FrequencyType.DAILY, // ONCE, DAILY, WEEKLY, MONTHLY

    // weekly/monthly selections
    val daysOfWeek: List<String>? = null, // ["Mon","Thu"] - use converter
    val daysOfMonth: List<Int>? = null,   // [12,22] - use converter

    // duration in minutes (nullable if unspecified)
    val durationMinutes: Int? = null, // e.g. 15

    // times stored as "HH:mm" (easier for DB + display)
    val startTime: String = "",   // e.g. "12:30"
    val endTime: String = "",     // e.g. "12:45"

    // start date for recurring tasks, stored as "yyyy-MM-dd"
    val startDate: String = "",   // e.g. "2025-10-28"

    val isReminderSet: Boolean = false,
    val reminderTime: LocalTime? = null, // e.g. "12:30"

    val isCompleted: Boolean = false,
    val iconName: String? = null,     // optional icon identifier

    val createdAt: String = LocalDateTime.now().toString(),
    val updatedAt: String? = null
)

enum class FrequencyType {
    ONCE, DAILY, WEEKLY, MONTHLY
}

