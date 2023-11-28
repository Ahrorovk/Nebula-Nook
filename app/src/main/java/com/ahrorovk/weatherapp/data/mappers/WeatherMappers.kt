package com.ahrorovk.weatherapp.data.mappers


import android.os.Build
import androidx.annotation.RequiresApi
import com.ahrorovk.weatherapp.data.network.remote.WeatherDataDto
import com.ahrorovk.weatherapp.data.network.remote.WeatherDto
import com.ahrorovk.weatherapp.domain.weather.model.WeatherData
import com.ahrorovk.weatherapp.domain.weather.model.WeatherInfo
import com.ahrorovk.weatherapp.domain.weather.model.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

/*
* Separation by index
* parsing time
* Output weatherType from weatherCode
*/
@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperature_2m[index]
        val weatherCode = weathercode[index]
        val windSpeed = windspeed_10m[index]
        val pressure = pressure_msl[index]
        val humidity = relativehumidity_2m[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

//Separation WeatherData from server to currentWeatherData and weatherDataMap (remaining days)

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDto.toWeatherInfo(dataId: Int = 0): WeatherInfo {
    val weatherDataMap = hourly.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[dataId]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}