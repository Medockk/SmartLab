package com.example.smartlab.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
val fontFamilyLato = FontFamily(Font(com.example.smartlab.R.font.lato_regular))
val fontFamilySF = FontFamily(Font(com.example.smartlab.R.font.sf_pro_display_regular))
val Lato60020_57A9FF = TextStyle(
    fontFamily = fontFamilyLato,
    fontWeight = FontWeight(600),
    fontSize = 20.sp,
    color = _57A9FF
)
val Lato60020_00B712 = TextStyle(
    fontFamily = fontFamilyLato,
    fontWeight = FontWeight(600),
    fontSize = 20.sp,
    color = _00B712
)
val SF40014_939396 = TextStyle(
    fontFamily = fontFamilySF,
    fontWeight = FontWeight(400),
    fontSize = 14.sp,
    color = _939396
)
val SF60017White = TextStyle(
    fontFamily = fontFamilySF,
    fontWeight = FontWeight(600),
    fontSize = 17.sp,
    color = Color.White
)
val SF40015Black = TextStyle(
    fontFamily = fontFamilySF,
    fontWeight = FontWeight(400),
    fontSize = 15.sp,
    color = Color.Black
)
val SF70024Black = TextStyle(
    fontFamily = fontFamilySF,
    fontWeight = FontWeight(700),
    fontSize = 24.sp,
    color = Color.Black
)
val SF60024Black = TextStyle(
    fontFamily = fontFamilySF,
    fontWeight = FontWeight(600),
    fontSize = 24.sp,
    color = Color.Black
)
val SF40015_939396 = TextStyle(
    fontFamily = fontFamilySF,
    fontWeight = FontWeight(400),
    fontSize = 15.sp,
    color = _939396
)
val SF40015_1A6FEE = TextStyle(
    fontFamily = fontFamilySF,
    fontWeight = FontWeight(400),
    fontSize = 15.sp,
    color = _1A6FEE
)