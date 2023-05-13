package com.legion.eduapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.legion.eduapp.data.local.FinanceManagerDatabase
import com.legion.eduapp.data.local.FinanceManagerDatabase_Impl
import com.legion.eduapp.databinding.ActivityMainBinding
import com.legion.eduapp.presentation.fragments.WelcomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<WelcomeFragment>(binding.fragmentContainerView.id)
            }
        }
    }
}