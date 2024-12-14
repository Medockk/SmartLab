package com.example.smartlab.core.presentation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.presentation.CustomButton
import com.example.smartlab.ui.theme.SF40015Black
import com.example.smartlab.ui.theme.SF50016Black
import com.example.smartlab.ui.theme.SF50016_939396
import com.example.smartlab.ui.theme.SF60014_939396
import com.example.smartlab.ui.theme.SF60020Black
import com.example.smartlab.ui.theme._7E7E9A
import com.example.smartlab.ui.theme._818C99

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun prev() {
    MoreInformationAboutProcedure({},{},
        "Клинический анализ крови\nс лейкоцитарной формулой",
        "Добавить за 690 ₽")
}

@Composable
fun MoreInformationAboutProcedure(
    closeClick: (Boolean) -> Unit,
    addClick: () -> Unit,
    title: String,
    price: String,
    modifier: Modifier = Modifier,
) {

    val paddingTop = LocalConfiguration.current.screenHeightDp / 4
    val paddingBottom = (LocalConfiguration.current.screenHeightDp / 2.3).toInt()

    val startOffset = (LocalConfiguration.current.screenHeightDp)
    val endOffset = LocalConfiguration.current.screenWidthDp - paddingBottom

    var boxState by remember { mutableStateOf(startOffset) }

    val offset by animateDpAsState(
        targetValue = boxState.dp,
        animationSpec = tween(1500)
    )

    boxState = endOffset

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = paddingTop.dp)
            .offset(y = offset)
            .background(Color.White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
        contentAlignment = Alignment.BottomCenter
    ){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceAround
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ){
                Text(title,
                    style = SF60020Black)
                Spacer(Modifier.weight(1f))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            Brush.verticalGradient(listOf(_818C99, _818C99)),
                            CircleShape, 0.12f
                        )
                        .clip(CircleShape)
                        .clickable(
                            remember { MutableInteractionSource() },
                            rememberRipple()
                        ) {
                            boxState = startOffset
                            closeClick(false)
                        }
                ){
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "close",
                        tint = _7E7E9A,
                        modifier = Modifier.padding(7.dp)
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight(0.55f)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxHeight(0.45f)
                ) {
                    Text("Описание", style = SF50016_939396)
                    Text("Клинический анализ крови – это самое важное" +
                            " комплексное лабораторное исследование при обследовании " +
                            "человека с любым заболеванием. Изменение исследуемых показателей, " +
                            "как правило, происходит задолго до появления видимых симптомов болезни. ",
                        style = SF40015Black)
                }
                Column (
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxHeight(0.85f)
                ){
                    Text("Подготовка", style = SF50016_939396)
                    Text("Кровь следует сдавать утром натощак, днем " +
                            "или вечером – через 4-5 часов после последнего приема пищи.",
                        style = SF40015Black)
                    Text("За 1–2 дня до исследования необходимо исключить " +
                            "из рациона продукты с высоким содержанием жиров и алкоголь.",
                        style = SF40015Black)
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Column {
                        Text("Результаты через:", style = SF60014_939396)
                        Text("1 день", style = SF50016Black)
                    }
                    Column {
                        Text("Биоматериал:", style = SF60014_939396)
                        Text("Венозная кровь", style = SF50016Black)
                    }
                }

                CustomButton(
                    title = price,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                ) {
                    boxState = startOffset
                    addClick()
                }
            }
        }
    }
}