package com.example.sunflower_jm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sunflower_jm.databinding.UpdateItemBinding
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.SunFlowerDao
import com.example.sunflower_jm.db.SunFlowerEntity

class UpdateItemActivity() : AppCompatActivity() {

    lateinit var binding: UpdateItemBinding
    lateinit var db : AppDatabase
    lateinit var sunFlowerDao: SunFlowerDao
    private lateinit var adapter: RecyclerViewAdapter

    private lateinit var item : SunFlowerEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UpdateItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        sunFlowerDao = db.getSunFlowerDao()

        binding.btnUpdateCompletion.setOnClickListener {
            updateItem()
        }

        item = intent.getSerializableExtra("data") as SunFlowerEntity
        binding.editTitle.text = Editable.Factory.getInstance().newEditable(item.title)
        binding.editContent.text = Editable.Factory.getInstance().newEditable(item.content)

    }

    private fun updateItem() {
        val id =  item.id
        val itemTitle = binding.editTitle.text.toString()
        val itemContent = binding.editContent.text.toString()

        Log.e("title check", itemTitle)
        Log.e("content check", itemContent)

        if(itemTitle.isBlank() || itemContent.isBlank()) {
            Toast.makeText(this, "모든 항목을 채워주세요!!", Toast.LENGTH_SHORT).show()
        } else {
            Thread {
                sunFlowerDao.updateItem(SunFlowerEntity(id, itemTitle, itemContent))
                runOnUiThread {
                    adapter.notifyDataSetChanged()
                    Toast.makeText(this, "수정되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.start()
        }
        setResult(Activity.RESULT_OK, Intent().apply {
            putExtra("title", itemTitle)
            putExtra("content", itemContent)})

        finish()
    }
}