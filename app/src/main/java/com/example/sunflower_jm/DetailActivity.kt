package com.example.sunflower_jm

import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.sunflower_jm.databinding.DetailViewBinding
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.DiaryDao
import com.example.sunflower_jm.db.DiaryEntity

class DetailActivity : AppCompatActivity() {


    lateinit var binding: DetailViewBinding
    lateinit var diaryDao: DiaryDao
    lateinit var db: AppDatabase

    /*
    잘 동작하는지 확인하기 위해 새로 update한 값 중 title값만 가져오고
    값이 잘 넘어왔는지 Toast로 확인해보기 위해 작성한 코드
     */
    private val getList: ActivityResultLauncher<DiaryEntity> =
        registerForActivityResult(DetailActivityContract()) { result: ArrayList<String>? ->
            result?.let {
                Log.e("test",it[0] )
                binding.detailTitle.text = it[0]
                binding.detailContent.text = it[1]
            }
        }

    private lateinit var item: DiaryEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DetailViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        diaryDao = db.getDiaryDao()

        /*
        Serializable은 java의 표준 인터페이스로
        reflection을 사용함, 객체의 구체적인 클래스 형태를 모를 때 사용하게 됨
        생성되어 있는 객체를 통해 해당 객체의 클래스 정보를 분석해냄
        런타임에 데이터를 직렬화/역직렬화하는 과정에 많은 객체를 생성하고
        GC가 할 일이 늘어나게 됨
         */
        item = intent.getSerializableExtra("data") as DiaryEntity
        binding.detailTitle.text = item.title
        binding.detailContent.text = item.content

        binding.update.setOnClickListener {
//            val intent = Intent(this, UpdateItemActivity::class.java).apply {
//                putExtra("data", item)
//            }
//            startActivity(intent)

            //launch로 액티비티 실행
            getList.launch(item)
        }
    }
}