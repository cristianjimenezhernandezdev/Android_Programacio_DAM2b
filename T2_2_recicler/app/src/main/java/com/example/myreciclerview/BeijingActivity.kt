package com.example.myreciclerview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class BeijingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Assignem el layout XML corresponent a aquesta Activity.
        setContentView(R.layout.activity_beijing)

        // Recuperem les dades passades des de la MainActivity via Intent Extras.
        // Si algun valor és null, utilitzem .orEmpty() per evitar errors.
        val city = intent.getStringExtra(MainActivity.EXTRA_CIUTAT).orEmpty()
        val province = intent.getStringExtra(MainActivity.EXTRA_PROVINCIA).orEmpty()
        val population = intent.getStringExtra(MainActivity.EXTRA_POBLACIO).orEmpty()
        val photo = intent.getStringExtra(MainActivity.EXTRA_PHOTO)

        // Busquem les vistes al layout (findViewById) i els hi assignem el valor rebut.
        // Nota: Aquí no usem ViewBinding per simplicitat en aquest exemple concret, o perquè el layout no té <layout>.
        findViewById<TextView>(R.id.tvCity).text = city
        findViewById<TextView>(R.id.tvProvinceValue).text = province
        findViewById<TextView>(R.id.tvPopulationValue).text = population

        // Carreguem la imatge amb Glide a l'ImageView corresponent.
        Glide.with(this).load(photo).into(findViewById<ImageView>(R.id.ivCity))

        // Configurem la barra d'acció per mostrar el títol de la ciutat i el botó "Enrere".
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = if (city.isNotBlank()) city else "Beijing"
    }

    // Gestiona l'acció de fer clic a la fletxa "Enrere" de la barra superior.
    override fun onSupportNavigateUp(): Boolean {
        // Simula que s'ha premut el botó físic "Enrere" d'Android, tancant l'Activity actual.
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
