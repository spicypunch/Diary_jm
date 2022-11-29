package com.example.sunflower_jm

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.sunflower_jm.databinding.DetailViewBinding
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.SunFlowerDao
import com.example.sunflower_jm.db.SunFlowerEntity

class DetailActivity : AppCompatActivity() {

    lateinit var binding: DetailViewBinding
    lateinit var sunFlowerDao: SunFlowerDao
    lateinit var db: AppDatabase

    private lateinit var title: ActivityResultLauncher<String>
    private lateinit var content: ActivityResultLauncher<String>

    private lateinit var item: SunFlowerEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DetailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        sunFlowerDao = db.getSunFlowerDao()

        /*
        Serializable은 java의 표준 인터페이스로
        reflection을 사용함, 객체의 구체적인 클래스 형태를 모를 때 사용하게 됨
        생성되어 있는 객체를 통해 해당 객체의 클래스 정보를 분석해냄
        런타임에 데이터를 직렬화/역직렬화하는 과정에 많은 객체를 생성하고
        GC가 할 일이 늘어나게 됨
         */
        item = intent.getSerializableExtra("data") as SunFlowerEntity

        binding.detailTitle.text = item.title
        binding.detailContent.text = item.content

        binding.update.setOnClickListener {
//            val intent = Intent(this, UpdateItemActivity::class.java).apply {
//                putExtra("data", item)
//            }
//            startActivity(intent)
            title = registerForActivityResult(UpdateActivityContract) { result: String? ->
                result?.let {

                }
            }
        }
    }
}