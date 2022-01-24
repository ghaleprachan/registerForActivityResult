package io.insightworkshop.activityresultinandroid

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity

class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        findViewById<TextView>(R.id.tvDataFromMain).text = intent.getStringExtra("KEY_NAME")

        findViewById<Button>(R.id.btnSuccess).setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra("KEY_RESULT", "SUCCESS "))
            finish()
        }
        findViewById<Button>(R.id.btnFailed).setOnClickListener {
            setResult(999, Intent().putExtra("KEY_RESULT", "FAILED "))
            finish()
        }
    }

    class Contract : ActivityResultContract<String, String>() {
        override fun createIntent(context: Context, input: String?): Intent =
            Intent(context, DataActivity::class.java).apply {
                putExtra("KEY_NAME", input + "Message from main activity ")
            }

        override fun parseResult(resultCode: Int, intent: Intent?): String {
            if (resultCode == Activity.RESULT_OK) {
                return intent?.getStringExtra("KEY_RESULT") + "Data has been updated successfully"
            } else {
                return intent?.getStringExtra("KEY_RESULT") + "Failed to update data"
            }
        }
    }
}

/**
 * Custom contract to handle parcelable we also can use inbuild [StartActivityForResult()]
 * To handle result for inbuild contract visit [https://developer.android.com/training/basics/intents/result#custom]
 * For full documentation visit [https://developer.android.com/training/basics/intents/result]
*/
class CustomContract<T : Parcelable> : ActivityResultContract<Intent, T?>() {
    companion object {
        const val RESULT_DATA = "result_data"
    }

    override fun createIntent(context: Context, input: Intent): Intent {
        return input
    }

    override fun parseResult(resultCode: Int, intent: Intent?): T? {
        return if (resultCode == Activity.RESULT_OK) {
            intent?.getParcelableExtra(RESULT_DATA)
        } else {
            null
        }
    }
}

