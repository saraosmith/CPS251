package com.myapplication.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sarasmith.myapplication.R
import com.sarasmith.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateTip.setOnClickListener {
            if (binding.billAmount.text!!.isBlank()) {
                binding.tipAmount.text = getString(R.string.you_must_enter_bill_amount)
            } else {
                val bill = binding.billAmount.text.toString().toFloat()
                var total = "The Tips are as follows:\n"
                total += "\n10% = " + String.format("%.2f", bill * 1.1)
                total += "\n15% = " + String.format("%.2f", bill * 1.15)
                total += "\n20% = " + String.format("%.2f", bill * 1.2)
                binding.tipAmount.text = total
            }
        }
    }
}