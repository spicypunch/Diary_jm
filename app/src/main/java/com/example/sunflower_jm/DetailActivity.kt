package com.example.sunflower_jm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sunflower_jm.databinding.DetailViewBinding
import com.example.sunflower_jm.db.SunFlowerEntity
import kotlinx.android.synthetic.main.detail_view.*

class DetailActivity : AppCompatActivity(){
//    private lateinit var datas : SunFlowerEntity
    private lateinit var binding: DetailViewBinding
    private var title : String = ""
    private var content : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Caused by: java.lang.ClassCastException: java.lang.Integer cannot be cast to com.example.sunflower_jm.db.SunFlowerEntity
//        datas = intent.getSerializableExtra("data") as SunFlowerEntity
//        val title = intent.getStringExtra("title")

        title =intent.getSerializableExtra("title") as String
        content =intent.getSerializableExtra("content") as String

        detail_title.text = title
        detail_content.text = content
    }

}