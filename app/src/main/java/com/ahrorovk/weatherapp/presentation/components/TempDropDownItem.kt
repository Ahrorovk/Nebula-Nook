package com.ahrorovk.weatherapp.presentation.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ahrorovk.weatherapp.core.models.Degree

@Composable
fun TempDropDownItem(
    temp: Degree,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    DropdownMenuItem(
        modifier=modifier,
        text = {
            Text(
                text = "${temp.name}(${temp.type})"
            )
        }, onClick = onClick
    )
}