package com.example.preferencies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.preferencies.PreferenciesApp.Companion.prefs
import com.example.preferencies.databinding.ActivityResultBinding

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initUI()
    }
    fun initUI(){
        //
        binding.btnBack.setOnClickListener{
            prefs.wipe()
            onBackPressed()
        }
        //Agafem el nom
        val userName = prefs.getName()
        val userCognom = prefs.getCognom()
        val userLink = prefs.getLink()
        //Mostrem la benvinguda al nom guardat a la BDD
        binding.tvName.text = "Benvingut $userName"
        binding.tvCognom.text = "Cognoms: $userCognom"
        binding.tvLink.text = "Link: $userLink"


        //Recuperem el VIP
        if(prefs.getVip())
        {
            setVIPColorBackground()
        }
    }
    fun setVIPColorBackground()
    {
        binding.container.setBackgroundColor(ContextCompat.getColor(this,R.color.teal_200))
    }
}