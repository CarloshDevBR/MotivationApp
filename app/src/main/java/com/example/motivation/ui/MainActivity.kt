package com.example.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences

class MainActivity : AppCompatActivity() {
    private var categoryId: Int = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        verifyUserName()

        handleUserName()

        val text_name = findViewById<TextView>(R.id.text_user_name)

        text_name.setOnClickListener {
            handleNavigateUser()
        }

        val image_all = findViewById<ImageView>(R.id.image_all)
        val image_happy = findViewById<ImageView>(R.id.image_happy)
        val image_sunny = findViewById<ImageView>(R.id.image_sunny)

        image_all.setOnClickListener {
            categoryId = MotivationConstants.FILTER.ALL
            handelIconSelection(image_all)
        }
        image_happy.setOnClickListener {
            categoryId = MotivationConstants.FILTER.HAPPY
            handelIconSelection(image_happy)
        }
        image_sunny.setOnClickListener {
            categoryId = MotivationConstants.FILTER.SUNNY
            handelIconSelection(image_sunny)
        }

        val text_phrase = findViewById<TextView>(R.id.text_phrase)

        val button_generate = findViewById<Button>(R.id.button_generate)

        button_generate.setOnClickListener {
            val phrase = Mock().getPhrash(categoryId)

            text_phrase.text = phrase
        }
    }

    private fun handelIconSelection(image: ImageView) {
        findViewById<ImageView>(R.id.image_all).setColorFilter(
            ContextCompat.getColor(
                this,
                R.color.black_500
            )
        )
        findViewById<ImageView>(R.id.image_happy).setColorFilter(
            ContextCompat.getColor(
                this,
                R.color.black_500
            )
        )
        findViewById<ImageView>(R.id.image_sunny).setColorFilter(
            ContextCompat.getColor(
                this,
                R.color.black_500
            )
        )

        image.setColorFilter(ContextCompat.getColor(this, R.color.white))
    }

    private fun handleNavigateUser() {
        SecurityPreferences(this).removeString(MotivationConstants.KEY.USER_NAME)

        startActivity(Intent(this, UserActivity::class.java))
        finish()
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)

        val title = findViewById<TextView>(R.id.text_user_name)

        title.text = "Olá, $name"
    }

    private fun verifyUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)

        if(name == "") {
            startActivity(Intent(this, UserActivity::class.java))
            finish()
        }
    }
}