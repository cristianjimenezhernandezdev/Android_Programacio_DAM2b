package com.example.t2_1_fragments_menu

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class PrimerFragment : Fragment(R.layout.fragment_primer) {

    // Guardem les dades. Es llegeixen a onCreate() i després
    // es fan servir a onViewCreated() per la UI.
    private var nom: String = ""
    private var cognoms: String = ""
    private var edat: String = ""
    private var dataNaixement: String = ""
    private var llocNaixement: String = ""

    companion object {
        // Claus del Bundle (arguments).
        private const val ARG_NOM = "Nom"
        private const val ARG_COGNOMS = "Cognoms"
        private const val ARG_EDAT = "Edat"
        private const val ARG_DATA = "DataNaixement"
        private const val ARG_LLOC = "LlocNaixement"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Llegir el Bundle i guardar-lo en variables.
        arguments?.let {
            nom = it.getString(ARG_NOM).orEmpty()
            cognoms = it.getString(ARG_COGNOMS).orEmpty()
            edat = it.getString(ARG_EDAT).orEmpty()
            dataNaixement = it.getString(ARG_DATA).orEmpty()
            llocNaixement = it.getString(ARG_LLOC).orEmpty()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Construïm el text i el mostrem amb les variables rebudes del bundle
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