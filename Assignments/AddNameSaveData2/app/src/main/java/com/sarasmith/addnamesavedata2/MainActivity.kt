package com.sarasmith.addnamesavedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sarasmith.addnamesavedata.ui.main.MainFragment
import com.sarasmith.addnamesavedata2.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}