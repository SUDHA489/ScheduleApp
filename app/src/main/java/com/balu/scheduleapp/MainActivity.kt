package com.balu.scheduleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.balu.scheduleapp.data.AppDataBase
import com.balu.scheduleapp.presentation.ScheduleRepo
import com.balu.scheduleapp.presentation.ScheduleViewModel
import com.balu.scheduleapp.presentation.ScheduleViewModelFactory
import com.balu.scheduleapp.presentation.readAndInsertUsersFromCSV
import com.balu.scheduleapp.ui.theme.ScheduleAppTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDataBase.getDatabase(this)
        val repository = ScheduleRepo(database.scheduleDao())
        val viewModelFactory = ScheduleViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[ScheduleViewModel::class.java]
        setContent {
            ScheduleAppTheme{
                val coroutineScope = rememberCoroutineScope()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val navController = rememberNavController()
                val context = LocalContext.current.applicationContext
                LaunchedEffect(Unit) {
                    coroutineScope.launch {
                        readAndInsertUsersFromCSV(context, database)
                    }
                }
                ModalNavigationDrawer(
                    drawerState = drawerState,
                    gesturesEnabled = true,
                    drawerContent = {
                        ModalDrawerSheet {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .background(color = Color.LightGray)
                            ){
                                Text(text = "")
                            }
                            HorizontalDivider()
                            NavigationDrawerItem(
                                label = { Text(text = "Search By Room Number")},
                                selected = false,
                                onClick = {
                                    coroutineScope.launch {
                                        drawerState.close()
                                    }
                                    navController.navigate("query2"){
                                        popUpTo(0)
                                    }
                                }
                            )

                            NavigationDrawerItem(
                                label = { Text(text = "Search By Branch Name")},
                                selected = false,
                                onClick = {
                                    coroutineScope.launch {
                                        drawerState.close()
                                    }
                                    navController.navigate("query1"){
                                        popUpTo(0)
                                    }
                                }
                            )
                        }
                    }
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(title = {
                                Text(text = "Time Table App") },
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = Color.Blue,
                                    titleContentColor = Color.White,
                                    navigationIconContentColor = Color.White
                                ),
                                navigationIcon = {
                                    IconButton(onClick = {
                                        coroutineScope.launch {
                                            drawerState.open()
                                        }
                                    }) {
                                        Icon(
                                            Icons.Rounded.Menu,
                                            contentDescription = ""
                                        )
                                    }
                                }
                            )
                        }
                    ){it->
                        NavHost(
                            navController = navController,
                            startDestination = "Query1",
                            modifier = Modifier.padding(it)
                        ){
                            composable(route = "query1"){
                                ScreenOne(viewModel,navController)
                            }
                            composable(route = "query2"){
                                ScreenTwo(viewModel,navController)
                            }
                            composable(route = "results"){
                                ResultsScreen(viewModel,navController)
                            }
                        }
                    }
                }
            }
        }
    }
}