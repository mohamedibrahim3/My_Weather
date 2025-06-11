package com.example.myweather.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myweather.presentation.theme.PrimaryTextDark
import com.example.myweather.presentation.theme.PrimaryTextLight
import com.example.myweather.presentation.theme.TertiaryTextDark
import com.example.myweather.presentation.theme.TertiaryTextLight

@Composable
fun WeatherCard(
    @DrawableRes icon: Int,
    value: String,
    description: String,
    modifier: Modifier = Modifier,
    isDay: Boolean
) {
    Column(
        modifier = modifier
            .height(115.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(
                width = 1.dp,
                color = if (isDay) Color(0x14060414) else Color(0x14FFFFFF)
            )
            .background(
                if (isDay) Color(0xB2FFFFFF)
                else Color(0xB2060414)
            )
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = description,
            tint = Color.Unspecified
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = if (isDay) PrimaryTextLight else PrimaryTextDark
        )
        Text(
            text = description,
            style = MaterialTheme.typography.titleSmall,
            color = if (isDay) TertiaryTextLight else TertiaryTextDark
        )
    }
}