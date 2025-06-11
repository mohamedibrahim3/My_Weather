package com.example.myweather.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myweather.presentation.theme.PrimaryTextDark
import com.example.myweather.presentation.theme.PrimaryTextLight
import com.example.myweather.presentation.utils.getDayName

@Composable
fun DayWeatherItems(
    modifier: Modifier = Modifier,
    days: List<String>,
    minTemperatures: List<String>,
    maxTemperatures: List<String>,
    weatherCodes: List<Int>,
    isDay: Boolean
) {
    Column(){
        Text(
            text = "Next 7 days",
            color = if (isDay) PrimaryTextLight else PrimaryTextDark,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(12.dp))
        Column(
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = if (isDay) Color(0x14060414) else Color(0x14FFFFFF),
                    shape = RoundedCornerShape(size = 24.dp)
                )
                .background(
                    color = if (isDay) Color(0xB2FFFFFF) else Color(0xB2060414),
                    shape = RoundedCornerShape(size = 24.dp)
                )
                .padding(vertical = 4.dp)
        ) {
            repeat(days.size) { index ->
                DayWeatherCard(
                    day = getDayName(days[index]) ,
                    minTemperature = minTemperatures[index],
                    maxTemperature = maxTemperatures[index],
                    weatherCode = weatherCodes[index],
                    modifier = Modifier,
                    isDay = isDay
                )
                if (index < days.size - 1) {
                    HorizontalDivider(
                        color = if (isDay) Color(0x14060414) else Color(0x14FFFFFF),
                        thickness = 1.dp
                    )
                }
            }
        }
    }

}