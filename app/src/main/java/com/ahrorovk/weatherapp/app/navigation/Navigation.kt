package com.ahrorovk.weatherapp.app.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ahrorovk.weatherapp.app.Permission
import com.ahrorovk.weatherapp.core.Routes
import com.ahrorovk.weatherapp.presentation.weatherScreen.WeatherEvent
import com.ahrorovk.weatherapp.presentation.weatherScreen.WeatherScreen
import com.ahrorovk.weatherapp.presentation.weatherScreen.WeatherViewModel

@SuppressLint("NewApi")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.WeatherScreen.route,
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ) {
        composable(Routes.WeatherScreen.route) {
            val viewModel = hiltViewModel<WeatherViewModel>()
            val state = viewModel.state.collectAsState()
            Permission {
                /*
                 * Getting current location after getting permission for it
                 * Call function getWeather(it is inside of this event)
                 */
                viewModel.onEvent(WeatherEvent.OnLocationChange)
            }
            WeatherScreen(
                state = state.value
            ) { event ->
                when (event) {
                    else -> viewModel.onEvent(event)
                }
            }
        }
    }
}