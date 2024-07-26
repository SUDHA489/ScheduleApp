package com.balu.scheduleapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balu.scheduleapp.data.ClassSchedule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ScheduleViewModel( private val repo :ScheduleRepo):ViewModel(){

    private val _userList = MutableLiveData<List<ClassSchedule>>()
    val userList: LiveData<List<ClassSchedule>> get() = _userList


    fun insertUser(period:ClassSchedule) {
        viewModelScope.launch {
            repo.insert(period)
        }
    }


    fun getDetails(branch: String,year:String, sem: String, week: String,time:String){
        _userList.value= emptyList()
        viewModelScope.launch {
            _userList.value = repo.getDetails(branch,year,sem,week,time)
        }
    }

    fun getByRoom(room: String, semester: String, week: String,time:String) {
        _userList.value= emptyList()
        viewModelScope.launch {
            _userList.value = repo.getByRoom(room,semester,week,time)
        }
    }
}