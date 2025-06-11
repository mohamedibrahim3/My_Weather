package com.example.myweather.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.R
import com.example.myweather.presentation.theme.SecondaryTextDark
import com.example.myweather.presentation.theme.SecondaryTextLight
import com.example.myweather.presentation.theme.TertiaryTextDark
import com.example.myweather.presentation.theme.TertiaryTextLight
import com.example.myweather.presentation.utils.WeatherCodeMapper

@Composable
fun DayWeatherCard(
    day: String,
    minTemperature: String,
    maxTemperature: String,
    weatherCode: Int,
    modifier: Modifier = Modifier,
    isDay: Boolean = true
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Text(
            text = day,
            style = MaterialTheme.typography.titleSmall.copy(
                fontSize = 16.sp,
                textAlign = TextAlign.Start
            ),
            color = if (isDay) TertiaryTextLight else TertiaryTextDark,
            modifier = Modifier.weight(2f)
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(WeatherCodeMapper.getWeatherIcon(weatherCode, isDay)),
            contentDescription = null,
            modifier = Modifier
                .height(32.dp)
                .width(32.dp),
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Icon(
                painter = painterResource(if (isDay) R.drawable.arrow_up_day else R.drawable.arrow_up_night),
                contentDescription = null,
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = maxTemperature,
                style = MaterialTheme.typography.titleMedium,
                color = if (isDay) SecondaryTextLight else SecondaryTextDark
            )

            Spacer(modifier = Modifier.width(8.dp))

            VerticalDivider(
                color = if (isDay) Color(0x3D060414) else Color(0x3DFFFFFF),
                thickness = 1.dp,
                modifier = Modifier.height(18.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                painter = painterResource(if (isDay) R.drawable.arrow_down_day else R.drawable.arrow_down_night),
                contentDescription = null,
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = minTemperature,
                style = MaterialTheme.typography.titleMedium,
                color = if (isDay) SecondaryTextLight else SecondaryTextDark
            )
        }
    }
}