package com.example.uservip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.uservip.UserVipApplication.Companion.prefs
import com.example.uservip.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
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
        //Mostrem la benvinguda al nom guardat a la BDD
        binding.tvName.text = "Benvingut $userName"

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