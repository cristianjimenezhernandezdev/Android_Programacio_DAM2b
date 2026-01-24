package com.example.t2_1_fragments_menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Punt d'entrada de l'Activity: carreguem el layout que conté
        // 1) el container (FrameLayout) on fem els Fragments i
        // 2) el BottomNavigationView per canviar de pantalla.
        setContentView(R.layout.activity_main)

        // Referència al menú inferior (BottomNavigationView) per detectar quin item es selecciona.
        val navigation = findViewById<BottomNavigationView>(R.id.navMenu)

        // Listener: quan l'usuari toca un item,
        // - preparem un Bundle
        // - i fem el canvi de Fragment amb el FragmentManager.
        navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemFragment1 -> {
                    // Enviem dades al al Fragment 1.
                    val bundle = bundleOf(
                        "Nom" to "Cristian",
                        "Cognoms" to "Jimenez Hernandez",
                        "Edat" to "39",
                        "DataNaixement" to "15-10-1986",
                        "LlocNaixement" to "Girona"
                    )

                    supportFragmentManager.commit {
                        setReorderingAllowed(true)

                        // Fem servir replace(): substitueix el Fragment actual
                        // pel nou Fragment dins del mateix container.
                        replace<PrimerFragment>(R.id.fragmentContainer, args = bundle)
                    }
                    return@setOnItemSelectedListener true
                }

                R.id.itemFragment2 -> {
                    // Dades en forma de llista: ArrayList<String>.
                    // Es passa al Fragment a través del Bundle i es recupera amb getStringArrayList(...).
                    val bundle = bundleOf(
                        "Estudis" to arrayListOf(
                            "Grau en Psicologia - Universitat de Girona (2011-2015)",
                            "CFGS Desenvolupament d'Aplicacions Multiplataforma - IES Campalans (2024-2026)"
                        )
                    )
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<SegonFragment>(R.id.fragmentContainer, args = bundle)
                    }
                    return@setOnItemSelectedListener true
                }

                R.id.itemFragment3 -> {

                    val bundle = bundleOf(
                        "Feines" to arrayListOf(
                            "Tècnic agronom - J.Sancho (2024-2025)",
                            "Oficial de Manteniment- La cellera de Ter (2026)"

                        )
                    )
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<TercerFragment>(R.id.fragmentContainer, args = bundle)
                    }
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

        // Fragment inicial:
        // - al tutorial s'afegeix amb add() com a primer Fragment que es veu en arrencar l'app.
        // - després, quan naveguem amb el menú inferior, fem replace().
        val bundle = bundleOf(
            "Nom" to "Cristian",
            "Cognoms" to "Jimenez Hernandez",
            "Edat" to "39",
            "DataNaixement" to "15-10-1986",
            "LlocNaixement" to "Girona"
        )
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<PrimerFragment>(R.id.fragmentContainer, args = bundle)
        }
    }
}