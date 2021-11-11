package ru.a1exs.srez.controller

import android.os.Bundle
import ru.a1exs.srez.common.base.BaseActivity
import ru.a1exs.srez.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}