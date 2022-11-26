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

class MainActivity : AppCompatActivity(), OnItemLongClickListener {
    private lateinit var binding: ActivityMainBinding

    private lateinit var db: AppDatabase
    private lateinit var sunflowerDao: SunFlowerDao
    private lateinit var sunflowerList: ArrayList<SunFlowerEntity>
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)
        }

//        if (savedInstanceState != null) {
//            textView.text = savedInstanceState.getString("")
//        }

        db = AppDatabase.getInstance(this)!!
        sunflowerDao = db.getSunFlowerDao()

        getAllItemList()
    }

    private fun getAllItemList() {
        Thread {
            sunflowerList = ArrayList(sunflowerDao.getAll())
            Log.e("check", sunflowerList.toString())
            setRecyclerView()
        }.start()
    }

    private fun setRecyclerView() {
        runOnUiThread {
            //this의 의미?
            adapter = RecyclerViewAdapter(sunflowerList, this)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        }
    }

    override fun onLongClick(position: Int) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("할 일 삭제") // 제목 설정
        builder.setMessage("정말 삭제하시겠습니까?") // 내용 설정
        builder.setNegativeButton("취소", null) // 취소 버튼 설정
        builder.setPositiveButton("네", object : DialogInterface.OnClickListener { // 확인 버튼 설정
            override fun onClick(p0: DialogInterface?, p1: Int) {
                deleteItem(position)
            }
        })
        builder.show()
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putString()
//        super.onSaveInstanceState(outState)
//    }

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
//        adapter.notifyDataSetChanged()
    }
}