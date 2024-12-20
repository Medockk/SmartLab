package com.example.smartlab.core.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartlab.R
import com.example.smartlab.ui.theme.SF60017White
import com.example.smartlab.ui.theme._1A6FEE


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    InCart("690 $",
        Modifier.fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 20.dp)
    ) {

    }
}

@Composable
fun InCart(
    price: String,
    modifier: Modifier = Modifier,
    addClick: () -> Unit
) {
    Card(
        onClick = addClick,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = _1A6FEE),
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.add_for),
                    contentDescription = "in cart",
                    tint = Color.White
                )
                Text(
                    "В корзину",
                    style = SF60017White,
                    modifier = Modifier.padding(start = 16.dp))
                Spacer(Modifier.weight(1f))
                Text(price, style = SF60017White)
            }
        }
    }
}