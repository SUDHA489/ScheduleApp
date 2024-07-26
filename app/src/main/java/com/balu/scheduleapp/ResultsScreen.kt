package com.balu.scheduleapp

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.balu.scheduleapp.presentation.ScheduleViewModel

@Composable
fun ResultsScreen(viewModel: ScheduleViewModel, navController: NavController){

    val userList by viewModel.userList.observeAsState(initial = emptyList())
    if (userList?.isEmpty() == true) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ){
            Text("No Classes Found")
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
        ) {
            items(userList){it->
                Card(modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .padding(15.dp)
                    .fillMaxWidth()) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Class Room Number:"+it.roomNumber)
                        Text(text = "Lecture Name:"+it.lectureName)
                        Text(text = "Subject Name:"+it.subject)
                        Text(text = "Time :"+it.time)
                    }
                }
            }
        }
    }
}