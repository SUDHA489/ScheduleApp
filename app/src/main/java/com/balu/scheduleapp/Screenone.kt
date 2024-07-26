package com.balu.scheduleapp

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.balu.scheduleapp.presentation.ScheduleViewModel


@Composable
fun ScreenOne(viewModel: ScheduleViewModel,navController: NavController) {


    val branchOptions = listOf("CSE", "ECE","EEE","MECH", "CIVIL","CHEM")
    val yearOptions = listOf("1", "2", "3", "4")
    val semesterOptions = listOf("1", "2")
    val weekOptions = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
    val timeOptions = listOf("9.30Am-10.30Am","10.30Am-11.30Am","11.30Am-12.30Pm","1.30Pm-2.30Pm","2.30Pm-3.30Pm","3.30Pm-4.30Pm")

    var branch by remember{ mutableStateOf("") }

    var year by remember{ mutableStateOf("") }

    var semester by remember{ mutableStateOf("") }

    var week by remember{ mutableStateOf("") }

    var time by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
    ) {

        DropdownMenuComponent(
            label = "Select branch",
            options = branchOptions,
            selectedOption = branch,
            onOptionSelected = { branch = it }
        )

        DropdownMenuComponent(
            label = "Select year of study",
            options = yearOptions,
            selectedOption = year,
            onOptionSelected = { year = it }
        )

        DropdownMenuComponent(
            label = "Select semester number",
            options = semesterOptions,
            selectedOption = semester,
            onOptionSelected = { semester = it }
        )

        DropdownMenuComponent(
            label = "Select week day",
            options = weekOptions,
            selectedOption = week,
            onOptionSelected = { week = it }
        )
        DropdownMenuComponent(
            label = "Select Time",
            options = timeOptions,
            selectedOption = time,
            onOptionSelected = { time = it }
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                Log.d("values","output:${viewModel.userList.value}")
                viewModel.getDetails(branch,year,semester,week,time)
                      navController.navigate("results")},
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Search for results")
        }
    }
}