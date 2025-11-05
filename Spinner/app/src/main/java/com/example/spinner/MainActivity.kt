package com.example.spinner

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivityMainBinding
lateinit var adapter:ArrayAdapter<CharSequence>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ////Per els Spinner Planetes----------------------------

        val spinnerPlanetes: Spinner = binding.planetsSpinner
// Create an ArrayAdapter using the string array and a default spinner layout.
        adapter = ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        )
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinnerPlanetes.adapter = adapter
            spinnerPlanetes.onItemSelectedListener = this
//////////////Per el segon spinner fent servis listof, bàsicament copio el codi i modifico una mica
        val spinnerInteressos: Spinner = binding.interessosSpinner
        val interessos = listOf("Plataformes", "Novela Grafica", "RPG", "Estratègia", "JRPG", "Carreres")

        // Creem un NOU adaptador per aquest Spinner
        val adapterInteressos = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            interessos
        )
        adapterInteressos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerInteressos.adapter = adapterInteressos
        spinnerInteressos.onItemSelectedListener = this

        }
Falta modificar el onitemselected per diferenciar
    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        Log.d("Missatge", position.toString())
        Log.d("Element", (adapter.getItem(position)).toString())
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}

