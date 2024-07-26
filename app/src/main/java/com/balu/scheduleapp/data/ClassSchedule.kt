package com.balu.scheduleapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "class_schedule")
data class ClassSchedule(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val roomNumber: String,
    val stream: String,
    val branchName: String,
    val lectureName: String,
    val time: String,
    val subject: String,
    val dayOfWeek: String,
    val semesterNumber: String,
    val yearOfStudy: String
)