package com.legion.eduapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.legion.eduapp.domain.enums.TransactionType

@Entity
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var categoryId: Int,
    var name: String,
    val amount: Double,
    var type: TransactionType,
    val createdAt: Long,
    var url: String?
)