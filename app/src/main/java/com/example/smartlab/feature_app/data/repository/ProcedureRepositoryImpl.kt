package com.example.smartlab.feature_app.data.repository

import com.example.smartlab.data.network.SupabaseInit.client
import com.example.smartlab.feature_app.domain.model.ListOfProcedures
import com.example.smartlab.feature_app.domain.repository.ProcedureRepository
import io.github.jan.supabase.postgrest.postgrest

class ProcedureRepositoryImpl: ProcedureRepository {

    override suspend fun getAllProcedure(): List<ListOfProcedures> {
        return client.postgrest["ListOfProcedures"].select().decodeList<ListOfProcedures>()
    }
}