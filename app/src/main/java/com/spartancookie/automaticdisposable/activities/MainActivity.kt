package com.spartancookie.automaticdisposable.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.spartancookie.automaticdisposable.R
import com.spartancookie.automaticdisposable.databinding.ActivityMainBinding
import com.spartancookie.automaticdisposable.viewmodels.MyViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        mainViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        mainViewModel.context = this
        //mainViewModel.initialize(this)
        binding.viewModel = mainViewModel
    }
}