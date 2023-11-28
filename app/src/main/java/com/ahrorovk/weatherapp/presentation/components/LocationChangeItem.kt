package com.ahrorovk.weatherapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahrorovk.weatherapp.core.Location
import com.ahrorovk.weatherapp.core.formatLocation
import com.ahrorovk.weatherapp.presentation.weatherScreen.WeatherState

@Composable
fun LocationChangeItem(
    state: WeatherState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    onSelectedDropDown: (Location) -> Unit
) {
    Box(
        modifier = modifier,
    ) {
        DropdownMenu(
            expanded = state.locationIsOpen,
            onDismissRequest = onDismiss
        ) {
            state.locationUnits.forEach { temp ->
                if (temp.name != state.selectedLocationUnit.name)
                    LocationDropDownItem(
                        temp = temp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        onDismiss()
                        onSelectedDropDown(temp)
                    }
            }
        }
        Row(
            modifier = Modifier
                .padding(8.dp)
                .clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier
                    .padding(end = 5.dp),
                text = state.selectedLocationUnit.name.formatLocation()
            )
            Icon(
                imageVector = if (state.tempIsOpen) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                contentDescription = "dropDown"
            )
        }
    }
}