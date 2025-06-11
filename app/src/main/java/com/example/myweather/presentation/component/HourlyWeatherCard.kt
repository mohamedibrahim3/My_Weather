package com.example.myweather.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myweather.presentation.theme.SecondaryTextDark
import com.example.myweather.presentation.theme.SecondaryTextLight
import com.example.myweather.presentation.theme.TertiaryTextDark
import com.example.myweather.presentation.theme.TertiaryTextLight

@Composable
fun HourlyWeatherCard(
    modifier: Modifier = Modifier,
    time: String,
    temperature: String,
    @DrawableRes icon: Int,
    isDay: Boolean
){
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .height(120.dp)
                .width(88.dp)
                .clip(RoundedCornerShape(24.dp))
                .border(
                    border = BorderStroke(
                        1.dp,
                        if (isDay) Color(0x14060414) else Color(0x14FFFFFF)
                    ),
                    shape = RoundedCornerShape(24.dp)
                )
                .background(if (isDay) Color(0xB2FFFFFF) else Color(0xB2060414))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = temperature,
                    color = if (isDay) SecondaryTextLight else SecondaryTextDark,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(bottom = 1.5.dp)
                )
                Text(
                    text = time,
                    color = if (isDay) TertiaryTextLight else TertiaryTextDark,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }

        Box(
            modifier = Modifier
                .height(63.dp)
                .width(69.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-15).dp)
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier
            )
        }
    }

}