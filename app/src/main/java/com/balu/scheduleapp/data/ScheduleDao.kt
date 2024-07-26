package com.balu.scheduleapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ScheduleDao {

    @Query("SELECT COUNT(*) FROM class_schedule")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(period: ClassSchedule)

    @Query("select distinct * from class_schedule" +
            " where branchName=:branch and " +
            "yearOfStudy=:year and " +
            "semesterNumber= :sem and " +
            "dayOfWeek = :week and " +
            "time =:time"
    )
    suspend fun getDetails(branch:String,year:String,sem:String,week:String,time:String):List<ClassSchedule>

    @Query("select distinct * from class_schedule " +
            "where roomNumber=:room and " +
            "semesterNumber=:sem and " +
            "dayOfWeek=:week and "+
            "time =:time"
    )
    suspend fun getByRoom(room: String, sem: String, week: String,time:String):List<ClassSchedule>
}