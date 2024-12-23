package com.example.smartlab.feature_app.presentation.Analuzes

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.feature_app.domain.model.Cart
import com.example.smartlab.feature_app.domain.usecase.Cart.AddProcedureInCartUseCase
import com.example.smartlab.feature_app.domain.usecase.Cart.GetAllUserItemFromCartUseCase
import com.example.smartlab.feature_app.domain.usecase.Cart.RemoveItemFromCartUseCase
import com.example.smartlab.feature_app.domain.usecase.Category.GetAllCategoryUseCase
import com.example.smartlab.feature_app.domain.usecase.Procedure.GetAllProcedureUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnalyzesViewModel(
    private val getAllCategoryUseCase: GetAllCategoryUseCase,
    private val getAllProcedureUseCase: GetAllProcedureUseCase,

    private val getAllUserItemFromCartUseCase: GetAllUserItemFromCartUseCase,

    private val addItemInCartUseCase: AddProcedureInCartUseCase,
    private val removeItemFromCartUseCase: RemoveItemFromCartUseCase
) : ViewModel() {

    private val _state = mutableStateOf(AnalyzesState())
    val state: State<AnalyzesState> = _state

    init {
        viewModelScope.launch {
            try {
                launch { getAllCategory() }
                launch { getAllProcedure() }
                launch { getAllUserItemFromCart() }
                launch {
                    if (state.value.userCart.isNotEmpty()){
                        _state.value = state.value.copy(
                            inCart = true
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("initEx", e.message.toString())
            }
        }
    }

    private suspend fun getAllUserItemFromCart(){
        val userCart = getAllUserItemFromCartUseCase()

        userCart.forEach {
            _state.value = if (state.value.amount.isNotEmpty()){
                state.value.copy(
                    amount = (state.value.amount.toInt() + it.price.toInt()).toString(),
                    inCart = true
                )
            }else{
                state.value.copy(
                    amount = it.price,
                    inCart = true
                )
            }
        }

        withContext(Dispatchers.IO){
            _state.value = state.value.copy(
                userCart = userCart
            )
        }
    }

    private suspend fun getAllProcedure(){
        val list = getAllProcedureUseCase()

        withContext(Dispatchers.IO){
            _state.value = state.value.copy(
                procedureList = list
            )
        }
    }

    private suspend fun getAllCategory(){
        try {
            val list = getAllCategoryUseCase()

            withContext(Dispatchers.IO){
                _state.value = state.value.copy(
                    categoryList = list
                )
            }
        } catch (e: Exception) {
            Log.e("getAllCategoryEx", e.message.toString())
        }
    }

    fun onEvent(event: AnalyzesEvent){
        when (event){
            is AnalyzesEvent.EnterFindText -> {
                _state.value = state.value.copy(
                    findText = event.value
                )
            }

            is AnalyzesEvent.AnalyzesAddClick -> {
                _state.value = state.value.copy(
                    addProcedure = event.value
                )
            }
            is AnalyzesEvent.AnalyzesCatalogClick -> {
                _state.value = state.value.copy(
                    selectedCategory = event.value,
                    isComplete = true
                )
            }
            AnalyzesEvent.AnalyzesRemoveClick -> {
                viewModelScope.launch {
                    try {
                        removeItemFromCartUseCase(
                            Cart(
                                procedure = state.value.nameProcedure
                            )
                        )
                        Log.v("removeClick", "remove")
                    } catch (e: Exception) {
                        Log.e("removeClickEx", e.message.toString())
                    }
                }
            }

            AnalyzesEvent.CompleteChanges -> {
                _state.value = state.value.copy(
                    isComplete = !state.value.isComplete
                )
            }

            is AnalyzesEvent.AnalyzesInformation -> {
                _state.value = state.value.copy(
                    dateProcedure = event.date,
                    priceProcedure = event.price,
                    nameProcedure = event.title
                )
            }

            AnalyzesEvent.AddProcedureInCart -> {
                viewModelScope.launch {
                    try {
                        val successful = addItemInCartUseCase(
                            Cart(
                                procedure = state.value.nameProcedure,
                                price = state.value.priceProcedure
                            )
                        )
                        _state.value = state.value.copy(
                            isRemoved = successful,
                            amount = if (state.value.amount.isNotEmpty()){
                               (state.value.priceProcedure.toInt() + state.value.amount.toInt()).toString()
                            }else{
                                state.value.priceProcedure
                            },
                            inCart = true
                        )
                        Log.v("addProcedureInCart", "add")
                    } catch (e: Exception) {
                        Log.e("addProcedureInCartEx", e.message.toString())
                    }
                }
            }
        }
    }
}