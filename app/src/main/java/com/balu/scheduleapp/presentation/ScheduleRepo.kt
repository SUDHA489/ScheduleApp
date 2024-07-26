package com.balu.scheduleapp.presentation

import com.balu.scheduleapp.data.ClassSchedule
import com.balu.scheduleapp.data.ScheduleDao

class ScheduleRepo(private val scheduleDao: ScheduleDao) {


    suspend fun insert(period: ClassSchedule)= scheduleDao.insert(period)
    suspend fun getDetails(branch:String,year:String,sem:String,week:String,time:String): List<ClassSchedule> {
        return scheduleDao.getDetails(branch,year,sem,week,time)
    }
    suspend fun getByRoom(room: String, sem: String, week: String,time:String): List<ClassSchedule> {
        return scheduleDao.getByRoom(room,sem,week,time)
    }
}