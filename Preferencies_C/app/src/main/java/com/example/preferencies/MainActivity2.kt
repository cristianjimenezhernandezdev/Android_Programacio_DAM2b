package com.example.preferencies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.preferencies.PreferenciesApp.Companion.prefs
import com.example.preferencies.databinding.ActivityResultBinding

class MainActivity2 : AppCompatActivity() {
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
        binding.tvName.text = "Informal $userName"
        binding.tvCognom.text = "Cognoms Informal: $userCognom"
        binding.tvLink.text = "Link Informal: $userLink"


        //Recuperem el VIP
        if(prefs.getVip())
        {
            setVIPColorBackground()
        }
    }
    fun setVIPColorBackground()
    {
        binding.container.setBackgroundColor(ContextCompat.getColor(this,R.color.purple_200))
    }
}