package com.legion.eduapp.domain


fun com.legion.eduapp.data.local.entity.TransactionEntity.toDomain(): TransactionEntity {
    return TransactionEntity(
        id = this.id,
        categoryId = this.categoryId,
        name = this.name,
        amount = this.amount,
        type = this.type,
        createdAt = this.createdAt,
        url=this.url
    )
}

fun TransactionEntity.toLocal(): com.legion.eduapp.data.local.entity.TransactionEntity {
    return com.legion.eduapp.data.local.entity.TransactionEntity(
        id = this.id,
        categoryId = this.categoryId,
        name = this.name,
        amount = this.amount,
        type = this.type,
        createdAt = this.createdAt,
        url=this.url
    )
}
