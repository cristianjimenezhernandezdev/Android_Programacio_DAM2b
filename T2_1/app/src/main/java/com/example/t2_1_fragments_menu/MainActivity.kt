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
        setContentView(R.layout.activity_main)

        val navigation = findViewById<BottomNavigationView>(R.id.navMenu)

        navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemFragment1 -> {
                    val bundle = bundleOf(
                        "Nom" to "Cristian",
                        "Cognoms" to "Jimenez",
                        "Edat" to "39",
                        "DataNaixement" to "15-10-1986",
                        "LlocNaixement" to "Girona"
                    )
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace<PrimerFragment>(R.id.fragmentContainer, args = bundle)
                    }
                    return@setOnItemSelectedListener true
                }

                R.id.itemFragment2 -> {
                    val bundle = bundleOf(
                        "Estudis" to arrayListOf(
                            "CFGM Sistemes Microinformàtics i Xarxes - Institut La Garrotxa (2003-2005)",
                            "CFGS Desenvolupament d'Aplicacions Multiplataforma - IES Exemple (2024-2026)"
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
                            "Tècnic informàtic (2010-2015)",
                            "Desenvolupador Android Jr (2016-2020)",
                            "Desenvolupador Android (2020-Actualitat)"
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

        // Fragment inicial
        val bundle = bundleOf(
            "Nom" to "Cristian",
            "Cognoms" to "Jimenez",
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