package com.example.bubble

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import api.RetrofitHelper
import api.UserApi
import api.data.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Match2 : AppCompatActivity() {
    lateinit var next2: Button
    lateinit var date2: Button
    lateinit var name2: TextView
    lateinit var birth2: TextView

    val apiKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVidW9ld21kZWVlZmVraXhiZGpnIiwicm9sZSI6ImFub24iLCJpYXQiOjE2NzI3MDg1ODMsImV4cCI6MTk4ODI4NDU4M30.gaAj8fT6JoUlwS-bjW1cNYqvytqHyUz5ABf6V_Ynxn8"
    val token = "Bearer $apiKey"
    val BubbleApi = RetrofitHelper.getInstance().create(UserApi::class.java)

    var x = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match2)

        next2 = findViewById(R.id.next2)
        date2 = findViewById(R.id.date2)
        name2 = findViewById(R.id.name2)
        birth2 = findViewById(R.id.birth2)

        var a = intent.getStringExtra("result")

        next2.setOnClickListener {
            var b = "0"

            val sharedPreference =  getSharedPreferences(
                "app_preference", Context.MODE_PRIVATE
            )

            var editor = sharedPreference.edit()
            editor.putString("a", a)
            editor.putString("b", b)
            editor.commit()

            val intent = Intent(this, ListMatch::class.java)
            intent.putExtra("result2", b)
            intent.putExtra("result", a)
            startActivity(intent)
        }

        date2.setOnClickListener {
            if (x != 0){
                CoroutineScope(Dispatchers.Main).launch {

                    val data = Data(name = name2.text.toString(), birth = birth2.text.toString())
                    val response = BubbleApi.create(token = token, apiKey = apiKey, data = data)

                    Toast.makeText(
                        applicationContext,
                        "Match!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            var b = "1"

            val sharedPreference =  getSharedPreferences(
                "app_preference", Context.MODE_PRIVATE
            )

            var editor = sharedPreference.edit()
            editor.putString("a", a)
            editor.putString("b", b)
            editor.commit()

            val intent = Intent(this, ListMatch::class.java)
            intent.putExtra("result2", b)
            intent.putExtra("result", a)
            startActivity(intent)
            finish()
        }
    }
}