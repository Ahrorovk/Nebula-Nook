package com.ahrorovk.weatherapp.data.network

import com.ahrorovk.weatherapp.data.network.remote.WeatherApi
import com.ahrorovk.weatherapp.data.network.remote.WeatherDto
import com.ahrorovk.weatherapp.domain.repository.WeatherRepository


class WeatherRepositoryImpl(
    private val apiWeather: WeatherApi
) : WeatherRepository {
    override suspend fun getWeather(
        lat: Double,
        long: Double,
        tempUnit: String
    ): WeatherDto {
        return apiWeather.getWeather(lat, long, tempUnit)
    }
}