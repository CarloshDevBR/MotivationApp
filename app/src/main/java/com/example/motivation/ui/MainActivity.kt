package com.example.motivation.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivation.infra.KEY
import com.example.motivation.R
import com.example.motivation.infra.SecurityPreferences

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        handleUserName()

        val image_all = findViewById<ImageView>(R.id.image_all)
        val image_happy = findViewById<ImageView>(R.id.image_happy)
        val image_sunny = findViewById<ImageView>(R.id.image_sunny)

        val button_generate = findViewById<Button>(R.id.button_generate)

        button_generate.setOnClickListener {

        }
    }

    private fun handleFilter() {

    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(KEY.USER_NAME)

        val title = findViewById<TextView>(R.id.text_user_name)

        title.text = "Olá, $name"
    }
}