package com.example.smartlab.feature_app.domain.usecase.Search

import com.example.smartlab.feature_app.domain.model.ListOfProcedures
import com.example.smartlab.feature_app.domain.repository.SearchRepository

class SearchAnalyzesUseCase(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(query: String) : List<ListOfProcedures>{
        return searchRepository.searchAnalyzes(query)
    }
}