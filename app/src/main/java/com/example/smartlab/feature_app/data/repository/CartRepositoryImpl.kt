package com.example.smartlab.feature_app.data.repository

import com.example.smartlab.data.network.SupabaseInit.client
import com.example.smartlab.feature_app.domain.model.Cart
import com.example.smartlab.feature_app.domain.repository.CartRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.postgrest

class CartRepositoryImpl : CartRepository {

    override suspend fun addItemInCart(procedure: Cart): Boolean {
        val userID = client.auth.currentUserOrNull()?.id
        client.postgrest["Cart"].insert(
            mapOf(
                "userID" to userID,
                "procedure" to procedure.procedure,
                "price" to procedure.price,
            )
        )

        return true
    }

    override suspend fun removeItemFromCart(procedure: Cart): Boolean {
        val userID = client.auth.currentUserOrNull()?.id
        client.postgrest["Cart"].delete {
            filter {
                eq("userID", userID ?: "")
                eq("procedure", procedure.procedure)
            }
        }
        return true
    }

    override suspend fun getAllUserItemFromCart(): List<Cart> {
        val userID = client.auth.currentUserOrNull()?.id
        return client.postgrest["Cart"].select {
            filter {
                eq("userID", userID ?: "")
            }
        }.decodeList<Cart>()
    }

    override suspend fun removeAllItemFromCart(): Boolean {
        val userID = client.auth.currentUserOrNull()?.id

        client.postgrest["Cart"].delete {
            filter {
                eq("userID", userID ?: "")
            }
        }

        return true
    }
}