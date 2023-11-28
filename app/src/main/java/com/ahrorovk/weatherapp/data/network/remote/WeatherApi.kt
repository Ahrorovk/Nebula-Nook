package com.ahrorovk.weatherapp.data.network.remote

import com.ahrorovk.weatherapp.core.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    // Main request to server for getting weatherData
    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double,
        @Query("temperature_unit") tempUnit: String,
        @Query("hourly") hourlyFields: String = Constants.hourlyFields
    ): WeatherDto
}