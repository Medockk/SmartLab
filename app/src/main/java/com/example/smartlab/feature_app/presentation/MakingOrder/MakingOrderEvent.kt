package com.example.smartlab.feature_app.presentation.MakingOrder

sealed class MakingOrderEvent {

    data class EnteredAddress(val value: String): MakingOrderEvent()
    data class EnteredData(val value: String): MakingOrderEvent()
    data class EnteredPerson(val value: String): MakingOrderEvent()
    data class EnteredPhone(val value: String): MakingOrderEvent()
    data class EnteredComment(val value: String): MakingOrderEvent()

    data object AddressClick: MakingOrderEvent()
    data object DateClick: MakingOrderEvent()

    data object AddOnMorePatient : MakingOrderEvent()
    data object MakeOrder : MakingOrderEvent()
    data object CompleteChanges : MakingOrderEvent()
}