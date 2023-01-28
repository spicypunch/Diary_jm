package com.example.sunflower_jm.activity

import android.Manifest
import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.sunflower_jm.R
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.DiaryDao
import com.example.sunflower_jm.databinding.AddItemBinding
import com.example.sunflower_jm.db.DiaryEntity

class AddItemActivity : AppCompatActivity() {

    lateinit var binding: AddItemBinding
    lateinit var db: AppDatabase
    lateinit var diaryDao: DiaryDao
    private var uriInfo: Uri? = null

    private val permissionList = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    private val requestMultiplePermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            result.forEach {
                Log.e("권한 확인", it.toString())
                if (!it.value) {
                    Toast.makeText(applicationContext, "${it.key}권한 허용 필요", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                }
            }
        }

    // 파일 불러오기
//    private val readImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
//        Glide.with(this).load(uri).into(binding.imgLoad)
//        uriInfo = uri
//    }
    private val readImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            it.data?.data?.let { uri ->
                contentResolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION)
                Glide.with(this).load(uri).into(binding.imgLoad)
                uriInfo = uri
            }
        }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AppDatabase.getInstance(this)!!
        diaryDao = db.getDiaryDao()
        requestMultiplePermission.launch(permissionList)

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"

        binding.btnAddImage.setOnClickListener {
            readImage.launch(intent)
        }
        binding.btnCompletion.setOnClickListener {
            insertItem()
        }
    }

    private fun insertItem() {
        val itemImage = uriInfo.toString()
        val itemTitle = binding.editTitle.text.toString()
        val itemContent = binding.editContent.text.toString()
        if (itemTitle.isBlank() || itemContent.isBlank()) {
            Toast.makeText(this, "모든 항목을 채워주세요!!", Toast.LENGTH_SHORT).show()
        } else {
            insertDB(itemImage, itemTitle, itemContent)
        }
    }

    private fun insertDB(itemImage: String, itemTitle: String, itemContent: String) {
        Thread {
            diaryDao.insertItem(DiaryEntity(null, itemImage, itemTitle, itemContent))
            runOnUiThread {
                Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }.start()
    }
}