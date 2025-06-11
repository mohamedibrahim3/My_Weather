package com.example.myweather.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myweather.R
import com.example.myweather.presentation.theme.PrimaryTextDark
import com.example.myweather.presentation.theme.SecondaryTextLight
import com.example.myweather.presentation.utils.ThemePreviews

@Composable
fun LocationItem(
    location: String,
    modifier: Modifier = Modifier,
    isDay: Boolean
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if(!isDay){
            Icon(
                painter = painterResource(R.drawable.location_icon_dark),
                contentDescription = null,
                tint = Color(0xFFFFFFFF)
            )
            Text(
                text = location,
                style = MaterialTheme.typography.titleMedium,
                color =  PrimaryTextDark
            )
        }
        else{
            Icon(
                painter = painterResource(R.drawable.location_icon),
                contentDescription = null,
                tint = Color(0xFF323232)
            )
            Text(
                text = location,
                style = MaterialTheme.typography.titleMedium,
                color = SecondaryTextLight
            )
        }

    }
}
@ThemePreviews
@Composable
private fun LocationItemPreview() {
    LocationItem(
        location = "Ismailia",
        modifier = Modifier,
        isDay = true
    )
}