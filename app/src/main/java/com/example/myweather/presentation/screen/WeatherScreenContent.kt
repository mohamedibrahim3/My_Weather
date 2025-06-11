package com.example.myweather.presentation.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.myweather.R
import com.example.myweather.presentation.component.DayWeatherItems
import com.example.myweather.presentation.component.LocationItem
import com.example.myweather.presentation.component.MinMaxTemperature
import com.example.myweather.presentation.component.TodayWeatherHourItems
import com.example.myweather.presentation.component.WeatherCard
import com.example.myweather.presentation.theme.PrimaryTextDark
import com.example.myweather.presentation.theme.PrimaryTextLight
import com.example.myweather.presentation.theme.TertiaryTextDark
import com.example.myweather.presentation.theme.TertiaryTextLight
import com.example.myweather.presentation.utils.CloudCoverageMapper
import com.example.myweather.presentation.utils.WeatherCodeMapper
import kotlin.math.roundToInt

@Composable
fun WeatherScreenContent(
    state: WeatherUiState.Success,
    modifier: Modifier = Modifier
) {
    val isDay = state.weather.current.isDayTime
    val current = state.weather.current
    val currentUnits = state.weather.currentUnits
    val daily = state.weather.daily
    val dailyUnits = state.weather.dailyUnits
    val hourly = state.weather.hourly
    val hourlyUnits = state.weather.hourlyUnits

    val scrollState = rememberScrollState()
    val isScrolled by remember {
        derivedStateOf { scrollState.value > 70 }
    }

    val imageSize by animateDpAsState(
        targetValue = if (isScrolled) 124.dp else 226.dp,
        animationSpec = tween(durationMillis = 300),
        label = "imageSize"
    )

    val textAlpha by animateFloatAsState(
        targetValue = if (isScrolled) 0f else 1f,
        animationSpec = tween(durationMillis = 300),
        label = "textAlpha"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    if (isDay) listOf(Color(0xFF87CEFA), Color(0xFFFFFFFF))
                    else listOf(Color(0xFF060414), Color(0xFF0D0C19))
                )
            )
            .padding(vertical = 24.dp, horizontal = 16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        LocationItem(location = state.cityName, isDay = isDay)
        Spacer(modifier = Modifier.height(10.dp))
        if (isScrolled) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(
                        WeatherCodeMapper.getWeatherIcon(
                            current.weatherCode,
                            isDay
                        )
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(imageSize)
                        .shadow(
                            elevation = 20.dp,
                            shape = CircleShape,
                            clip = false
                        )
                )


                Spacer(modifier = Modifier.width(30.dp))

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${current.temperature2m}${currentUnits.temperature2m}",
                        color = if (isDay) PrimaryTextLight else PrimaryTextDark,
                        style = MaterialTheme.typography.displayMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = CloudCoverageMapper.getCloudDescription(current.cloudCover),
                        color = if (isDay) TertiaryTextLight else TertiaryTextDark,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    MinMaxTemperature(
                        minTemperature = "${daily.temperature2mMin[0]}${dailyUnits.temperature2mMin}",
                        maxTemperature = "${daily.temperature2mMax[0]}${dailyUnits.temperature2mMax}",
                        isDay = isDay,
                    )
                }
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(
                        WeatherCodeMapper.getWeatherIcon(
                            current.weatherCode,
                            isDay
                        )
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(imageSize)
                        .shadow(
                            elevation = 20.dp,
                            shape = CircleShape,
                            clip = false
                        )
                )


                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.graphicsLayer(alpha = textAlpha)
                ) {
                    Text(
                        text = "${current.temperature2m}${currentUnits.temperature2m}",
                        color = if (isDay) PrimaryTextLight else PrimaryTextDark,
                        style = MaterialTheme.typography.displayLarge
                    )
                    Text(
                        text = CloudCoverageMapper.getCloudDescription(current.cloudCover),
                        color = if (isDay) TertiaryTextLight else TertiaryTextDark,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    MinMaxTemperature(
                        minTemperature = "${daily.temperature2mMin[0]}${dailyUnits.temperature2mMin}",
                        maxTemperature = "${daily.temperature2mMax[0]}${dailyUnits.temperature2mMax}",
                        isDay = isDay
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            WeatherCard(
                icon = R.drawable.wind_speed,
                value = current.windSpeed10m.toString() + currentUnits.windSpeed10m,
                description = stringResource(R.string.wind),
                modifier = Modifier.weight(1f),
                isDay = isDay
            )
            WeatherCard(
                icon = R.drawable.humidity,
                value = current.relativeHumidity2m.toString() + currentUnits.relativeHumidity2m,
                description = stringResource(R.string.humidity),
                modifier = Modifier.weight(1f),
                isDay = isDay
            )
            WeatherCard(
                icon = R.drawable.rain,
                value = current.precipitationProbability.toString() + currentUnits.precipitationProbability,
                description = stringResource(R.string.rain),
                modifier = Modifier.weight(1f),
                isDay = isDay
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            WeatherCard(
                icon = R.drawable.uv,
                value = current.uvIndex.toString(),
                description = stringResource(R.string.uv_index),
                modifier = Modifier.weight(1f),
                isDay = isDay
            )
            WeatherCard(
                icon = R.drawable.pressure_day,
                value = current.surfacePressure.toString() + currentUnits.surfacePressure,
                description = stringResource(R.string.pressure),
                modifier = Modifier.weight(1f),
                isDay = isDay
            )
            WeatherCard(
                icon = R.drawable.temperature_day,
                value = current.apparentTemperature.toString() + currentUnits.apparentTemperature,
                description = stringResource(R.string.feels_like),
                modifier = Modifier.weight(1f),
                isDay = isDay
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                text = "Today",
                style = MaterialTheme.typography.headlineMedium,
                color = if(isDay) Color(0xFF060414) else Color(0xFFFFFFFF),
            )
            Spacer(modifier = Modifier.height(8.dp))
            TodayWeatherHourItems(
                timeList = hourly.time,
                temperatureList = hourly.temperature2m.map {
                    it.roundToInt().toString() + hourlyUnits.temperature2m
                },
                code = hourly.weatherCode,
                isDay = isDay,
            )
            Spacer(modifier = Modifier.height(24.dp))
            DayWeatherItems(
                days = daily.time,
                minTemperatures = daily.temperature2mMin.map {
                    it.roundToInt().toString() + dailyUnits.temperature2mMin
                },
                maxTemperatures = daily.temperature2mMax.map {
                    it.roundToInt().toString() + dailyUnits.temperature2mMax
                },
                weatherCodes = daily.weatherCode,
                isDay = isDay
            )
        }
    }
}