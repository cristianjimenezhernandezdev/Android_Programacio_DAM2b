package com.example.myreciclerview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class ChengduActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chengdu)

        val city = intent.getStringExtra(MainActivity.EXTRA_CIUTAT).orEmpty()
        val province = intent.getStringExtra(MainActivity.EXTRA_PROVINCIA).orEmpty()
        val population = intent.getStringExtra(MainActivity.EXTRA_POBLACIO).orEmpty()
        val photo = intent.getStringExtra(MainActivity.EXTRA_PHOTO)

        findViewById<TextView>(R.id.tvCity).text = city
        findViewById<TextView>(R.id.tvProvinceValue).text = province
        findViewById<TextView>(R.id.tvPopulationValue).text = population

        Glide.with(this).load(photo).into(findViewById<ImageView>(R.id.ivCity))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = if (city.isNotBlank()) city else "Chengdu"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
