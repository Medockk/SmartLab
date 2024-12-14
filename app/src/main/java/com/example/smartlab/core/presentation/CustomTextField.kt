package com.example.smartlab.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartlab.ui.theme.SF40015Black
import com.example.smartlab.ui.theme.SF40015_939396
import com.example.smartlab.ui.theme._EBEBEB
import com.example.smartlab.ui.theme._F5F5F9

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun prev() {

}

@Composable
fun CustomTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    hintText: String,
    isGender: Boolean = false,
    modifier: Modifier = Modifier
) {

    BasicTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(_F5F5F9, RoundedCornerShape(10.dp))
            .border(1.dp, _EBEBEB),
        singleLine = true,
        textStyle = SF40015Black,
        decorationBox = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box{
                    if (value.isEmpty()){
                        Text(hintText, style = SF40015_939396)
                    }
                    it()
                }
                Spacer(Modifier.weight(1f))
                if (isGender){
                    Column {
                        val r = remember { mutableStateOf(false) }
                        Icon(
                            imageVector = Icons.Outlined.KeyboardArrowDown,
                            contentDescription = null,
                            modifier = Modifier.clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = rememberRipple()
                            ){
                                r.value = !r.value
                            }
                        )
                        DropdownMenu(
                            expanded = r.value, onDismissRequest = {r.value = !r.value}) {
                            Column (
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                Text("male",
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = rememberRipple()
                                        ) {
                                            onValueChanged("male")
                                            r.value = !r.value
                                        }
                                )
                                Text("female",
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = rememberRipple()
                                        ) {
                                            onValueChanged("female")
                                            r.value = !r.value
                                        })
                            }
                        }
                    }
                }
            }
        }
    )
}
