package com.example.t2_1_fragments_menu

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class PrimerFragment : Fragment(R.layout.fragment_primer) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nom = arguments?.getString("Nom").orEmpty()
        val cognoms = arguments?.getString("Cognoms").orEmpty()
        val edat = arguments?.getString("Edat").orEmpty()
        val dataNaixement = arguments?.getString("DataNaixement").orEmpty()
        val llocNaixement = arguments?.getString("LlocNaixement").orEmpty()

        val text = buildString {
            appendLine("Fragment 1")
            appendLine()
            appendLine("Nom: $nom")
            appendLine("Cognoms: $cognoms")
            appendLine("Edat: $edat")
            appendLine("Data de naixement: $dataNaixement")
            appendLine("Lloc de naixement: $llocNaixement")
        }

        view.findViewById<TextView>(R.id.fragmentText).text = text
    }
}