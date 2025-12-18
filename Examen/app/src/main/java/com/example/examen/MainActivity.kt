package com.example.examen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
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
        val opcions = listOf("Horizontal", "Vertical")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opcions)
        binding.spinnerTipus.adapter = adapter

        //Amb el Spinner seleccionem l'Activity a obrir amb el la funcio ItemSelectedListener sense fer servir buton
        binding.spinnerTipus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val nom = binding.etNom.text.toString().trim()
                val edat = binding.etEdat.text.toString().trim()
                val telefon = binding.etTelefon.text.toString().trim()
                val tipus = binding.spinnerTipus.selectedItem.toString()

                // Validació de camps buits
                if (nom.isEmpty() || edat.isEmpty() || telefon.isEmpty()) {
                    Toast.makeText(this@MainActivity, "Omple tots els camps, si us plau", Toast.LENGTH_SHORT).show()
                    return
                }

                // Validació de telèfon: només números i màxim 9 dígits
                if (!telefon.all { it.isDigit() }) {
                    Toast.makeText(this@MainActivity, "El telèfon només pot tenir números", Toast.LENGTH_SHORT).show()
                    return
                }
                if (telefon.length < 9) {
                    Toast.makeText(this@MainActivity, "El telèfon ha de tenir exactament 9 dígits", Toast.LENGTH_SHORT).show()
                    return
                }
                if (telefon.length > 9) {
                    Toast.makeText(this@MainActivity, "El telèfon no pot tenir més de 9 dígits", Toast.LENGTH_SHORT).show()
                    return
                }

                // Crear intent segons el tipus seleccionat
                val intent = if (tipus == "Vertical") {
                    Intent(this@MainActivity, VerticalActivity::class.java)
                } else {
                    Intent(this@MainActivity, HorizontalActivity::class.java)
                }
                intent.putExtra("nom", nom)
                intent.putExtra("edat", edat)
                intent.putExtra("telefon", telefon)
                startActivity(intent)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
}