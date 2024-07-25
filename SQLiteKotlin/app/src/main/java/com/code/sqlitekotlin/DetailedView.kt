package com.code.sqlitekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.code.sqlitekotlin.databinding.ActivityDetailedViewBinding

class DetailedView : AppCompatActivity() {
        private lateinit var binding: ActivityDetailedViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailedViewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.clickBtn.setOnClickListener {
            startActivity(Intent(this@DetailedView, MainActivity::class.java))
        }
    }
}