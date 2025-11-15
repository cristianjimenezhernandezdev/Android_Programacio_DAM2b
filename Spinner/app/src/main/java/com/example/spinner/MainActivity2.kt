package com.example.spinner

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spinner.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //Poso les variables que venen de l'altre activity importades per els intent i faig el binding
        // amb el xml del activity2
        val posicio = intent.getIntExtra("posicio", -1)
        val text = intent.getStringExtra("text")

        binding.textPosicio.text = posicio.toString()
        binding.textInteres.text = text
//Amb finish tanco el activity i per defecte tornarà a la anterior
        binding.buttonTornar.setOnClickListener {

            finish()}


    }
}