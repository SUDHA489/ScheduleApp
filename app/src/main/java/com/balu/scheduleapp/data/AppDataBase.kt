package com.balu.scheduleapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ClassSchedule::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun scheduleDao():ScheduleDao

    companion object {
        @Volatile private var instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDataBase::class.java, "app_database")
                .build()
    }
}