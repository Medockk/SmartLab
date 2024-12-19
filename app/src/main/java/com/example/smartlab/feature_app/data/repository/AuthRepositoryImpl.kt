package com.example.smartlab.feature_app.data.repository

import com.example.smartlab.data.network.SupabaseInit.client
import com.example.smartlab.feature_app.domain.repository.AuthRepository
import com.example.smartlab.feature_app.domain.model.UserData
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class AuthRepositoryImpl : AuthRepository {
    override suspend fun signIn(mail: String, pass: String) {
        client.auth.signInWith(Email){
            this.email = mail
            this.password = pass
        }
    }

    override suspend fun signUp(mail: String, pass: String, userData: UserData) {
        client.auth.signUpWith(Email){
            email = mail
            password = pass + "00"
        }
        client.postgrest["Users"].insert(
            mapOf(
                "name" to userData.name,
                "surname" to userData.surname,
                "patronymic" to userData.patronymic,
                "birthdayData" to userData.birthdayData,
                "gender" to userData.gender,
                "userID" to client.auth.currentUserOrNull()?.id
            )
        )
    }

    override suspend fun getUserData(): List<UserData> {
        val userId = client.auth.currentUserOrNull()?.id
        return client.postgrest["Users"].select(columns = Columns.ALL){
            filter {
                eq("userID", userId?:"")
            }
        }.decodeList<UserData>()
    }

    override suspend fun signOut() {
        client.auth.signOut()
    }

    override suspend fun updateUserData(userData: UserData) {
        val userID = client.auth.currentUserOrNull()?.id
        client.postgrest["Users"].update(
            mapOf(
                "name" to userData.name,
                "surname" to userData.surname,
                "patronymic" to userData.patronymic,
                "birthdayData" to userData.birthdayData,
                "gender" to userData.gender,
            )
        ) {
            filter {
                eq("userID", userID?:"")
            }
        }
    }
}
