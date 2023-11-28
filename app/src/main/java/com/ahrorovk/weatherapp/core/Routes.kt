package com.ahrorovk.weatherapp.core

sealed class Routes(val route: String){
    object WeatherScreen : Routes("WeatherScreen")
}
