package com.example.sunflower_jm.view.main.adapter

import com.example.sunflower_jm.db.model.DiaryEntity

interface OnItemLongClickListener {
    fun onLongClick(item: DiaryEntity)
}