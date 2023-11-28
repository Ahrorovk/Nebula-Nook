package com.ahrorovk.weatherapp.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateItem(
    dayIndex: Int,
) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .width(100.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        if (dayIndex == 1)
            Text(
                text = "Tomorrow",
                modifier = Modifier
                    .padding(5.dp),
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        else if (dayIndex in 2..5)
            Text(
                text = "${
                    LocalDate.now().plusDays(dayIndex.toLong()).dayOfWeek
                }".toLowerCase().capitalize(),
                modifier = Modifier
                    .padding(3.dp),
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
    }
}