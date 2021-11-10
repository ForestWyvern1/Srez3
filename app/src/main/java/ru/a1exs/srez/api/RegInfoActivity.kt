package ru.a1exs.srez.api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import ru.a1exs.srez.MainActivity
import ru.a1exs.srez.R
import ru.a1exs.srez.adapters.TopicsAdapter
import ru.a1exs.srez.api.models.TopicsModel
import ru.a1exs.srez.databinding.ActivityRegInfoBinding

class RegInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topicsRv.layoutManager = GridLayoutManager(this, 3)

        val list = ArrayList<TopicsModel>()
        for (i in 1..10){
            list.add(TopicsModel(i.toString(), "Title $i"))
        }
        binding.topicsRv.adapter = TopicsAdapter(this, list)

        binding.buttonSave.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.avatar.setOnClickListener {
            binding.avatar.setImageResource(R.drawable.pressed_avatar)
        }
    }
}