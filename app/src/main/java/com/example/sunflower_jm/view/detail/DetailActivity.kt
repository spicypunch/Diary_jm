package com.example.sunflower_jm.view.detail

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.sunflower_jm.R
import com.example.sunflower_jm.databinding.DetailViewBinding
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.DiaryDao
import com.example.sunflower_jm.db.model.DiaryEntity
import com.example.sunflower_jm.view.update.ActivityContract

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: DetailViewBinding
    private lateinit var diaryDao: DiaryDao
    private lateinit var db: AppDatabase
    private lateinit var item: DiaryEntity

    private val getList: ActivityResultLauncher<DiaryEntity> =
        registerForActivityResult(ActivityContract()) { result: HashMap<String, String>? ->
            result?.let {
                if (it.get("image") != "null") {
                    binding.detailImage.setImageURI(Uri.parse(it.get("image")))
                    item.image = it.get("image")
                }

                binding.detailTitle.text = it.get("title")
                binding.detailContent.text = it.get("content")

                item.title = it.get("title")!!
                item.content = it.get("content")!!
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.detail_view)

        db = AppDatabase.getInstance(this)!!
        diaryDao = db.getDiaryDao()

        item = intent.getSerializableExtra("data") as DiaryEntity
        binding.data = item

        binding.update.setOnClickListener {
            getList.launch(item)
        }
    }
}