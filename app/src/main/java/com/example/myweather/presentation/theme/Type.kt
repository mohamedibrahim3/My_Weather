package com.example.myweather.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.myweather.R


val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 64.sp,
        letterSpacing = 0.25.sp,
        textAlign = TextAlign.Center,
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist)),
        fontWeight = FontWeight(600),
        fontSize = 20.sp,
        letterSpacing = 0.25.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist)),
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        letterSpacing = 0.25.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist)),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        letterSpacing = 0.25.sp,
        textAlign = TextAlign.Center
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.urbanist)),
        fontWeight = FontWeight(400),
        fontSize = 14.sp,
        letterSpacing = 0.25.sp,
        textAlign = TextAlign.Center
    )
)