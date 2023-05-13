package com.legion.eduapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.legion.eduapp.data.local.FinanceManagerDatabase
import com.legion.eduapp.domain.TransactionEntity
import com.legion.eduapp.domain.enums.TransactionType
import com.legion.eduapp.domain.toLocal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsFragmentViewModel @Inject constructor(
    private val db: FinanceManagerDatabase
) : ViewModel() {

    val transactions = db.transactionDao.getAllTransactions()

    fun insertTransaction(
        name: String,
        amount: Double,
        type: TransactionType,
        url: String?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val transaction = TransactionEntity(
                id = 0,
                categoryId = 0,
                name = name,
                amount = amount,
                type = type,
                createdAt = System.currentTimeMillis(),
                url = url
            )
            val id = db.transactionDao.insertTransaction(transaction.toLocal())
        }
    }

}