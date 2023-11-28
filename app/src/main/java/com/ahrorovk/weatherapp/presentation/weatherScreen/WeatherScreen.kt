package com.ahrorovk.weatherapp.presentation.weatherScreen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ahrorovk.weatherapp.presentation.components.CompressedWeatherDataItem
import com.ahrorovk.weatherapp.presentation.components.DateItem
import com.ahrorovk.weatherapp.presentation.components.LocationChangeItem
import com.ahrorovk.weatherapp.presentation.components.TempChangeItem
import com.ahrorovk.weatherapp.presentation.components.WeatherItem
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherScreen(
    state: WeatherState,
    onEvent: (WeatherEvent) -> Unit
) {
    val context = LocalContext.current
    val swipeState = rememberSwipeRefreshState(state.weatherDataState.isLoading)
    LaunchedEffect(key1 = state.weatherDataState.error) {
        if (state.weatherDataState.error.isNotEmpty())
            Toast.makeText(context, state.weatherDataState.error, Toast.LENGTH_SHORT).show()
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.weatherDataState.isLoading)
            CircularProgressIndicator()
        SwipeRefresh(
            state = swipeState,
            onRefresh = {
                onEvent(WeatherEvent.GetWeather)
            }
        ) {
            Column {
                state.weatherDataState.response?.let { res ->
                    res.currentWeatherData?.let { current ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            LocationChangeItem(
                                state = state,
                                onClick = {
                                    onEvent(WeatherEvent.OnLocationIsOpenChange(true))
                                },
                                onDismiss = {
                                    onEvent(WeatherEvent.OnLocationIsOpenChange(false))
                                }
                            ) {
                                onEvent(WeatherEvent.OnLocationUnitChange(it))
                            }
                            TempChangeItem(
                                state = state,
                                onClick = {
                                    onEvent(WeatherEvent.OnTempIsOpenChange(true))
                                },
                                onDismiss = {
                                    onEvent(WeatherEvent.OnTempIsOpenChange(false))
                                }
                            ) {
                                onEvent(WeatherEvent.OnTempUnitChange(it))
                            }
                        }
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            WeatherItem(
                                state = state,
                                backgroundColor = MaterialTheme.colorScheme.primary
                            )
                        }
                        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                            item {
                                res.weatherDataPerDay.forEach { (dayIndex, dailyWeather) ->
                                    DateItem(dayIndex = dayIndex)
                                    if (dayIndex in 1..5) {
                                        LazyRow(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 16.dp)
                                        ) {
                                            item {
                                                dailyWeather.forEachIndexed { dataId, data ->
                                                    CompressedWeatherDataItem(
                                                        data = data,
                                                        selectedTempUnit = state.selectedTempUnit
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}