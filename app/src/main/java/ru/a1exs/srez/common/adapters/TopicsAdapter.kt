package ru.a1exs.srez.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.a1exs.srez.R
import ru.a1exs.srez.common.api.models.TopicsModel
import ru.a1exs.srez.databinding.TopicsRvBinding

class TopicsAdapter(private val context: Context, private val listTopics : ArrayList<TopicsModel>) : RecyclerView.Adapter<TopicsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.topics_rv, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = TopicsRvBinding.bind(holder.itemView)

        binding.itemText.text = listTopics[position].title


    }

    override fun getItemCount(): Int = listTopics.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}