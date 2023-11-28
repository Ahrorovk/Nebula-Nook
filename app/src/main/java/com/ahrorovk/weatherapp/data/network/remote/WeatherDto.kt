package com.ahrorovk.weatherapp.data.network.remote

data class WeatherDto(
    val hourly: WeatherDataDto,
    val error: String
)
