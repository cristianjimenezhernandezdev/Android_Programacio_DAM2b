package com.example.uservip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.uservip.PreferenciesApp.Companion.prefs
import com.example.uservip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private var pos = 0
    private var textSeleccionat = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        initSpinner()
        checkUserValues()

    }
    fun checkUserValues() {
        if (prefs.getName().isNotEmpty()) {
            goToDetail()
        }
    }


    // 🔹 Configuració del Spinner
    private fun initSpinner() {
        val interessos = listOf(
            "Formal",
            "Informal",
        )

        val adapterInteressos = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            interessos
        )
        adapterInteressos.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.interessosSpinner.adapter = adapterInteressos
        binding.interessosSpinner.onItemSelectedListener = this


        val esVipGuardat = prefs.getVip()
        val index = if (esVipGuardat) 0 else 1   // 0 = Formal, 1 = Informal

        binding.interessosSpinner.setSelection(index)
        pos = index
        textSeleccionat = interessos[index]
    }





    fun initUI() {
        binding.btnContinuar.setOnClickListener { accesToDatail() }
    }

    // guardar a SharedPreferences
    fun accesToDatail() {
        if (binding.etName.text.toString().isNotEmpty() &&
            binding.etCognom.text.toString().isNotEmpty() &&
            binding.etLink.text.toString().isNotEmpty()
        ) {
            prefs.saveName(binding.etName.text.toString())
            prefs.saveCognom(binding.etCognom.text.toString())
            prefs.saveLink(binding.etLink.text.toString())
            prefs.saveVIP(textSeleccionat == "Formal")
            goToDetail()
        } else {
            Toast.makeText(
                this,
                "Les dades no poden ser buides",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    // Quan es selecciona un element del Spinner
    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        if (parent?.id == R.id.interessos_spinner) {
            pos = position
            textSeleccionat = parent.getItemAtPosition(position).toString()

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


    fun goToDetail() {
        if (prefs.getVip()) {
            // Usuari VIP va a MainActivity3
            startActivity(Intent(this, MainActivity3::class.java))
        } else {
            // Usuari normal va a MainActivity2 amb les dades del Spinner
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("posicio", pos)
            intent.putExtra("text", textSeleccionat)
            startActivity(intent)
        }
    }
}
