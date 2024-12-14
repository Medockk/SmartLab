package com.example.smartlab.data.network

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseInit {
    val client = createSupabaseClient(
        supabaseUrl = "https://fvfsrvbjwpzimwzrvndx.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZ2ZnNydmJqd3B6aW13enJ2bmR4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzQxNjA2MjgsImV4cCI6MjA0OTczNjYyOH0.awK34HV4XnHeAvO_iFMnVcjzw40wwwmBP4-HnFLat7E"
    ){
        install(Auth)
        install(Storage)
        install(Postgrest)
    }
}