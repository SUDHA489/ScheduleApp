package com.balu.scheduleapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ScheduleViewModelFactory(private val repository: ScheduleRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ScheduleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}