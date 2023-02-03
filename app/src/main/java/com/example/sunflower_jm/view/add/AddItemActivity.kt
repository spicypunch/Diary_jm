package com.example.sunflower_jm.view.add

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sunflower_jm.R
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.DiaryDao
import com.example.sunflower_jm.databinding.AddItemBinding
import com.example.sunflower_jm.db.model.DiaryEntity
import java.text.SimpleDateFormat
import java.util.*

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
                if (!it.value) {
                    Toast.makeText(applicationContext, "${it.key}권한 허용 필요", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                }
            }
        }

    private val readImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            it.data?.data?.let { uri ->
                contentResolver.takePersistableUriPermission(uri, FLAG_GRANT_READ_URI_PERMISSION)
                Glide.with(this).load(uri).into(binding.imgLoad)
                uriInfo = uri
            }
        }

    private val getTakePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it) {
                uriInfo.let { binding.imgLoad.setImageURI(uriInfo) }
            }
        }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AppDatabase.getInstance(this)!!
        diaryDao = db.getDiaryDao()

        binding.btnAddImage.setOnClickListener {
            requestMultiplePermission.launch(permissionList)
            openDialog(this)
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
            Thread {
                diaryDao.insertItem(DiaryEntity(null, itemImage, itemTitle, itemContent))
                runOnUiThread {
                    Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.start()
        }
    }
    private fun openDialog(context: Context) {
        val dialogLayout = layoutInflater.inflate(R.layout.dialog, null)
        val dialogBuild = AlertDialog.Builder(context).apply {
            setView(dialogLayout)
        }
        val dialog = dialogBuild.create().apply { show() }

        val cameraAddBtn = dialogLayout.findViewById<Button>(R.id.btn_camera)
        val fileAddBtn = dialogLayout.findViewById<Button>(R.id.btn_file)

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"

        cameraAddBtn.setOnClickListener {
            uriInfo = createImageFile()
            getTakePicture.launch(uriInfo)
            dialog.dismiss()
        }
        fileAddBtn.setOnClickListener {
            readImage.launch(intent)
            dialog.dismiss()
        }
    }

    private fun createImageFile(): Uri? {
        val now = SimpleDateFormat("yyMMdd_HHmmss").format(Date())
        val content = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "img_$now.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        }
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, content)
    }

}