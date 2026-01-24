package com.example.t2_1_fragments_menu

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class SegonFragment : Fragment(R.layout.fragment_segon) {

    // Dades rebudes com a list. Les carguem a onCreate().
    private var estudis: ArrayList<String> = arrayListOf()

    companion object {
        // Clau del Bundle per fer servir la llista.
        private const val ARG_ESTUDIS = "Estudis"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Llegim els arguments (Bundle) abans de crear la vista.
        // getStringArrayList(...) retorna null si no existeix la clau, (?: arrayListOf()) control d'errors.
        estudis = arguments?.getStringArrayList(ARG_ESTUDIS) ?: arrayListOf()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Tenim la vista i la convertim en text per mostrar-la al TextView.
        val text = buildString {
            appendLine("Fragment 2")
            appendLine("Experiència acadèmica")
            appendLine()
            if (estudis.isEmpty()) {
                appendLine("(Sense dades)")
            } else {
                estudis.forEachIndexed { index, item ->
                    appendLine("${index + 1}. $item")
                }
            }
        }

        view.findViewById<TextView>(R.id.fragmentText).text = text
    }
}
