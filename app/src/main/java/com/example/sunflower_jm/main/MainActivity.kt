package com.example.sunflower_jm.main

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sunflower_jm.AddItemActivity
import com.example.sunflower_jm.OnItemLongClickListener
import com.example.sunflower_jm.RecyclerViewAdapter
import com.example.sunflower_jm.databinding.ActivityMainBinding
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.DiaryEntity

class MainActivity : AppCompatActivity(), OnItemLongClickListener, MainContract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapter

    private val presenter by lazy {
        MainPresenter(
            AppDatabase.getInstance(this)!!.getDiaryDao(),
            this,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = RecyclerViewAdapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

       binding.fab.setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)
        }
        presenter.obtainLoadItems()
    }

    override fun updateItems(items: MutableList<DiaryEntity>) {
        runOnUiThread {
            adapter.updateList(items)
//            adapter.notifyDataSetChanged()
        }
    }

    override fun onLongClick(item: DiaryEntity) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("할 일 삭제")
        builder.setMessage("정말 삭제하시겠습니까?")
        builder.setNegativeButton("취소", null)
        builder.setPositiveButton("네", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                deleteItem(item)
            }
        })
        builder.show()
    }

    private fun deleteItem(item: DiaryEntity) {
        Thread {
            presenter.delete(item)
            runOnUiThread {
//                adapter.notifyDataSetChanged()
                Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    override fun onRestart() {
        super.onRestart()
        presenter.obtainLoadItems()
    }
}