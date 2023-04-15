package com.example.sunflower_jm.view.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sunflower_jm.databinding.ItemBinding
import com.example.sunflower_jm.db.model.DiaryEntity
import com.example.sunflower_jm.view.detail.DetailActivity

class RecyclerViewAdapter(private val listener : OnItemLongClickListener) :
    ListAdapter<DiaryEntity, RecyclerViewAdapter.MyViewHolder>(diffUtil){

    class MyViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val root = binding.root

        fun bind(item: DiaryEntity) {
            binding.data = item
            itemView.setOnClickListener {
                Intent(root.context, DetailActivity::class.java).apply {
                    putExtra("data", item)
//                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { root.context.startActivity(this) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemBinding =
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<DiaryEntity>() {

            override fun areItemsTheSame(oldItem: DiaryEntity, newItem: DiaryEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DiaryEntity, newItem: DiaryEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}