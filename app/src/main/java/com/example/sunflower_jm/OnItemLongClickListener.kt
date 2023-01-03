package com.example.sunflower_jm

import com.example.sunflower_jm.db.DiaryEntity

interface OnItemLongClickListener {
    fun onLongClick(item: DiaryEntity)
}