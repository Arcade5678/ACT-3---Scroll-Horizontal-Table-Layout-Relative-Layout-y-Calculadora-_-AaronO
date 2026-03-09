package com.example.act3_scrollhorizontaltablelayoutrelativelayoutycalculadora

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnExercici11).setOnClickListener {
            startActivity(Intent(this, Exercici11Activity::class.java))
        }

        findViewById<Button>(R.id.btnExercici12).setOnClickListener {
            startActivity(Intent(this, Exercici12Activity::class.java))
        }

        findViewById<Button>(R.id.btnExercici13).setOnClickListener {
            startActivity(Intent(this, Exercici13Activity::class.java))
        }

        findViewById<Button>(R.id.btnExercici14).setOnClickListener {
            startActivity(Intent(this, Exercici14Activity::class.java))
        }
    }
}