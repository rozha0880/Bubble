package com.example.bubble

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Logout : AppCompatActivity() {
    lateinit var logout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)

        logout = findViewById(R.id.logout)

        logout.setOnClickListener {
            val intent = Intent(this, iconLogin::class.java)
            startActivity(intent)
        }
    }
}