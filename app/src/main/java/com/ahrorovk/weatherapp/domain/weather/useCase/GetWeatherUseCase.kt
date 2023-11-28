package com.ahrorovk.weatherapp.domain.weather.useCase

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.ahrorovk.weatherapp.core.Resource
import com.ahrorovk.weatherapp.data.mappers.toWeatherInfo
import com.ahrorovk.weatherapp.domain.repository.WeatherRepository
import com.ahrorovk.weatherapp.domain.weather.model.WeatherInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    @RequiresApi(Build.VERSION_CODES.O)
    operator fun invoke(
        long: Double,
        lat: Double,
        tempUnit: String
    ): Flow<Resource<WeatherInfo>> = flow {
        try {
            emit(Resource.Loading<WeatherInfo>())
            // Get Success response from server
            val response = repository.getWeather(lat, long, tempUnit)
            emit(Resource.Success<WeatherInfo>(response.toWeatherInfo()))
        } catch (e: HttpException) {
            // Catch some http exception from server
            emit(
                Resource.Error<WeatherInfo>(
                    e.message() ?: "Error"
                )
            )
        } catch (e: IOException) {
            // Catch io exception like `invalid internet connection` from server
            emit(Resource.Error<WeatherInfo>("Check your internet connection."))
        } catch (e: Exception) {
            // Catch other exceptions from server
            emit(Resource.Error<WeatherInfo>("${e.message}"))
        }
    }
}