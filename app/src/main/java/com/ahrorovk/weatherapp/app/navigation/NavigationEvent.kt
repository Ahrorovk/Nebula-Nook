package com.ahrorovk.weatherapp.app.navigation

sealed class NavigationEvent {
    data class GetWeather(val state: () -> Unit) : NavigationEvent()
}
