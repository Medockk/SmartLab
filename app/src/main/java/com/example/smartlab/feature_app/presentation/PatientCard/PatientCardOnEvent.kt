package com.example.smartlab.feature_app.presentation.PatientCard

sealed class PatientCardOnEvent {

    data object SaveClick: PatientCardOnEvent()

    data class EnteredName(val value: String): PatientCardOnEvent()
    data class EnteredSurname(val value: String): PatientCardOnEvent()
    data class EnteredPatronymic(val value: String): PatientCardOnEvent()
    data class EnteredBirthdayData(val value: String): PatientCardOnEvent()
    data class EnteredGender(val value: String): PatientCardOnEvent()
}