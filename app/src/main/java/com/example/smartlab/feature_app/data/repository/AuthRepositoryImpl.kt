package com.example.smartlab.feature_app.data.repository

import android.util.Log
import com.example.smartlab.data.network.SupabaseInit.client
import com.example.smartlab.feature_app.domain.repository.AuthRepository
import com.example.smartlab.feature_app.domain.model.UserData
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.providers.builtin.OTP
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns

class AuthRepositoryImpl : AuthRepository {
    override suspend fun signIn(mail: String, pass: String): Boolean {
        client.auth.signInWith(Email){
            this.email = mail
            this.password = pass
        }
        return true
    }

    override suspend fun signUp(mail: String, pass: String, userData: UserData): Boolean {
        client.auth.signUpWith(Email){
            email = mail
            password = pass
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
        return true
    }

    override suspend fun sentOTP(mail: String) {
        client.auth.signInWith(OTP){
            this.email = mail
        }
    }

    override suspend fun verifyEmail(mail: String, tokenOTP: String) {
        client.auth.verifyEmailOtp(
            type = OtpType.Email.MAGIC_LINK,
            email = mail,
            token = tokenOTP
        )
    }

    override suspend fun getUserData(): List<UserData> {
        val userId = client.auth.currentUserOrNull()?.id
        Log.e("userID", "$userId")
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
                "gender" to userData.gender
            )
        ) {
            filter {
                eq("userID", userID?:"")
            }
        }
    }

    override suspend fun updateUserAddress(newAddress: String) {
        val userID = client.auth.currentUserOrNull()?.id
        client.postgrest["Users"].update(
            mapOf(
                "address" to newAddress
            )
        ){
            filter {
                eq("userID", userID?:"")
            }
        }
    }
}
