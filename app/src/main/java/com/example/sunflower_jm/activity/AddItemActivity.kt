package com.example.sunflower_jm.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.load
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.DiaryDao
import com.example.sunflower_jm.databinding.AddItemBinding
import com.example.sunflower_jm.db.Converter
import com.example.sunflower_jm.db.DiaryEntity
import okio.IOException

class AddItemActivity : AppCompatActivity() {

    lateinit var binding: AddItemBinding
    lateinit var db: AppDatabase
    lateinit var diaryDao: DiaryDao
    private var REQUEST_IMAGE_CODE = 1001
    private var uriInfo: Uri? = null
    lateinit var itemImage: ImageView
    var bitMap: Bitmap? = null

//    private val permissionList = arrayOf(
//        Manifest.permission.CAMERA,
//        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//        Manifest.permission.READ_EXTERNAL_STORAGE
//    )
//
//    private val requestMultiplePermission =
//        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
//            result.forEach {
//                if (!it.value) {
//                    Toast.makeText(applicationContext, "${it.key}권한 허용 필요", Toast.LENGTH_SHORT)
//                        .show()
//                    finish()
//                }
//            }
//        }

    // 파일 불러오기
    private val readImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        binding.imgLoad.load(uri)
        uriInfo = uri
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = AppDatabase.getInstance(this)!!
        diaryDao = db.getDiaryDao()
        binding.btnAddImage.setOnClickListener {
            requestPermissions()
//            requestMultiplePermission.launch(permissionList)
            readImage.launch("image/*")

        }
        binding.btnCompletion.setOnClickListener {
//            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            startActivityForResult(intent, REQUEST_IMAGE_CODE)
            insertItem()
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == REQUEST_IMAGE_CODE) {
//            uri = data?.data
//            Log.e("uri", uri.toString())
//            try {
//                bitMap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
//                Log.e("bitMap", bitMap.toString())
//                itemImage.setImageBitmap(bitMap)
//                Log.e("itemImage", itemImage.toString())
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//
//        }
//    }

    private fun requestPermissions(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }

        val permissions = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,

            )

        ActivityCompat.requestPermissions(this, permissions, 0)
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            0 -> {
                if (grantResults.isNotEmpty()) {
                    var isAllGranted = true
                    // 요청한 권한 허용/거부 상태 한번에 체크
                    for (grant in grantResults) {
                        if (grant != PackageManager.PERMISSION_GRANTED) {
                            isAllGranted = false
                            break;
                        }
                    }
                    // 요청한 권한을 모두 허용했음.
                    if (isAllGranted) {
                        // 다음 step으로 ~
                    }
                    // 허용하지 않은 권한이 있음. 필수권한/선택권한 여부에 따라서 별도 처리를 해주어야 함.
                    else {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                            )
                            || !ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                Manifest.permission.CAMERA
                            )
                            || !ActivityCompat.shouldShowRequestPermissionRationale(
                                this,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            )
                        ) {
                            // 다시 묻지 않기 체크하면서 권한 거부 되었음.
                        } else {
                            // 접근 권한 거부하였음.
                        }
                    }
                }
            }
        }
    }

    private fun insertItem() {
        Log.e("test", uriInfo.toString())
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
}