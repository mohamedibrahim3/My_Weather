package com.example.myweather.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myweather.presentation.utils.WeatherCodeMapper.getWeatherIcon

@Composable
fun TodayWeatherHourItems(
    timeList: List<String>,
    temperatureList: List<String>,
    code: List<Int>,
    isDay: Boolean
) {
        LazyRow(
            modifier = Modifier
                    .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(timeList.size) {
                HourlyWeatherCard(
                    time = timeList[it].substring(11, 16),
                    temperature = temperatureList[it],
                    icon = getWeatherIcon(isDay = isDay, code = code[it]),
                    isDay = isDay,
                )
            }
        }
}