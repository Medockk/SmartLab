package com.example.smartlab.feature_app.data.repository

import com.example.smartlab.data.network.SupabaseInit.client
import com.example.smartlab.feature_app.domain.model.ListOfProcedures
import com.example.smartlab.feature_app.domain.repository.SearchRepository
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.filter.TextSearchType

class SearchRepositoryImpl : SearchRepository {

    override suspend fun searchAnalyzes(query: String): List<ListOfProcedures> {

        val list = client.postgrest["ListOfProcedures"].select {
            filter {
                textSearch("name", query, TextSearchType.NONE)
            }
        }.decodeList<ListOfProcedures>()

        return list
    }
}