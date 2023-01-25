package com.example.sunflower_jm.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sunflower_jm.DiffUtilCallback
import com.example.sunflower_jm.OnItemLongClickListener
import com.example.sunflower_jm.databinding.ItemBinding
import com.example.sunflower_jm.db.DiaryEntity
import com.example.sunflower_jm.activity.DetailActivity

class RecyclerViewAdapter(private val listener : OnItemLongClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private val itemList = mutableListOf<DiaryEntity>()

    fun updateList(items: MutableList<DiaryEntity>) {
        val diffCallback = DiffUtilCallback(itemList, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        itemList.clear()
        itemList.addAll(items)

        diffResult.dispatchUpdatesTo(this)
    }

    class MyViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val root = binding.root
//        val image: ImageView = binding.itemImage

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
        holder.bind(itemList[position])

//        holder.apply {
//            Glide.with(root.context).load(itemList[position].image)
//                .into(image)
//        }

        holder.root.setOnLongClickListener {
            listener.onLongClick(itemList[position])
            false
        }
    }

    override fun getItemCount(): Int = itemList.size
}