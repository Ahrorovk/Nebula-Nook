package com.ahrorovk.weatherapp.presentation.weatherScreen

import com.ahrorovk.weatherapp.core.Location
import com.ahrorovk.weatherapp.core.models.Degree

sealed class WeatherEvent {
    data class OnTempUnitChange(val state: Degree) : WeatherEvent()
    data class OnLocationUnitChange(val state: Location) : WeatherEvent()
    data class OnDataIdChange(val dataId: Int) : WeatherEvent()
    data class OnTempIsOpenChange(val state: Boolean) : WeatherEvent()
    data class OnLocationIsOpenChange(val state: Boolean) : WeatherEvent()
    object OnLocationChange : WeatherEvent()
    object GetWeather : WeatherEvent()
}
