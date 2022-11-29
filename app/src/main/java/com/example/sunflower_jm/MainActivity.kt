package com.example.sunflower_jm

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sunflower_jm.databinding.ActivityMainBinding
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.SunFlowerDao
import com.example.sunflower_jm.db.SunFlowerEntity

/*
AppCompatActivity
안드로이드의 하위 버전을 지원하는 Activity의 일종
하위 버전과의 호환성을 고려하기 위해 사용

OnItemLongClickListener
한 항목을 오래 누르는 거을 감지하여 자동으로 콜백 메소드를 호출하는 리스너
 */
class MainActivity : AppCompatActivity(), OnItemLongClickListener {

    /*
    lateinit
    초기화를 미루는 방법, 프로퍼티 선언과 동시에 초기화하지 않아도 됨
    var로 선언한 프로퍼티에만 사용할 수 있음
    클래스 몸체, Top-Level, 함수 내부에 선언한 프로퍼티에 사용할 수 있음
    null 허용 프로퍼티에는 사용할 수 없음
    기초 타입 프로퍼티에는 사용할 수 없음
    */
    private lateinit var binding: ActivityMainBinding
    private lateinit var db: AppDatabase
    private lateinit var sunflowerDao: SunFlowerDao
    private lateinit var sunflowerList: ArrayList<SunFlowerEntity>
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        intent
        앱 컴포넌트가 무엇을 할 것인지를 담는 메시지 객체
        다른 액티비티, 서비스, 브로드캐스트 리시버, 컨탠트 프로바이더 등 그들 사이에 데이터를 주고 받기 위한 용도로 쓰임

        액티비티
        안드로이드 앱의 화면을 담당하는 컴포넌트, 화면에 보이지 않을 때에는 실행이 되지 않는 것이 원칙

        서비스
        액티비티의 실행 여부와 상관없이 백그라운드로 작동하는 컴포넌트, 액티비티는 서비스를 실행할 수 있음, 액티비티가 다른 것에 가려지더라도 서비스는 계속 작동
        외부 앱에서 실행할 수 있는 서비스를 원격 서비스, 특정한 앱 내에서만 작동하는 서비스를 지역 서비스라고 함

        브로드캐스트 리시버
        모든 앱들이 받을 수 있는 메시지, 안드로이드에서 시스템 이벤트가 발생할 때, 안드로이드는 앱들에게 브로드캐스트를 보냄
        브로드캐스트 리시버는 안드로이드가 보내는 브로드캐스트를 받고 이에 대응하는 어떤 작업을 수행할 수 있는 컴포넌트
        ex) 부팅이 완료됐을 때, 화면이 켜질 때, 화면이 꺼질 때, 날짜가 변경될 때, 시간이 변경될 때, 외장 메모리를 꽂거나 뺄 때

        컨텐트 프로바이더
        앱들이 공유 할 수 있도록 자료를 제공하는 앱 컴포넌트
        메소드들을 호출하여 컨텐트 프로바이더의 자료에 접근 할 수 있음
        */
       binding.fab.setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)
        }

        db = AppDatabase.getInstance(this)!!
        sunflowerDao = db.getSunFlowerDao()

        getAllItemList()
    }
    /*
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
    private fun getAllItemList() {
        Thread {
            sunflowerList = ArrayList(sunflowerDao.getAll())
            Log.e("check", sunflowerList.toString())
            setRecyclerView()
        }.start()
    }
    /*
    adapter
    간단히 말해 데이터와 리스트 뷰 사이의 통신을 위한 다리 역할을 함
    하나의 객체로서 보여지는 view와 그 view에 올릴 Data를 연결하는 일종의 Bridge임

    adapter view
    많은 정보를 효과적으로 처리하기 위해, view에 직접 정보를 주입하지 않고, Adapter라는 중간 매개체를 이용함
    내부적으로 많은 뷰들을 담을 수 있고 대표적으로 ListView, GridView, Spinner, Gallery 둥이 있음
    */
    private fun setRecyclerView() {
        runOnUiThread {
            adapter = RecyclerViewAdapter(sunflowerList, this)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        }
    }
    /*
    AlertDialog
    전체 화면을 가리지 않으면서 사용자의 응답이나 추가 정보를 입력하도록 하는 창

    builder
    AlertDialog에 여러 정보를 설정하기 위한 객체

    DialogInterface
    사용자가 버튼을 눌렀을 때 실행할 작업을 정의
    */
    override fun onLongClick(position: Int) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("할 일 삭제")
        builder.setMessage("정말 삭제하시겠습니까?")
        builder.setNegativeButton("취소", null)
        builder.setPositiveButton("네", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                deleteItem(position)
            }
        })
        builder.show()
    }

    private fun deleteItem(position: Int) {
        Thread {
            sunflowerDao.deleteItem(sunflowerList[position])
            sunflowerList.removeAt(position)
            runOnUiThread {
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    override fun onRestart() {
        super.onRestart()
        getAllItemList()
    }
}