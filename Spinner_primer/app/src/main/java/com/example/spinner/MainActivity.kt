package com.example.spinner

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var binding: ActivityMainBinding
lateinit var adapterPlanetes:ArrayAdapter<CharSequence>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ////Per els Spinner Planetes----------------------------

        val spinnerPlanetes: Spinner = binding.planetsSpinner
// Create an ArrayAdapter using the string array and a default spinner layout.
        adapterInteressos = ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        )
            // Specify the layout to use when the list of choices appears.
            adapterPlanetes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinnerPlanetes.adapter = adapterPlanetes
            spinnerPlanetes.onItemSelectedListener = this
//////////////Per el segon spinner fent servis listof, bàsicament copio el codi i modifico una mica
        // --- Spinner 2: Interessos (amb List)
        val spinnerInteressos: Spinner = binding.interessosSpinner
        val interessos = listOf("Plataformes", "Novel·la Gràfica", "RPG", "Estratègia", "JRPG", "Carreres")

        val adapterInteressos = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            interessos
        )
        adapterInteressos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerInteressos.adapter = adapterInteressos
        spinnerInteressos.onItemSelectedListener = this

        }
//Modifico el onItem selected perque com que vull tenir els dos spinners el programa ha de saber quin és quin
    //En cada moment
    override fun onItemSelected(
    parent: AdapterView<*>?,
    view: View?,
    position: Int,
    id: Long
) {
    when (parent?.id) {
        R.id.planets_spinner -> {
            val planetaSeleccionat = adapterPlanetes.getItem(position)
            Log.d("SPINNER", "Planeta seleccionat: $planetaSeleccionat")
        }

        R.id.interessos_spinner -> {
            val interesSeleccionat = parent.getItemAtPosition(position)
            Log.d("SPINNER", "Interès seleccionat: $interesSeleccionat")
            //Faig que apareixi el missatge per pantalla amb el toast
            Toast.makeText(this, "Interès seleccionat: $interesSeleccionat", Toast.LENGTH_SHORT).show()

        }

    }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}

