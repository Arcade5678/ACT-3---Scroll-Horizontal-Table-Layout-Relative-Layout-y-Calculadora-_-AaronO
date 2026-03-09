package com.example.act3_scrollhorizontaltablelayoutrelativelayoutycalculadora

import android.os.Bundle
import android.widget.HorizontalScrollView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Exercici11Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercici11)

        val scrollView = findViewById<HorizontalScrollView>(R.id.horizontalScrollView)
        val switchScroll = findViewById<Switch>(R.id.switchScroll)
        val tvStatus = findViewById<TextView>(R.id.tvStatus)

        // Establim estat inicial: scrollbar visible
        scrollView.isHorizontalScrollBarEnabled = true

        // Toggle per mostrar/ocultar la barra de scroll horitzontal
        switchScroll.setOnCheckedChangeListener { _, isChecked ->
            scrollView.isHorizontalScrollBarEnabled = isChecked
            tvStatus.text = if (isChecked) {
                "Barres de scroll: VISIBLES"
            } else {
                "Barres de scroll: OCULTES"
            }
        }
    }
}
