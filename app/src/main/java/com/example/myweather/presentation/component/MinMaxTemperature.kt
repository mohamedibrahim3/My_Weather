package com.example.myweather.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myweather.R
import com.example.myweather.presentation.theme.SecondaryTextDark
import com.example.myweather.presentation.theme.SecondaryTextLight

@Composable
fun MinMaxTemperature(
    minTemperature: String,
    maxTemperature: String,
    modifier: Modifier = Modifier,
    isDay: Boolean
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(
                color = if (isDay) Color(0x14060414)
                else Color(0x14FFFFFF),
                shape = RoundedCornerShape(size = 100.dp)
            )
            .padding(horizontal = 24.dp, vertical = 8.dp)
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
