package com.example.a151124_practic

import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.a151124_practic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        val ViewModel = ViewModelProvider(this).get(CustomViewModel::class.java)
        ViewModel.initContext(this)
        binding.viewModel = ViewModel
        binding.lifecycleOwner = this
    }
}