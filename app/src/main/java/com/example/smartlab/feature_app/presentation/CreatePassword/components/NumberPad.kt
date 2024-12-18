package com.example.smartlab.feature_app.presentation.CreatePassword.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.smartlab.R
import com.example.smartlab.ui.theme.SF60024Black
import com.example.smartlab.ui.theme._1A6FEE
import com.example.smartlab.ui.theme._F5F5F9

@Composable
fun NumberPad(
    currentNumber: Int?,
    modifier: Modifier = Modifier,
    isDelete: Boolean = false,
    numberClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(
                color = if (isDelete) Color.Transparent
                else if (currentNumber != null) _F5F5F9
            else Color.Transparent, CircleShape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = _1A6FEE)
            ){
                if (currentNumber != null) {
                    numberClick(currentNumber)
                }
            }
            .then(modifier),
        contentAlignment = Alignment.Center
    ){
        if (!isDelete){
            Text(
                text = currentNumber?.toString() ?: "",
                style = SF60024Black)
        }else{
            Icon(
                painter = painterResource(R.drawable.del_icon),
                contentDescription = "delete"
            )
        }
    }
}