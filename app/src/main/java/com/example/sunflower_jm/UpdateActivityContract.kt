package com.example.sunflower_jm

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

abstract class UpdateActivityContract : ActivityResultContract<String, String>() {
    override fun createIntent(context: Context, input: String): Intent {
        val intent = Intent(context, UpdateItemActivity::class.java)
        intent.putExtra("input", input)
        return intent
    }
    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return when (resultCode) {
            Activity.RESULT_OK -> intent?.getStringExtra("title")
            else -> null
        }
    }
}