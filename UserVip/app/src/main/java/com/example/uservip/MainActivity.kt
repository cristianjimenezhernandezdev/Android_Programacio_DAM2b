package com.example.uservip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uservip.UserVipApplication.Companion.prefs
import com.example.uservip.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //inicialitzem l'activity
        initUI()
        //Accedim a les dades guardades
        checkUserValues()
    }
    fun checkUserValues()
    {
        //Si tenim dades guardades obrirem directament la ResultActivity
        if(prefs.getName().isNotEmpty())
        {
            goToDetail()
        }
    }

    fun initUI(){
        binding.btnContinuar.setOnClickListener{accesToDatail()}
    }

    //Accedim a les dades guardades
    fun accesToDatail()
    {
        //Validem el nom
        if(binding.etName.text.toString().isNotEmpty())
        {
            //Guarcem l'usuari
            prefs.saveName(binding.etName.text.toString())
            //Guardem el VIO
            prefs.saveVIP(binding.cbVip.isChecked)
            goToDetail()
        }
        else {
            //altre
        }
    }
    fun goToDetail()
    {
        startActivity(Intent(this,ResultActivity::class.java))
    }
}