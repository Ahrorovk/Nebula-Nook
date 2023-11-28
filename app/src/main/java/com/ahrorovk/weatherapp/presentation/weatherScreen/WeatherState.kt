package com.ahrorovk.weatherapp.presentation.weatherScreen

import com.ahrorovk.weatherapp.core.Location
import com.ahrorovk.weatherapp.core.getLocations
import com.ahrorovk.weatherapp.core.getDegrees
import com.ahrorovk.weatherapp.core.models.Degree
import com.ahrorovk.weatherapp.data.state.WeatherDataState

data class WeatherState(
    val selectedTempUnit: Degree = getDegrees()[0],
    val long: Double = 0.0,
    val lat: Double = 0.0,
    val tempIsOpen: Boolean = false,
    val locationIsOpen: Boolean = false,
    val locationUnits: List<Location> = getLocations(),
    val selectedLocationUnit: Location = getLocations()[0],
    val selectedDataId: Int = 0,
    val weatherDataState: WeatherDataState = WeatherDataState(),
    val tempUnits: List<Degree> = getDegrees()
)
