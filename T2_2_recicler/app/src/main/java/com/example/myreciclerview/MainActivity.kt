package com.example.myreciclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myreciclerview.adapter.SuperHeroAdapter
import com.example.myreciclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }
    //Mètode encaregat de inflar el recyclerview amb els elements de la llista i el layout de item específic
    fun initRecyclerView()
    {
        binding.recyclerSuperHero.layoutManager=LinearLayoutManager(this)
        binding.recyclerSuperHero.adapter=SuperHeroAdapter(SuperHeroProvider.superHeroList, {superHero -> onItemSelected(superHero)})

    }
    //Creem la funció lambda que ens servirà per assignar a cada item del recyclerView una funció específica.
    fun onItemSelected(superHero: SuperHero)
    {
        Toast.makeText(this,superHero.superhero, Toast.LENGTH_SHORT).show()
    }
}