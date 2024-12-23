package com.example.smartlab.feature_app.domain.usecase.Procedure

import com.example.smartlab.feature_app.domain.model.ListOfProcedures
import com.example.smartlab.feature_app.domain.repository.ProcedureRepository

class GetAllProcedureUseCase(
    private val procedureRepository: ProcedureRepository
) {

    suspend operator fun invoke(): List<ListOfProcedures> {
        return procedureRepository.getAllProcedure()
    }
}