package com.example.t2_1_fragments_menu

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class TercerFragment : Fragment(R.layout.fragment_tercer) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val feines = arguments?.getStringArrayList("Feines") ?: arrayListOf()

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
