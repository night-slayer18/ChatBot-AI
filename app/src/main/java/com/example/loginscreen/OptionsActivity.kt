package com.example.loginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginscreen.databinding.ActivityOptionsBinding

class OptionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOptionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.general.setOnClickListener {
            startActivity(Intent(applicationContext,GeneralTextActivity::class.java))
            overridePendingTransition(0,0)
        }

        binding.academics.setOnClickListener {
            startActivity(Intent(applicationContext,AcademicsTextActivity::class.java))
            overridePendingTransition(0,0)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}