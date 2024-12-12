package com.example.smartlab.CreateCard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smartlab.core.presentation.PatientCardTextField

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CreateCardScreen(
    viewModel: CreateCardViewModel = viewModel()
){

    val state = viewModel.state.value
    var expanded by remember {
        mutableStateOf(false)
    }

    PatientCardTextField(
        value = state.name,
        onValueChanged = {
            viewModel.onEvent(CreateCardEvent.EnteredName(it))
        },
        hintText = "text",
        isGender = true,
    )
}