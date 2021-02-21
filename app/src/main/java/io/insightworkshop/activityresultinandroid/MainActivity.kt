package io.insightworkshop.activityresultinandroid

import android.Manifest
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        // can be updated to viewmodel
        if (granted) {
            Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show()
        }
    }

    private val activityResult = registerForActivityResult(DataActivity.Contract()) { result ->
        // We can update data to viewmodel
        tvResult.text = result
    }

    private lateinit var tvResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvResult = findViewById(R.id.tvResult)
        findViewById<Button>(R.id.btnAskPermission).setOnClickListener {
            requestPermission.launch(Manifest.permission.CAMERA)
        }

        findViewById<Button>(R.id.btnCustomContracts).setOnClickListener {
            activityResult.launch("FROM MAIN ACTIVITY ")
        }
    }
}