package com.ahrorovk.weatherapp.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ahrorovk.weatherapp.core.models.Degree
import com.ahrorovk.weatherapp.domain.weather.model.WeatherData

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CompressedWeatherDataItem(
    data: WeatherData,
    selectedTempUnit: Degree
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .width(60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${data.time.toLocalTime()}"
        )
        Image(
            painter = painterResource(id = data.weatherType.iconRes),
            contentDescription = "weatherType",
            modifier = Modifier.size(35.dp)
        )
        Text(
            text = data.temperatureCelsius.toString() + selectedTempUnit.type
        )
    }
}