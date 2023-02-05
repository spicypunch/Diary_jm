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
import com.example.sunflower_jm.db.model.DiaryEntity
import java.text.SimpleDateFormat
import java.util.*

class UpdateItemActivity : AppCompatActivity() {

    private lateinit var binding: UpdateItemBinding
    private var uriInfo: Uri? = null

    private val viewModel by lazy {
        UpdateViewModel(
            AppDatabase.getInstance(this)!!.getDiaryDao(),
        )
    }

    private val readImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            it.data?.data?.let { uri ->
                contentResolver.takePersistableUriPermission(
                    uri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                Glide.with(this).load(uri).into(binding.imgLoad)
                viewModel.updateUri(uri)
            }
        }

    private val getTakePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {
            if (it) {
                uriInfo.let { binding.imgLoad.setImageURI(uriInfo) }
                viewModel.updateUri(uriInfo!!)

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UpdateItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddImage.setOnClickListener {
            openDialog()
        }

        binding.btnUpdateCompletion.setOnClickListener {
            viewModel.updateContent(
                binding.editTitle.text.toString(),
                binding.editContent.text.toString(),
            )
        }

        viewModel.updateItem(intent.getSerializableExtra(KEY_DATA) as DiaryEntity)

        viewModel.message.observe(this, androidx.lifecycle.Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.map.observe(this, androidx.lifecycle.Observer {
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra("result", it)
            })
            finish()
        })

        viewModel.item.observe(this, androidx.lifecycle.Observer {
            binding.imgLoad.setImageURI(Uri.parse(it.image))
            binding.editTitle.text = Editable.Factory.getInstance().newEditable(it.title)
            binding.editContent.text = Editable.Factory.getInstance().newEditable(it.content)
        })
    }

    private fun openDialog() {
        val dialogLayout = layoutInflater.inflate(R.layout.dialog, null)
        val dialogBuild = AlertDialog.Builder(this).apply {
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
        val now = SimpleDateFormat("yyMMdd_HHmm ss", Locale.KOREA).format(Date())
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