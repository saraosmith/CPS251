package com.sarasmith.recycleviewintents

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sarasmith.recycleviewintents.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val extras = intent.extras ?: return

        val title = extras.getInt("Title")
        val detail = extras.getInt("Detail")
        val image = extras.getInt("Image")
        binding.heading.text = Data.titles[title]
        binding.description.text = Data.details[detail]
        binding.imageView.setImageResource(Data.images[image])

    }
}
