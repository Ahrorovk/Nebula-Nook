package com.ahrorovk.weatherapp.presentation.weatherScreen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahrorovk.weatherapp.core.Location
import com.ahrorovk.weatherapp.core.Resource
import com.ahrorovk.weatherapp.data.state.WeatherDataState
import com.ahrorovk.weatherapp.domain.location.LocationTracker
import com.ahrorovk.weatherapp.domain.weather.model.WeatherInfo
import com.ahrorovk.weatherapp.domain.weather.useCase.GetWeatherUseCase
import com.ahrorovk.weatherapp.test.GetWeatherUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val locationTracker: LocationTracker
) : ViewModel() {
    private val _state = MutableStateFlow(WeatherState())
    val state = _state.stateIn(
        viewModelScope + Dispatchers.IO,
        SharingStarted.WhileSubscribed(5000),
        WeatherState()
    )

    @RequiresApi(Build.VERSION_CODES.O)
    fun onEvent(event: WeatherEvent) {
        when (event) {
            is WeatherEvent.OnTempUnitChange -> {
                _state.update {
                    it.copy(
                        selectedTempUnit = event.state
                    )
                }
                getWeather()
            }

            is WeatherEvent.OnLocationChange -> {
                /*
                * Changing current location of user from device
                * Getting weather data after changing current location
                 */
                viewModelScope.launch(Dispatchers.IO) {
                    locationTracker.getCurrentLocation()?.let { location ->
                        _state.update {
                            it.copy(
                                lat = location.latitude,
                                long = location.longitude,
                                weatherDataState = WeatherDataState(isLoading = true)
                            )
                        }
                        delay(500)
                        getWeather()
                    } ?: kotlin.run {
                        _state.update {
                            it.copy(
                                weatherDataState = WeatherDataState(
                                    isLoading = false,
                                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                                )
                            )
                        }
                    }
                }
            }

            WeatherEvent.GetWeather -> {
                //Get weather Data from Server
                getWeather()
            }

            is WeatherEvent.OnDataIdChange -> {
                _state.update {
                    it.copy(
                        selectedDataId = event.dataId
                    )
                }
            }

            is WeatherEvent.OnTempIsOpenChange -> {
                _state.update {
                    it.copy(
                        tempIsOpen = event.state
                    )
                }
            }

            is WeatherEvent.OnLocationIsOpenChange -> {
                _state.update {
                    it.copy(
                        locationIsOpen = event.state
                    )
                }
            }

            is WeatherEvent.OnLocationUnitChange -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _state.update {
                        it.copy(
                            selectedLocationUnit = event.state
                        )
                    }
                    // Checking location Unit state
                    if (event.state.name != Location.Current_Location.name) {
                        _state.update {
                            it.copy(
                                lat = event.state.lat,
                                long = event.state.long
                            )
                        }
                        delay(200)
                        getWeather()
                    } else onEvent(WeatherEvent.OnLocationChange)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getWeather() {
        getWeatherUseCase.invoke(
            _state.value.long,
            _state.value.lat,
            _state.value.selectedTempUnit.name
        ).onEach { result: Resource<WeatherInfo> ->
            //Checking three states of result
            when (result) {
                is Resource.Success -> {
                    val response = result.data
                    _state.update {
                        it.copy(
                            weatherDataState = WeatherDataState(response = response)
                        )
                    }
                    Log.e(
                        "TAG",
                        "GetWeatherResponse->\n ${_state.value.weatherDataState.response}"
                    )
                }

                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            weatherDataState = WeatherDataState(error = result.message.toString())
                        )
                    }
                    Log.e("TAG", "GetWeatherError->${result.message}")
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            weatherDataState = WeatherDataState(isLoading = true)
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}