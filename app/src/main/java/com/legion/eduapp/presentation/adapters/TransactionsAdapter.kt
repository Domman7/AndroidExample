package com.legion.eduapp.presentation.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.legion.eduapp.App
import com.legion.eduapp.R
import com.legion.eduapp.databinding.RecyclerViewTransactionListItemBinding
import com.legion.eduapp.domain.TransactionEntity
import com.legion.eduapp.domain.enums.TransactionType
import java.security.AccessController.getContext

class TransactionsAdapter(
    private val transactions: List<TransactionEntity>
) : RecyclerView.Adapter<TransactionsAdapter.ViewHolder>()
{
    class ViewHolder(val binding: RecyclerViewTransactionListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewTransactionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            val transaction = transactions[position]
            when (transaction.type) {
                TransactionType.Income -> {
                    tvAmount.setTextColor(
                        ContextCompat.getColor(holder.itemView.context, R.color.green)
                    )
                    tvAmount.text = "+Р${transaction.amount}"
                }
                TransactionType.Expense -> {
                    tvAmount.setTextColor(
                        ContextCompat.getColor(holder.itemView.context, R.color.red)
                    )
                    tvAmount.text = "-Р${transaction.amount}"
                }
            }
            tvTitle.text = transaction.name
            //Glide
                //.with(ivItem.context)
                //.load(transaction.url)
                //.dontAnimate()
                //.into(ivItem)
                //.onLoadFailed(R.drawable.profile_avatar)
        }
    }

    override fun getItemCount(): Int {
        return transactions.count()
    }

}