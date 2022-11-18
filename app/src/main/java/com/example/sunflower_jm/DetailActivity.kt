package com.example.sunflower_jm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sunflower_jm.databinding.DetailViewBinding
import com.example.sunflower_jm.databinding.ItemBinding
import com.example.sunflower_jm.db.SunFlowerEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail_view.*

class DetailActivity : AppCompatActivity(){
    private lateinit var datas : SunFlowerEntity
    private lateinit var binding: DetailViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        datas = intent.getSerializableExtra("data") as SunFlowerEntity

        detail_title.text = datas.title
        detail_content.text = datas.content

//        intent.getStringExtra("title")
//        intent.getStringExtra("content")
    }

}