package com.example.myreciclerview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class BeijingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Assignem el layout XML
        setContentView(R.layout.activity_beijing)

        // Recuperem les dades passades des de la MainActivity via Intent Extras.
        // Si algun valor és null, utilitzem .orEmpty() per evitar errors.
        val ciutat = intent.getStringExtra(MainActivity.EXTRA_CIUTAT).orEmpty()
        val provincia = intent.getStringExtra(MainActivity.EXTRA_PROVINCIA).orEmpty()
        val poblacio = intent.getStringExtra(MainActivity.EXTRA_POBLACIO).orEmpty()
        val photo = intent.getStringExtra(MainActivity.EXTRA_PHOTO)


        //Aqui és mes directe fer servir el Id
        findViewById<TextView>(R.id.tvCity).text = ciutat
        findViewById<TextView>(R.id.tvProvinceValue).text = provincia
        findViewById<TextView>(R.id.tvPopulationValue).text = poblacio

        // carguesm la imatge amb Glide al ImageView.
        Glide.with(this).load(photo).into(findViewById<ImageView>(R.id.ivCity))

        // Posem el boto per navegar enrere. Aquesta part es codi nou.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = if (ciutat.isNotBlank()) ciutat else "Beijing"
    }


    override fun onSupportNavigateUp(): Boolean {

        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
