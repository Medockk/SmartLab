package com.example.smartlab.core.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.smartlab.R
import com.example.smartlab.ui.theme.SF40012_1A6FEE
import com.example.smartlab.ui.theme.SF40012_B8C1CC
import com.example.smartlab.ui.theme._1A6FEE
import com.example.smartlab.ui.theme._B8C1CC

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun prev() {
    BottomNavigation(Modifier.fillMaxWidth(), {},{},{},{}, true)
}

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    analyzesClick: () -> Unit,
    resultsClick: () -> Unit,
    supportsClick: () -> Unit,
    profileClick: () -> Unit,
    selectedAnalyzes: Boolean = false,
    selectedResults: Boolean = false,
    selectedSupports: Boolean = false,
    selectedProfile: Boolean = false,
) {
    LazyRow(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        item {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ){
                    analyzesClick()
                }
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.analyzes),
                    contentDescription = "analyzes",
                    tint = if (selectedAnalyzes) _1A6FEE else _B8C1CC
                )
                Text(
                    "Анализы",
                    style = if (selectedAnalyzes) SF40012_1A6FEE else SF40012_B8C1CC
                )
            }
        }
        item {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ){
                    resultsClick()
                }
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.results),
                    contentDescription = "results",
                    tint = if (selectedResults) _1A6FEE else _B8C1CC
                )
                Text(
                    "Результаты",
                    style = if (selectedResults) SF40012_1A6FEE else SF40012_B8C1CC
                )
            }
        }
        item {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ){
                    supportsClick()
                }
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.supports),
                    contentDescription = "supports",
                    tint = if (selectedSupports) _1A6FEE else _B8C1CC
                )
                Text(
                    "Анализы",
                    style = if (selectedSupports) SF40012_1A6FEE else SF40012_B8C1CC
                )
            }
        }
        item {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple()
                ){
                    profileClick()
                }
            ){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.user),
                    contentDescription = "profile",
                    tint = if (selectedProfile) _1A6FEE else _B8C1CC
                )
                Text(
                    "Результаты",
                    style = if (selectedProfile) SF40012_1A6FEE else SF40012_B8C1CC
                )
            }
        }
    }
}