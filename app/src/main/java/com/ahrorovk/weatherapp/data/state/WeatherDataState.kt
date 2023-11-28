package com.ahrorovk.weatherapp.data.state

import com.ahrorovk.weatherapp.domain.weather.model.WeatherInfo

data class WeatherDataState(
    val isLoading: Boolean = false,
    val response: WeatherInfo? = null,
    val error: String = ""
)
