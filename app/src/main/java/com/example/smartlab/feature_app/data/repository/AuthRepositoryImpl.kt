
package com.example.smartlab.feature_app.data.repository


import com.example.smartlab.data.network.SupabaseInit.client
import com.example.smartlab.feature_app.domain.repository.AuthRepository
import com.example.smartlab.feature_app.domain.repository.UserData
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest

class AuthRepositoryImpl : AuthRepository {
    override suspend fun signIn(mail: String, pass: String) {
        client.auth.signInWith(Email){
            email = mail
            password = pass
        }
    }

    override suspend fun signUp(mail: String, pass: String, userData: UserData) {
        client.auth.signUpWith(Email){
            email = mail
            password = pass
        }
        client.postgrest["Users"].insert(mapOf("userData" to userData))
    }
}
