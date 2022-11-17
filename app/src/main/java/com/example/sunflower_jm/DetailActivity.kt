package com.example.sunflower_jm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sunflower_jm.db.SunFlowerEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail_view.*

class DetailActivity : AppCompatActivity(){
    private lateinit var datas : SunFlowerEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_view)

        datas = intent.getSerializableExtra("data") as SunFlowerEntity

        detail_title.text = datas.title.toString()
        detail_content.text = datas.content.toString()
    }

}