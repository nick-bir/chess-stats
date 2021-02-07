package com.example.chessstats.model.params

data class SqlFilterParams(
        val filters: List<String>,
        val params: Map<String, Any>
)