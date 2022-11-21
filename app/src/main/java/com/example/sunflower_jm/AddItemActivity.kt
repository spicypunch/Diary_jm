package com.example.sunflower_jm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.SunFlowerDao
import com.example.sunflower_jm.databinding.AddItemBinding
import com.example.sunflower_jm.db.SunFlowerEntity

class AddItemActivity : AppCompatActivity() {

    lateinit var binding: AddItemBinding
    lateinit var db : AppDatabase
    lateinit var sunFlowerDao: SunFlowerDao
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = AddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        sunFlowerDao = db.getSunFlowerDao()

        binding.btnCompletion.setOnClickListener {
            insertItem()
        }

    }

    private fun insertItem() {
        val itemTitle = binding.editTitle.text.toString()
        val itemContent = binding.editContent.text.toString()

        if(itemTitle.isBlank() || itemContent.isBlank()) {
            Toast.makeText(this, "모든 항목을 채워주세요!!", Toast.LENGTH_SHORT).show()
        } else {
          Thread {
              sunFlowerDao.insertItem(SunFlowerEntity(null, itemTitle, itemContent))
              runOnUiThread {
                  adapter.notifyDataSetChanged()
                  Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show()
                  finish()
              }
          }.start()
        }
    }


}