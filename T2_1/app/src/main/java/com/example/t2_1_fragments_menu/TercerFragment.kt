package com.example.t2_1_fragments_menu

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class TercerFragment : Fragment(R.layout.fragment_tercer) {

    // Llista rebuda.
    private var feines: ArrayList<String> = arrayListOf()

    companion object {
        // Clau del Bundle per recuperar la llista.
        private const val ARG_FEINES = "Feines"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Llegim el bundle
        feines = arguments?.getStringArrayList(ARG_FEINES) ?: arrayListOf()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fem servir el Foreach per que la vagi mostrant per ordre.
        val text = buildString {
            appendLine("Fragment 3")
            appendLine("Experiència laboral")
            appendLine()
            if (feines.isEmpty()) {
                appendLine("(Sense dades)")
            } else {
                feines.forEachIndexed { index, item ->
                    appendLine("${index + 1}. $item")
                }
            }
        }

        view.findViewById<TextView>(R.id.fragmentText).text = text
    }
}
