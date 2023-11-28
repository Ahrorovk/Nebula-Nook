package com.ahrorovk.weatherapp.core.models

import com.ahrorovk.weatherapp.domain.weather.model.WeatherType

data class CompressedWeatherData(
    val time: String,
    val weatherType: WeatherType,
    val temperature: Double
)
