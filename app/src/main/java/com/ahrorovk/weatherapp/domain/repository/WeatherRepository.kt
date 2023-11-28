package com.ahrorovk.weatherapp.domain.repository

import com.ahrorovk.weatherapp.data.network.remote.WeatherDto

interface WeatherRepository {
    suspend fun getWeather(
        lat: Double,
        long: Double,
        tempUnit: String
    ): WeatherDto
}