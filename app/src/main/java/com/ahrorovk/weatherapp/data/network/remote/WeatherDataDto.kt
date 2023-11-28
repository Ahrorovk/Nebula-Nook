package com.ahrorovk.weatherapp.data.network.remote

data class WeatherDataDto(
    val time: List<String> = emptyList(),
    val temperature_2m: List<Double> = emptyList(),
    val weathercode: List<Int> = emptyList(),
    val pressure_msl: List<Double> = emptyList(),
    val windspeed_10m: List<Double> = emptyList(),
    val relativehumidity_2m: List<Double> = emptyList()
)
