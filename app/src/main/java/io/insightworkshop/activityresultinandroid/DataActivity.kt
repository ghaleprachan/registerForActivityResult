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