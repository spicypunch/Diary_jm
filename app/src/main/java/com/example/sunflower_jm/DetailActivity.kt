package com.example.sunflower_jm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.example.sunflower_jm.databinding.DetailViewBinding
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.SunFlowerDao
import com.example.sunflower_jm.db.SunFlowerEntity

class DetailActivity : AppCompatActivity() {

    companion object {
        const val KEY_ID = "id"
    }

    lateinit var binding: DetailViewBinding
    lateinit var sunFlowerDao: SunFlowerDao
    lateinit var db: AppDatabase

    /*
    잘 동작하는지 확인하기 위해 새로 update한 값 중 title값만 가져오고
    값이 잘 넘어왔는지 Toast로 확인해보기 위해 작성한 코드
     */
    private val getTest: ActivityResultLauncher<SunFlowerEntity> =
        registerForActivityResult(DetailActivityContract()) { result: String? ->
            result?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }

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

        /*
        get_Test의 변수가 null이 아닐 경우 get_Test의 값을 적용
        하지만 if (get_Test != null)가 딱 봐도 잘 안 돌아 갈 것 같습니다..
        값이 잘 넘어오는지 확인 되면 조건문 수정하겠습니다.
        현재는 주석처리

        if (get_Test != null) {
            binding.detailTitle.text = get_Test.toString()
        } else {
         */
            binding.detailTitle.text = item.title
            binding.detailContent.text = item.content
//        }

        binding.update.setOnClickListener {
//            val intent = Intent(this, UpdateItemActivity::class.java).apply {
//                putExtra("data", item)
//            }
//            startActivity(intent)

            //launch로 액티비티 실행
            getTest.launch(item)
        }
    }
}