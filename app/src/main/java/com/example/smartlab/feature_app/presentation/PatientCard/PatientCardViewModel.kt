package com.example.smartlab.feature_app.presentation.PatientCard

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.feature_app.domain.model.UserData
import com.example.smartlab.feature_app.domain.usecase.Auth.GetUserDataUseCase
import com.example.smartlab.feature_app.domain.usecase.Auth.UpdateUserDataUseCase
import com.example.smartlab.feature_app.domain.usecase.Storage.GetImageUrlUseCase
import com.example.smartlab.feature_app.domain.usecase.Storage.SetImageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PatientCardViewModel(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val updateUserDataUseCase: UpdateUserDataUseCase,

    private val getImageUrlUseCase: GetImageUrlUseCase,
    private val setImageUseCase: SetImageUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(PatientCardState())
    val state: State<PatientCardState> = _state

    init {
        viewModelScope.launch {
            try {
                getAllUserData()
                getImageUrl()
            } catch (e: Exception) {
                Log.e("initEx", e.message.toString())
            }
        }
    }

    private suspend fun getImageUrl() {
        val imageUrl = getImageUrlUseCase()

        withContext(Dispatchers.IO){
            _state.value = state.value.copy(
                image = imageUrl
            )
        }
    }

    private suspend fun getAllUserData() {
        val list = getUserDataUseCase()

        list.forEach {
            _state.value = state.value.copy(
                name = it.name,
                surname = it.surname,
                patronymic = it.patronymic,
                birthdayData = it.birthdayData,
                gender = it.gender
            )
        }

        withContext(Dispatchers.IO) {
            _state.value = state.value.copy(
                listOfUserData = list
            )
        }
    }

    fun onEvent(event: PatientCardOnEvent) {

        when (event) {
            is PatientCardOnEvent.EnteredBirthdayData -> {
                _state.value = state.value.copy(
                    birthdayData = event.value
                )
            }

            is PatientCardOnEvent.EnteredGender -> {
                _state.value = state.value.copy(
                    gender = event.value
                )
            }

            is PatientCardOnEvent.EnteredName -> {
                _state.value = state.value.copy(
                    name = event.value
                )
            }

            is PatientCardOnEvent.EnteredPatronymic -> {
                _state.value = state.value.copy(
                    patronymic = event.value
                )
            }

            is PatientCardOnEvent.EnteredSurname -> {
                _state.value = state.value.copy(
                    surname = event.value
                )
            }

            PatientCardOnEvent.SaveClick -> {
                viewModelScope.launch {
                    try {
                        updateUserDataUseCase(
                            UserData(
                                name = state.value.name,
                                surname = state.value.surname,
                                patronymic = state.value.patronymic,
                                birthdayData = state.value.birthdayData,
                                gender = state.value.gender,
                                address = "",
                            )
                        )
                        Log.v("supa update", "update")
                    } catch (e: Exception) {
                        Log.e("saveClickEx", e.message.toString())
                    }
                }
            }
        }
    }
}