package com.example.sunflower_jm.view.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.sunflower_jm.db.model.DiaryEntity

class DiffUtilCallback(
    private val oldList: MutableList<DiaryEntity>,
    private val newList: MutableList<DiaryEntity>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

}