package com.ahrorovk.weatherapp.data.network

import com.ahrorovk.weatherapp.data.network.remote.WeatherDataDto
import com.ahrorovk.weatherapp.data.network.remote.WeatherDto
import com.ahrorovk.weatherapp.domain.repository.WeatherRepository

class TestWeatherRepositoryImpl : WeatherRepository {
    private var shouldReturnNetworkError: Boolean = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getWeather(lat: Double, long: Double, tempUnit: String): WeatherDto {
        return if (shouldReturnNetworkError) {
            WeatherDto(WeatherDataDto(), "Error")
        } else {
            WeatherDto(
                WeatherDataDto(),
                ""
            )
        }
    }
}