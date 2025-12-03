package com.example.preferencies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.preferencies.PreferenciesApp.Companion.prefs
import com.example.preferencies.databinding.ActivityMain3Binding


class MainActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initUI()
    }

    fun initUI(){
        // En clicar enrere, forcem que es borri tot (wipe)

        binding.btnBack.setOnClickListener{
            prefs.wipe()
            onBackPressed()
        }

        // Recuperem les dades directament del SharedPreferences
        // fent servir el prefs.
        val userName = prefs.getName()
        val userCognom = prefs.getCognom()
        val userLink = prefs.getLink()

        // Actualitzem la UI amb les dades internes.
        binding.tvName.text = "Benvingut $userName"
        binding.tvCognom.text = "Cognoms: $userCognom"
        binding.tvLink.text = "Link: $userLink"



        if(prefs.getVip())
        {
            colorFons()
        }
    }

    // Canviem el color de fons si és formal reutilitzo la funcio del vip de l'exemple
    fun colorFons()
    {
        binding.container.setBackgroundColor(ContextCompat.getColor(this,R.color.teal_200))
    }
}
