package com.example.examen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.examen.databinding.ActivityVerticalBinding

class VerticalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerticalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerticalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nom = intent.getStringExtra("nom") ?: ""
        val edat = intent.getStringExtra("edat") ?: ""
        val telefon = intent.getStringExtra("telefon") ?: ""

        binding.tvNom.text = nom
        binding.tvEdat.text = edat
        binding.tvTelefon.text = telefon

        binding.btnTornar.setOnClickListener {
            finish()
        }
    }
}

