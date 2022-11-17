package com.example.sunflower_jm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sunflower_jm.databinding.ActivityMainBinding
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.SunFlowerDao
import com.example.sunflower_jm.db.SunFlowerEntity
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var db : AppDatabase
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

//        binding.recyclerView.setOnClickListener {
//            val intent = Intent(this, DetailActivity::class.java)
//            startActivity(intent)
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
            adapter = RecyclerViewAdapter(sunflowerList)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = GridLayoutManager(this,2)
        }
    }

    override fun onRestart() {
        super.onRestart()
        getAllItemList()
    }
}