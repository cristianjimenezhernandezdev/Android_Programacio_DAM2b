package com.example.s_preferences

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.s_preferences.databinding.ActivityMainBinding
import kotlin.jvm.java


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivityMainBinding

    // Declarem l’adaptador que connectarà la llista d’interessos amb el Spinner
    lateinit var adapter: ArrayAdapter<CharSequence>

    //Declaro la variable perque no salti el spinner
    var primerCop = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // --- Inicialitza el ViewBinding ---
        // Inicialitzem el ViewBinding i establim la vista principal de l’activitat
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Creo una llista dels formats que apreixeran a la llista
        val listaDeFormats = listOf(
            "Format formal",
            "Format informal"
        )

        // Creo l'adaptador utilitzant el layout prederminat del sistema
        adapter = ArrayAdapter(
            this,
            R.layout.simple_spinner_item,
            listaDeFormats
        )
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.formatSpinner.adapter = adapter
        binding.formatSpinner.onItemSelectedListener=this




    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        val SeleccioSpinner = adapter.getItem(position).toString()
        if (primerCop) {
            primerCop = false
        } else {
           val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("format", SeleccioSpinner)
            startActivity(intent)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}