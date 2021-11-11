package ru.a1exs.srez.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import ru.a1exs.srez.R
import ru.a1exs.srez.common.adapters.TopicsAdapter
import ru.a1exs.srez.common.api.models.TopicsModel
import ru.a1exs.srez.common.base.BaseActivity
import ru.a1exs.srez.databinding.ActivityRegInfoBinding

class RegInfoActivity : BaseActivity() {
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