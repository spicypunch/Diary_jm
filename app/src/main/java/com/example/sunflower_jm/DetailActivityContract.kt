package com.example.sunflower_jm

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.sunflower_jm.db.SunFlowerEntity

class DetailActivityContract : ActivityResultContract<SunFlowerEntity, String?>() {

    /*
     다른 액티비티를 호출하기 위한 인텐트를 생성, 제네릭 타입 I가 인텐트를 생성하기 위한 매개변수 타입으로 전달
     startActivityForResult 메서드 호출을 대체
     */
    override fun createIntent(context: Context, input: SunFlowerEntity): Intent {
        return Intent(context, UpdateItemActivity::class.java).apply {
            putExtra("input", input)
        }
    }

    /*
    액티비티로 전달받은 결과 데이터를 제네릭 O타입으로 변환함
    onActivityResult 콜백 메서드 처리를 대체
     */

    /*
    parseResult의 반환값 String? 부분이 계속 에러가 나는데
    https://developer.android.com/training/basics/intents/result?hl=ko
    예제 코드나 공식 문서를 찾아봐도 제가 어떤 부분에서 실수를 한 건지 잘 모르겠습니다...
    ?를 지우고 null을 반환 안 하고 테스트 해보려해도 잘 안 되네요
     */
    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return when (resultCode) {
            Activity.RESULT_OK -> intent?.getStringExtra("result")
            else -> "cool"
        }
    }
}