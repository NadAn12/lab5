package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hoursEditText = findViewById<EditText>(R.id.hoursEditText)
        val discountSeekBar = findViewById<SeekBar>(R.id.discountSeekBar)
        val discountValueTextView = findViewById<TextView>(R.id.discountValueTextView)
        val okButton = findViewById<Button>(R.id.okButton)

        discountSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                discountValueTextView.text = "$progress%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        okButton.setOnClickListener {
            val hours = hoursEditText.text.toString().toInt()
            val discount = discountSeekBar.progress

            val cost = calculateCost(hours, discount)
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("cost", cost)
            startActivity(intent)
        }
    }

    private fun calculateCost(hours: Int, discount: Int): Int {
        val costPerHour = 2000
        val totalCost = hours * costPerHour
        val discountAmount = (totalCost * discount) / 100
        return totalCost - discountAmount
    }
}

