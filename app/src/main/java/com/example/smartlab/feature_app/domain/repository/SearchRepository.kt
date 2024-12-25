package com.example.smartlab.feature_app.domain.repository

import com.example.smartlab.feature_app.domain.model.ListOfProcedures

interface SearchRepository {

    suspend fun searchAnalyzes(query: String) : List<ListOfProcedures>
}