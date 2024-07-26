package com.balu.scheduleapp.presentation

import android.content.Context
import com.balu.scheduleapp.R
import com.balu.scheduleapp.data.AppDataBase
import com.balu.scheduleapp.data.ClassSchedule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

fun readAndInsertUsersFromCSV(context: Context, database: AppDataBase) {
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val count = database.scheduleDao().getCount()
            if (count == 0) {
                val inputStream = context.resources.openRawResource(R.raw.periods)
                val bufferedReader = BufferedReader(InputStreamReader(inputStream))
                bufferedReader.useLines { lines ->
                    lines.drop(1).forEach { line ->
                        val tokens = line.split(",")
                        val user = ClassSchedule(
                            roomNumber = tokens[0],
                            stream = tokens[1],
                            branchName = tokens[2],
                            lectureName = tokens[3],
                            time = tokens[4],
                            subject = tokens[5],
                            dayOfWeek = tokens[6],
                            semesterNumber = tokens[7],
                            yearOfStudy = tokens[8]
                        )
                        database.scheduleDao().insert(user)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
