package com.example.sunflower_jm

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.DiaryDao
import com.example.sunflower_jm.databinding.AddItemBinding
import com.example.sunflower_jm.db.DiaryEntity

/*
AppCompatActivity
안드로이드의 하위 버전을 지원하는 Activity의 일종
하위 버전과의 호환성을 고려하기 위해 사용
 */
class AddItemActivity : AppCompatActivity() {

    /*
    lateinit
    초기화를 미루는 방법, 프로퍼티 선언과 동시에 초기화하지 않아도 됨
    var로 선언한 프로퍼티에만 사용할 수 있음
    클래스 몸체, Top-Level, 함수 내부에 선언한 프로퍼티에 사용할 수 있음
    null 허용 프로퍼티에는 사용할 수 없음
    기초 타입 프로퍼티에는 사용할 수 없음
    */
    lateinit var binding: AddItemBinding
    lateinit var db : AppDatabase
    lateinit var diaryDao: DiaryDao
    private lateinit var adapter: RecyclerViewAdapter

    /*
    onCreate
    Activity가 생성될 때 한 번 실행되는 메소드
    Bundle은 여러가지 타입을 저장하는 Map 클래스
    Activity간 데이터를 주고받을 때 Bundle 클래스를 사용해 다양한 데이터를 전송
    Activity를 중단하면 savedInstanceState를 호출하여 데이터를 임시 저장함
    다시 동작을 하게되면 저장된 데이터를 가지고 다시 Activity를 생성한다.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        inflate(전개)
        xml에 표기된 레이아웃들을 메모리에 올리는 과정

        layoutInflater
        미리 정의해둔 틀을 실제 메모리에 올려주는 역할

        setContenView()
        화면에 보이는 역할을 함
         */
        binding = AddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        AppDatabase.kt을 통해 DB 정보를 가져옴
         */
        db = AppDatabase.getInstance(this)!!
        diaryDao = db.getDiaryDao()

        /*
        btn_completion을 클릭 시 insertItem()을 실행
         */
        binding.btnCompletion.setOnClickListener {
            insertItem()
        }
    }

    private fun insertItem() {

        /*
        xml에 EditText에 기입된 문자열 변수에 넣음
         */
        val itemTitle = binding.editTitle.text.toString()
        val itemContent = binding.editContent.text.toString()

        /*
        만약 가져온 변수의 값이 비어있을 때 메시지를 출력함

        Toast
        사용자에게 짧은 메시지 형식으로 정보를 전달하는 팝업
        시간이 지나면 자동으로 사라지는 메시지

        Toast.LENGTH_SHORT
        짧게 Toast 메시지를 표시함

        show()
        사용자에게 보여줌

        Thread
        앱이 처음 시작될 때 시스템이 스레드 하나를 생성하는데 이를 메인 스레드라고 함
        메인 스레드의 역할은
        1. 액티비티의 모든 생명 주기 관련 콜백 실행을 담당함
        2. 버튼, 에디트 텍스트화 같은 UI 위젯을 사용한 사용자 이벤트와 UI 드로잉 이벤트를 담당
        긴 시간이 걸리는 작업을 모두 메인 스레드 큐에 넣고 작업하면 한 작업이 끝날 때까지 다른 작업을 처리하지 못함
        
        백그라운드 스레드를 활용하면 이러한 먹통 현상을 피할 수 있음
        복잡한 연산이나, 네트워크 작업, 데이터 베이스 작업 등을 해주면 됨
        추가로 절대로 UI 관련 작업을 백그라운드 스레드에서 하면 안 됨
        UI처리 순서를 알 수 없기 때문에
        따라서 runOnUiThread을 통해 UI 작업을 함
        */
        if(itemTitle.isBlank() || itemContent.isBlank()) {
            Toast.makeText(this, "모든 항목을 채워주세요!!", Toast.LENGTH_SHORT).show()
        } else {
          Thread {
              diaryDao.insertItem(DiaryEntity(null, itemTitle, itemContent))
              runOnUiThread {
                  Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show()
                  finish()
              }
          }.start()
        }
    }


}