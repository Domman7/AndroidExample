package com.legion.eduapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceControl.Transaction
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.legion.eduapp.R
import com.legion.eduapp.databinding.FragmentCreateTransactionBinding
import com.legion.eduapp.domain.enums.TransactionType
import com.legion.eduapp.presentation.viewmodels.TransactionsFragmentViewModel
import com.legion.eduapp.utils.parseDouble
import com.legion.eduapp.utils.snack
import com.legion.eduapp.utils.transformIntoDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CreateTransactionFragment : Fragment() {

    companion object {
        private val transactionType = listOf("Income", "Expense")
    }

    private var _binding: FragmentCreateTransactionBinding? = null
    private val binding: FragmentCreateTransactionBinding get() = _binding!!

    private val viewModel: TransactionsFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transactionTypeAdapter = ArrayAdapter(
            requireContext(),
            R.layout.item_autocomplete_layout,
            transactionType
        )

        with(binding) {
            etTransactionType.setAdapter(transactionTypeAdapter)

            // transform TextInputEditText into DatePicker
            etWhen.transformIntoDatePicker(
                requireContext(),
            "dd/MM/yyyy",
                Date(),
            )

            btnSaveTransaction.setOnClickListener {
                binding.apply {
                    val title = binding.etTitle.text.toString()
                    val amount = parseDouble(binding.etAmount.text.toString())
                    val transactionType = binding.etTransactionType.text.toString()
                    val date = binding.etWhen.text.toString()
                    val url = binding.etUrl.text.toString()

                    when {
                        title.isEmpty() -> {
                            this.etTitle.error = "Title must not be empty"
                        }
                        amount.isNaN() -> {
                            this.etAmount.error = "Amount must not be empty"
                        }
                        transactionType.isEmpty() -> {
                            this.etTransactionType.error = "Transaction type must not be empty"
                        }
                        date.isEmpty() -> {
                            this.etWhen.error = "Date must not be empty"
                        }
                        url.isEmpty() -> {
                            this.etUrl.error = "Url must not be empty"
                        }
                        else -> {
                            viewModel.insertTransaction(
                                name = title,
                                amount = amount,
                                type = TransactionType.valueOf(transactionType),
                                url = url
                            ).run {
                                binding.root.snack(
                                    string = R.string.successfully_saved
                                )

                                val fragment = TransactionsFragment()
                                parentFragmentManager
                                    .beginTransaction()
                                    .replace(R.id.fragment_container_view, fragment)
                                    .commit()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}