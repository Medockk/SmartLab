package com.example.smartlab.feature_app.data.network

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseInit {
    val client = createSupabaseClient(
        supabaseUrl = "",
        supabaseKey = ""
    ){
        install(Auth)
        install(Storage)
        install(Postgrest)
    }
}