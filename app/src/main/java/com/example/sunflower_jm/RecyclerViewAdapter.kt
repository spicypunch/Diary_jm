package com.example.sunflower_jm

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sunflower_jm.databinding.ItemBinding
import com.example.sunflower_jm.db.DiaryEntity
import com.example.sunflower_jm.detail.DetailActivity

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

    class MyViewHolder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val item_title = binding.itemTitle
        val item_content = binding.itemContent
        val root = binding.root

        fun bind(item: DiaryEntity) {
            item_title.text = item.title
            item_content.text = item.content
            Log.e("title, content", item.title)

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
        val diaryData = itemList[position]
        holder.item_title.text = diaryData.title
        holder.item_content.text = diaryData.content
        holder.bind(diaryData)

        holder.root.setOnLongClickListener {
            listener.onLongClick(diaryData)
            false
        }
    }

    override fun getItemCount(): Int = itemList.size
}