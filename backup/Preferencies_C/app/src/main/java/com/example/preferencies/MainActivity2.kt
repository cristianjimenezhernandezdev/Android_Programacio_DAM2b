package com.example.preferencies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.preferencies.PreferenciesApp.Companion.prefs
import com.example.preferencies.databinding.ActivityMain3Binding

class MainActivity2 : AppCompatActivity() {

    // Utilitzem el binding generat pel layout 'activity_main3.xml' (ActivityMain3Binding)
    // tot i que estem dins de la classe MainActivity2. amb aixo  reutilitzem.
    private lateinit var binding: ActivityMain3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initUI()
    }

    fun initUI(){
        // Esborra totes les dades guardades (wipe)
        // i torna a la pantalla anterior.
        binding.btnBack.setOnClickListener{
            prefs.wipe()
            onBackPressed()
        }

        // Recuperem les dades a SharedPreferences
        // a través del 'prefs'.
        val userName = prefs.getName()
        val userCognom = prefs.getCognom()
        val userLink = prefs.getLink()

        // Actualitzem la interfície amb les dades recuperades.

        binding.tvName.text = "Informal $userName"
        binding.tvCognom.text = "Cognoms Informal: $userCognom"
        binding.tvLink.text = "Link Informal: $userLink"


        // Canviem el color de fons si és formal reutilitzo la funcio del vip de l'exemple
        if(prefs.getVip())
        {
            setVIPColorBackground()
        }
    }

    // Canvia el color de fons 
    fun setVIPColorBackground()
    {
        binding.container.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_200))
    }
}
