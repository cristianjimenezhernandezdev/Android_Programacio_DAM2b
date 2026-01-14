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

        // spinner
        val opcions = listOf("Horizontal", "Vertical")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opcions)
        binding.spinnerTipus.adapter = adapter

        //Amb el Spinner seleccionem l'Activity i li passem els valors que hem posat, en plan nom etc
        binding.spinnerTipus.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val nom = binding.etNom.text.toString().trim()
                val edat = binding.etEdat.text.toString().trim()
                // Aixo ve del custom
                val telefon = binding.customTelefon.findViewById<android.widget.EditText>(R.id.customEdit).text.toString().trim()
                val tipus = binding.spinnerTipus.selectedItem.toString()

                // Validar si no està buit
                if (nom.isEmpty() || edat.isEmpty() || telefon.isEmpty()) {
                    Toast.makeText(this@MainActivity, "Et falta posar algunt camp crack!", Toast.LENGTH_SHORT).show()
                    return
                }

                // Telefon validar
                if (!telefon.all { it.isDigit() }) {
                    Toast.makeText(this@MainActivity, "TUUU, Posa numeros", Toast.LENGTH_SHORT).show()
                    return
                }
                if (telefon.length < 9) {
                    Toast.makeText(this@MainActivity, "S'han de posar 9 numeros minim, posa més", Toast.LENGTH_SHORT).show()
                    return
                }
                if (telefon.length > 9) {
                    Toast.makeText(this@MainActivity, "Maxim 9 numeros", Toast.LENGTH_SHORT).show()
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