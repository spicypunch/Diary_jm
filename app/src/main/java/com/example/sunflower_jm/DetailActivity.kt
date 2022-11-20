package com.example.sunflower_jm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sunflower_jm.databinding.DetailViewBinding
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.SunFlowerDao
import com.example.sunflower_jm.db.SunFlowerEntity
import kotlinx.android.synthetic.main.detail_view.*

class DetailActivity : AppCompatActivity(){
    //    private lateinit var datas : SunFlowerEntity
    lateinit var binding: DetailViewBinding
    lateinit var sunFlowerDao: SunFlowerDao
    lateinit var db : AppDatabase

    private var id = 0
    private var title = ""
    private var content = ""

//    private var id : Int = intent.getSerializableExtra("id") as Int
//    private var title : String = intent.getSerializableExtra("title") as String
//    private var content : String = intent.getSerializableExtra("content") as String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DetailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        sunFlowerDao = db.getSunFlowerDao()

        //Caused by: java.lang.ClassCastException: java.lang.Integer cannot be cast to com.example.sunflower_jm.db.SunFlowerEntity
//        datas = intent.getSerializableExtra("data") as SunFlowerEntity
//        val title = intent.getStringExtra("title")

        title = intent.getSerializableExtra("title") as String
        content = intent.getSerializableExtra("content") as String
        id = intent.getSerializableExtra("id") as Int

        detail_title.text = title
        detail_content.text = content

        binding.update.setOnClickListener {
            val intent = Intent(this, UpdateItemActivity::class.java).apply {
                putExtra("id", id)
            }
            startActivity(intent)
        }
    }
}