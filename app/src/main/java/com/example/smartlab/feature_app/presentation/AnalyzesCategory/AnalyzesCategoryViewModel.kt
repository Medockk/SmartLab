package com.example.smartlab.feature_app.presentation.AnalyzesCategory

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartlab.feature_app.domain.model.Cart
import com.example.smartlab.feature_app.domain.usecase.Cart.AddProcedureInCartUseCase
import com.example.smartlab.feature_app.domain.usecase.Cart.GetAllUserItemFromCartUseCase
import com.example.smartlab.feature_app.domain.usecase.Category.GetAllCategoryUseCase
import com.example.smartlab.feature_app.domain.usecase.Procedure.GetAllProcedureUseCase
import com.example.smartlab.feature_app.domain.usecase.Search.SearchAnalyzesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnalyzesCategoryViewModel(
    private val getAllCategoryUseCase: GetAllCategoryUseCase,
    private val getAllProcedureUseCase: GetAllProcedureUseCase,
    private val addProcedureInCartUseCase: AddProcedureInCartUseCase,
    private val getAllUserItemFromCartUseCase: GetAllUserItemFromCartUseCase,

    private val searchAnalyzesUseCase: SearchAnalyzesUseCase,
): ViewModel() {

    private val _state = mutableStateOf(AnalyzesCategoryState())
    val state: State<AnalyzesCategoryState> = _state

    init {
        viewModelScope.launch {
            launch { getAllCategory() }
            launch { getAllProcedures() }
            launch { getAllItemFromCart() }
        }
    }

    private suspend fun getAllItemFromCart() {
        try {
            val cart = getAllUserItemFromCartUseCase()

            _state.value = state.value.copy(
                amount = ""
            )

            cart.forEach {
                _state.value = state.value.copy(
                    amount = if (state.value.amount.isNotEmpty()){
                        (state.value.amount.toInt() + it.price.toInt()).toString()
                    }else{
                        it.price
                    }
                )
            }
        } catch (e: Exception) {
            Log.e("getAllItemFromCartEx", e.message.toString())
        }
    }

    private suspend fun getAllProcedures() {
        try {
            val proceduresList = getAllProcedureUseCase()

            withContext(Dispatchers.IO){
                _state.value = state.value.copy(
                    proceduresList = proceduresList
                )
            }
        } catch (e: Exception) {
            Log.e("getAllProceduresEx", e.message.toString())
        }
    }

    private suspend fun getAllCategory() {
        try {
            val categoryList = getAllCategoryUseCase()

            withContext(Dispatchers.IO){
                _state.value = state.value.copy(
                    categoryList = categoryList
                )
            }

        } catch (e: Exception) {
            Log.e("getAllCategoryEx", e.message.toString())
        }
    }

    private var job: Job? = null

    fun onEvent(event: AnalyzesCategoryEvent){
        when (event){
            is AnalyzesCategoryEvent.FindText -> {
                _state.value = state.value.copy(
                    findText = event.value
                )

                if (state.value.findText.isNotEmpty() && state.value.findText.isNotBlank()){
                    job?.cancel("")
                    job = viewModelScope.launch {
                        val list = searchAnalyzesUseCase(state.value.findText)

                        _state.value = state.value.copy(
                            proceduresList = list
                        )
                    }
                }else{
                    viewModelScope.launch {
                        getAllProcedures()
                    }
                }
            }

            is AnalyzesCategoryEvent.AnalyzesCatalogClick -> {
                _state.value = state.value.copy(
                    selectedCategory = event.value
                )
            }
            is AnalyzesCategoryEvent.AddClick -> {

                _state.value = state.value.copy(
                    procedureName = event.name,
                    procedureDate = event.time,
                    procedurePrice = event.price
                )

                _state.value = state.value.copy(
                    isAdded = !state.value.isAdded
                )
            }

            AnalyzesCategoryEvent.AddProcedureInCart -> {
                viewModelScope.launch {
                    try {
                        addProcedureInCartUseCase(
                            cart = Cart(
                                procedure = state.value.procedureName,
                                price = state.value.procedurePrice
                            )
                        )
                        _state.value = state.value.copy(
                            isRemoved = true,
                            isAdded = false,
                            inCart = true
                        )

                        getAllItemFromCart()
                    } catch (e: Exception) {
                        Log.e("addProcedureEx", e.message.toString())
                    }
                }
            }

            AnalyzesCategoryEvent.ChangeCardState -> {
                _state.value = state.value.copy(
                    isAdded = !state.value.isAdded
                )
            }
        }
    }
}