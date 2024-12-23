package com.example.smartlab.feature_app.presentation.MakingOrder.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.presentation.CustomButton
import com.example.smartlab.R
import com.example.smartlab.core.presentation.CustomEmptyButton
import com.example.smartlab.core.presentation.CustomTextField
import com.example.smartlab.feature_app.domain.model.UserData
import com.example.smartlab.ui.theme.SF40014_7E7E9A
import com.example.smartlab.ui.theme.SF40016Black
import com.example.smartlab.ui.theme.SF40016_7E7E9A
import com.example.smartlab.ui.theme.SF50016Black
import com.example.smartlab.ui.theme.SF50016White
import com.example.smartlab.ui.theme.SF50016_939396
import com.example.smartlab.ui.theme.SF60020Black
import com.example.smartlab.ui.theme._1A6FEE
import com.example.smartlab.ui.theme._B8C1CC
import com.example.smartlab.ui.theme._EBEBEB
import com.example.smartlab.ui.theme._F5F5F9

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    val heightDate = LocalConfiguration.current.screenHeightDp / 2
    val heightAddress = LocalConfiguration.current.screenHeightDp / 4
    CustomMakingOrderMenu(
        height = heightAddress.dp,
        title = "Select the patient",
        icon = ImageVector.vectorResource(R.drawable.cross),
        value = "my address",
        onValueChanged = {},
        isAddress = true
    ){

    }
}

@Composable
fun CustomMakingOrderMenu(
    modifier: Modifier = Modifier,
    height: Dp,
    title: String,
    icon: ImageVector,
    isAddress: Boolean = false,
    value: String,
    onValueChanged: (String) -> Unit,
    isDate: Boolean = false,
    isPerson: Boolean = false,
    userData: UserData = UserData(
        name = "Name", surname = "Surname",
        patronymic = "", birthdayData = "", gender = "", address = ""
    ),
    saveAddressState: Boolean = false,
    saveAddressClick: (Boolean) -> Unit = {},
    closeClick: (Boolean) -> Unit = {},
    selectClick: (String) -> Unit = {},
    confirmClick: () -> Unit,
){
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = height)
            .background(Color.White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(title, style = SF60020Black)
                Icon(
                    imageVector = icon,
                    contentDescription = "closeIcon",
                    tint = _B8C1CC,
                    modifier = Modifier.clickable {
                        closeClick(false)
                    }
                )
            }

            if (isAddress){
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxHeight(0.15f)
                ) {
                    Text("Ваш адрес", style = SF40014_7E7E9A)
                    CustomTextField(
                        value = value,
                        onValueChanged = onValueChanged,
                        hintText = "Ваш адрес",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.8f)
                    )
                }
                val latitude = remember { mutableStateOf("188") }
                val longitude = remember { mutableStateOf("4") }
                val coordinatesHeight = remember { mutableStateOf("8") }
                LazyRow(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillParentMaxHeight(0.15f)
                        ) {
                            Text("Долгота", style = SF40014_7E7E9A)
                            AddressDataTextField(latitude.value, {latitude.value = it},
                                modifier = Modifier
                                    .fillParentMaxWidth(0.35f)
                                    .fillMaxHeight(0.85f))
                        }
                    }
                    item {
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillParentMaxHeight(0.15f)
                        )  {
                            Text("Широта", style = SF40014_7E7E9A)
                            AddressDataTextField(longitude.value, {longitude.value = it},
                                modifier = Modifier
                                    .fillParentMaxWidth(0.35f)
                                    .fillMaxHeight(0.85f))
                        }
                    }
                    item {
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillParentMaxHeight(0.15f)
                        )  {
                            Text("Высота", style = SF40014_7E7E9A)
                            AddressDataTextField(coordinatesHeight.value, {coordinatesHeight.value = it},
                                modifier = Modifier
                                    .fillParentMaxWidth(0.15f)
                                    .fillMaxHeight(0.85f))
                        }
                    }
                }
                val flat = remember { mutableStateOf("188") }
                val entrance = remember { mutableStateOf("4") }
                val floor = remember { mutableStateOf("8") }
                LazyRow(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillParentMaxHeight(0.18f)
                        )  {
                            Text("Квартира", style = SF40014_7E7E9A)
                            AddressDataTextField(flat.value, {flat.value = it},
                                modifier = Modifier
                                    .fillParentMaxWidth(0.25f)
                                    .fillMaxHeight(0.85f))
                        }
                    }
                    item {
                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillParentMaxHeight(0.18f)
                        )  {
                            Text("Подъезд", style = SF40014_7E7E9A)
                            AddressDataTextField(entrance.value, {entrance.value = it},
                                modifier = Modifier
                                    .fillParentMaxWidth(0.25f)
                                    .fillMaxHeight(0.85f))
                        }
                    }
                    item {
                        Column (
                            verticalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillParentMaxHeight(0.18f)
                        ) {
                            Text("Этаж", style = SF40014_7E7E9A)
                            AddressDataTextField(floor.value, {floor.value = it},
                                modifier = Modifier
                                    .fillParentMaxWidth(0.3f)
                                    .fillMaxHeight(0.85f))
                        }
                    }
                }
                val intercom = remember { mutableStateOf("188*2180") }
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxHeight(0.25f)
                )  {
                    Text("Домофон", style = SF40014_7E7E9A)
                    CustomTextField(
                        value = intercom.value,
                        onValueChanged = {intercom.value = it},
                        hintText = "your intercom",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.85f)
                    )
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text("Сохранить этот адрес?", style = SF50016Black)
                    Switch(
                        checked = saveAddressState,
                        onCheckedChange = saveAddressClick,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            uncheckedThumbColor = Color.White,
                            checkedTrackColor = _1A6FEE,
                            uncheckedTrackColor = _EBEBEB,
                            checkedBorderColor = Color.Transparent,
                            uncheckedBorderColor = Color.Transparent,
                        )
                    )
                }
                if (saveAddressState){
                    val saveAddressText = remember { mutableStateOf("") }
                    CustomTextField(
                        value = saveAddressText.value,
                        onValueChanged = {saveAddressText.value = it},
                        hintText = "Name: for example home, work",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.25f)
                    )
                }
            }

            if (isDate){
                Column (
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxHeight(0.2f)
                ){
                    Text("Выберите дату", style = SF50016_939396)
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        CustomTextField(
                            value = value,
                            onValueChanged = onValueChanged,
                            hintText = "date",
                            modifier = Modifier.fillMaxHeight(0.85f)
                        )
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null
                        )
                    }
                }
                Column (
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxHeight(0.5f)
                ){
                    val timeList = listOf("10:00", "13:00", "14:00","15:00")
                    val timeList1 = listOf("16:00", "18:00","19:00")
                    Text("Выберите время", style = SF50016_939396)
                    LazyRow{
                        items(timeList){
                            val timeListClick = remember { mutableStateOf(false) }
                            Box(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .fillParentMaxWidth(0.2f)
                                    .fillMaxHeight(0.4f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        color = if (timeListClick.value) _1A6FEE
                                        else _F5F5F9,
                                        RoundedCornerShape(10.dp)
                                    )
                                    .clickable {
                                        timeListClick.value = !timeListClick.value
                                        if (timeListClick.value){
                                            selectClick(it)
                                        }else{
                                            selectClick("")
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ){
                                Text(it, style = if (timeListClick.value) SF50016White
                                                    else SF40016_7E7E9A)
                            }
                        }
                    }
                    LazyRow{
                        items(timeList1){
                            val timeListClick = remember { mutableStateOf(false) }
                            Box(
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .fillParentMaxWidth(0.2f)
                                    .fillMaxHeight(0.75f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(
                                        color = if (timeListClick.value) _1A6FEE
                                        else _F5F5F9,
                                        RoundedCornerShape(10.dp)
                                    )
                                    .clickable {
                                        timeListClick.value = !timeListClick.value
                                        if (timeListClick.value){
                                            selectClick(it)
                                        }else{
                                            selectClick("")
                                        }
                                    },
                                contentAlignment = Alignment.Center
                            ){
                                Text(it, style = if (timeListClick.value) SF50016White
                                                else SF40016_7E7E9A)
                            }
                        }
                    }
                }
            }

            if (isPerson){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(_F5F5F9, RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            selectClick(userData.name)
                        }
                ){
                    Row (
                        Modifier.padding(12.dp)
                    ){
                        Image(
                            painter = if (userData.gender == UserData.male){
                                painterResource(R.drawable.gender_male)
                            }else{
                                painterResource(R.drawable.gender_female)
                            },
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(text = "${userData.name} ${userData.surname}",
                            style = SF40016Black,
                            modifier = Modifier.padding(start = 12.dp))
                    }
                }
                CustomEmptyButton(
                    title = "Добавить пациента",
                    modifier = Modifier.fillMaxWidth()
                        .fillMaxHeight(0.17f)
                ) { }
            }

            CustomButton(
                title = "Подтвердить",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                onClick = confirmClick
            )
        }
    }
}

@Composable
fun AddressDataTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChanged,
        singleLine = true,
        modifier = modifier
            .background(_F5F5F9, RoundedCornerShape(10.dp))
            .border(1.dp, _EBEBEB, RoundedCornerShape(10.dp)),
        decorationBox = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 14.dp),
                contentAlignment = Alignment.CenterStart
            ){
                it()
            }
        }
    )
}