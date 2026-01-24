package com.example.t2_1_fragments_menu

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class SegonFragment : Fragment(R.layout.fragment_segon) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val estudis = arguments?.getStringArrayList("Estudis") ?: arrayListOf()

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
