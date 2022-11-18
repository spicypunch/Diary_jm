package com.example.sunflower_jm

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sunflower_jm.databinding.ItemBinding
import com.example.sunflower_jm.db.SunFlowerEntity

class RecyclerViewAdapter(private val itemList: ArrayList<SunFlowerEntity>) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    class MyViewHolder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val item_title = binding.itemTitle
        val item_content = binding.itemContent
        val root = binding.root

        //detailview 페이지로 이동
        fun bind(item: SunFlowerEntity) {
//            Log.e("잘 나오고 있어요~~~~~~~~~~~", "잘 나오고 있어요@")
            item_title.text = item.title
            item_content.text = item.content

            itemView.setOnClickListener {
                Intent(root.context, DetailActivity::class.java).apply {
                    putExtra("data", item.id)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
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
        val sunflowerData = itemList[position]
        holder.item_title.text = sunflowerData.title
        holder.item_content.text = sunflowerData.content
    }

    override fun getItemCount(): Int = itemList.size
}