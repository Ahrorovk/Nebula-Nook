package com.ahrorovk.weatherapp.presentation.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ahrorovk.weatherapp.core.Location
import com.ahrorovk.weatherapp.core.formatLocation

@Composable
fun LocationDropDownItem(
    temp: Location,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    DropdownMenuItem(
        modifier=modifier,
        text = {
            Text(
                text = temp.name.formatLocation()
            )
        }, onClick = onClick
    )
}