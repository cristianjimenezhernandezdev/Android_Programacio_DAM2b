package com.example.uservip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
        if(binding.etName.text.toString().isNotEmpty()&&binding.etCognom.text.toString().isNotEmpty()&&binding.etLink.text.toString().isNotEmpty())
        {
            //Guarcem l'usuari
            prefs.saveName(binding.etName.text.toString())
            prefs.saveCognom(binding.etCognom.text.toString())
            prefs.saveLink(binding.etLink.text.toString())
                     //Guardem el VIO
            prefs.saveVIP(binding.cbVip.isChecked)
            goToDetail()
        }
        else {

            //posem el toast
            Toast.makeText(this, "Les dades no poden ser buides", Toast.LENGTH_SHORT).show()
        }
    }


    fun goToDetail(){
    if(prefs.getVip())
    {
        startActivity(Intent(this,ResultActivity::class.java))
    }

    else
    {
        startActivity(Intent(this, MainActivity2::class.java))
    }
    }


}