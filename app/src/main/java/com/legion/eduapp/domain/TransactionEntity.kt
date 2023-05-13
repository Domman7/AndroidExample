package com.legion.eduapp.domain

import com.legion.eduapp.domain.enums.TransactionType
import java.util.*

data class TransactionEntity(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val amount: Double,
    val type: TransactionType,
    val createdAt: Long,
    val url: String?
)