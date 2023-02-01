package com.example.sunflower_jm.view.update

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.sunflower_jm.R
import com.example.sunflower_jm.databinding.UpdateItemBinding
import com.example.sunflower_jm.db.AppDatabase
import com.example.sunflower_jm.db.DiaryDao
import com.example.sunflower_jm.db.model.DiaryEntity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class UpdateItemActivity : AppCompatActivity(), UpdateContract.View {

    lateinit var binding: UpdateItemBinding
    lateinit var db: AppDatabase
    lateinit var diaryDao: DiaryDao
    private var uriInfo: Uri? = null

    private lateinit var item: DiaryEntity

    private val presenter by lazy {
        UpdatePresenter(
            item.id!!,
            uriInfo,
            binding.editTitle.text.toString(),
            binding.editContent.text.toString(),
            diaryDao,
            this
        )
    }

    private val readImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        it.data?.data?.let { uri ->
            contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UpdateItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        diaryDao = db.getDiaryDao()

        val getIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        getIntent.type = "image/*"

        binding.btnAddImage.setOnClickListener {
            openDialog(this)
        }

        binding.btnUpdateCompletion.setOnClickListener {
            if (binding.editTitle.text.isBlank() || binding.editContent.text.isBlank()) {
                Toast.makeText(this, "모든 항목을 채워주세요!", Toast.LENGTH_SHORT).show()
            } else {
                presenter.updateContent()
            }
        }

        item = intent.getSerializableExtra(KEY_DATA) as DiaryEntity

        binding.imgLoad.setImageURI(Uri.parse(item.image))
        binding.editTitle.text = Editable.Factory.getInstance().newEditable(item.title)
        binding.editContent.text = Editable.Factory.getInstance().newEditable(item.content)
    }

    override fun finishActivity(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        presenter.makeMap()
        finish()
    }

    override fun sendResult(map: HashMap<String, String>) {
        setResult(Activity.RESULT_OK, Intent().apply {
            putExtra("result", map)
        })
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

    companion object {

        private const val KEY_DATA = "key-data"

        fun getIntent(context: Context, data: DiaryEntity): Intent =
            Intent(context, UpdateItemActivity::class.java).apply {
                putExtra(KEY_DATA, data)
            }
    }
}