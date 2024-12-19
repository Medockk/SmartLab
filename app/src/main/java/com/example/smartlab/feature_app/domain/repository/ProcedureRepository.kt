package com.example.smartlab.feature_app.domain.repository

import com.example.smartlab.feature_app.domain.model.ListOfProcedures

interface ProcedureRepository {

    suspend fun getAllProcedure(): List<ListOfProcedures>
}