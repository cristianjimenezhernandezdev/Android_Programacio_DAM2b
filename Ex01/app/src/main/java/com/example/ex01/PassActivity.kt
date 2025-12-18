package com.example.ex01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ex01.databinding.ActivityPassBinding

class PassActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPassBinding

    companion object {
        private const val PREFS = "ex01_prefs"
        private const val K_NOM = "nom"
        private const val K_COGNOMS = "cognoms"
        private const val K_DATA = "data"
        private const val K_MODALITAT = "modalitat"
        private const val K_TRACTE = "tracte"
        private const val K_AVISOS = "avisos"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences(PREFS, MODE_PRIVATE)

        val nom = prefs.getString(K_NOM, "") ?: ""
        val cognoms = prefs.getString(K_COGNOMS, "") ?: ""
        val data = prefs.getString(K_DATA, "") ?: ""
        val modalitat = prefs.getString(K_MODALITAT, "") ?: ""
        val tracte = prefs.getString(K_TRACTE, "Formal") ?: "Formal"
        val avisos = prefs.getBoolean(K_AVISOS, false)

        val missatge = if (tracte == "Formal") {
            "Benvingut/da, $nom $cognoms.\nData: $data\nModalitat: $modalitat\nAvisos: ${if (avisos) "Sí" else "No"}"
        } else {
            "Ei $nom! 😄\n$data · $modalitat · Avisos: ${if (avisos) "Sí" else "No"}"
        }

        binding.tvMissatge.text = missatge

        // (Opcional útil) Botó per “tancar sessió” i tornar al formulari
        // Si NO el vols, elimina aquest bloc i el botó del layout.

    }
}
