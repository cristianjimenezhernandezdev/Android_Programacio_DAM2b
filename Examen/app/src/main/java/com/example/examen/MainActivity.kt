package com.example.examen

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Opcions del selector
        val opcions = listOf("Escull una opcio", "Horizontal", "Vertical")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opcions)
        binding.spinnerTipus.adapter = adapter

        binding.btnContinuar.setOnClickListener {
            val nom = binding.etNom.text.toString().trim()
            val edat = binding.etEdat.text.toString().trim()
            val telefon = binding.etTelefon.text.toString().trim()
            val tipus = binding.spinnerTipus.selectedItem.toString()

            // Validació de camps buits
            if (nom.isEmpty() || edat.isEmpty() || telefon.isEmpty() || tipus == "Escull una opcio") {
                Toast.makeText(this, "Omple tots els camps i tria un tipus", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validació de telèfon: exactament 9 dígits
            if (telefon.length != 9) {
                Toast.makeText(this, "El telèfon ha de tenir 9 dígits", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Crear intent segons el tipus seleccionat
            val intent = if (tipus == "Vertical") {
                Intent(this, VerticalActivity::class.java)
            } else {
                Intent(this, HorizontalActivity::class.java)
            }

            intent.putExtra("nom", nom)
            intent.putExtra("edat", edat)
            intent.putExtra("telefon", telefon)

            startActivity(intent)
        }
    }
}