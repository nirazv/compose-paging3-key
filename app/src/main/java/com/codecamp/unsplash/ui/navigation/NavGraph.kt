package com.codecamp.unsplash.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.codecamp.unsplash.ui.screen.home.HomeScreen
import com.codecamp.unsplash.ui.screen.SearchScreen



fun NavigationUI() {

    val home: () -> Unit = {

    }

    val screen: () -> Unit = {

    }
}

@Composable
fun SetupNavigation(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }

        composable(route = Screen.Search.route) {
            SearchScreen()
        }
    }
}