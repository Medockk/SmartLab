package com.example.smartlab.feature_app.domain.usecase.Cart

import com.example.smartlab.feature_app.domain.model.Cart
import com.example.smartlab.feature_app.domain.repository.CartRepository

class AddProcedureInCartUseCase(
    private val cartRepository: CartRepository
) {

    suspend operator fun invoke(cart: Cart) : Boolean{
        return cartRepository.addItemInCart(cart)
    }
}