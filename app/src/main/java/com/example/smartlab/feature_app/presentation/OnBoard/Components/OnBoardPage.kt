package com.example.smartlab.OnBoard.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartlab.OnBoard.OnBoardItem
import com.example.smartlab.ui.theme.Lato60020_00B712
import com.example.smartlab.ui.theme.Lato60020_57A9FF
import com.example.smartlab.ui.theme.SF40014_939396
import com.example.smartlab.ui.theme._57A9FF33

@Composable
fun OnBoardPage(
    item: OnBoardItem,
    index: Int = 0
) {
    val paddingTop = LocalConfiguration.current.screenHeightDp / 25
    Row (
        modifier = Modifier.fillMaxWidth()
            .padding(start = 20.dp, top = paddingTop.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ){
        Text(text = if (index != 2)"Пропустить" else "Завершить",
            style = Lato60020_57A9FF,
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple()
            ){

            })
        Box(
            modifier = Modifier.fillMaxWidth(0.6f)
                .fillMaxHeight(0.2f)
                .clip(RoundedCornerShape(topStart = 15.dp, bottomEnd = 15.dp))
                .background(_57A9FF33),
            contentAlignment = Alignment.Center
        ){
            Image(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(0.6f)
                    .fillMaxHeight(0.6f),
                colorFilter = ColorFilter.tint(Color.White),
                contentScale = ContentScale.Crop
            )
        }
    }
    Box(modifier = Modifier.fillMaxWidth()
        .fillMaxHeight(0.45f),
        contentAlignment = Alignment.BottomCenter){
        Column(
            modifier = Modifier.fillMaxHeight(0.17f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(item.title, style = Lato60020_00B712)
            Text(item.description, style = SF40014_939396)
        }
    }
}