package com.example.sunflower_jm

import android.app.Activity
import android.content.Context
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

class UpdateItemActivity : AppCompatActivity() {

    lateinit var binding: UpdateItemBinding
    lateinit var db : AppDatabase
    lateinit var sunFlowerDao: SunFlowerDao

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

        item = intent.getSerializableExtra(KEY_DATA) as SunFlowerEntity
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
                    Toast.makeText(this, "수정되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.start()
        }
        //수정된 제목 값 다시 넘기기
        setResult(Activity.RESULT_OK, Intent().apply { putExtra("result", itemTitle) })

    }

    companion object {

        private const val KEY_DATA = "key-data"

        fun getIntent(context: Context, data: SunFlowerEntity): Intent =
            Intent(context, UpdateItemActivity::class.java).apply {
                putExtra(KEY_DATA, data)
            }
    }
}