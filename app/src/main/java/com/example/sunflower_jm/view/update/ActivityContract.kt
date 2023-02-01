package com.example.sunflower_jm.view.update

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.sunflower_jm.db.model.DiaryEntity

class ActivityContract : ActivityResultContract<DiaryEntity, HashMap<String, String>?>() {

    override fun createIntent(context: Context, input: DiaryEntity): Intent {
        return UpdateItemActivity.getIntent(context, input)
//        return Intent(context, UpdateItemActivity::class.java).apply {
//            putExtra("input", input)
//        }
    }

    /*
    액티비티로 전달받은 결과 데이터를 제네릭 O타입으로 변환함
    onActivityResult 콜백 메서드 처리를 대체
     */
    override fun parseResult(resultCode: Int, intent: Intent?): HashMap<String, String>? {
        return when (resultCode) {
            Activity.RESULT_OK -> intent?.getSerializableExtra("result") as HashMap<String, String>
            else -> null
        }
    }
}