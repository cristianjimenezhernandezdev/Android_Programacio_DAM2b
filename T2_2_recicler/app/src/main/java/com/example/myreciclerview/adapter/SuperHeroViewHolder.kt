package com.example.myreciclerview.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myreciclerview.R
import com.example.myreciclerview.SuperHero
import com.bumptech.glide.Glide
import com.example.myreciclerview.databinding.ItemSuperheroBinding


class SuperHeroViewHolder(view: View) :RecyclerView.ViewHolder(view){

    val binding = ItemSuperheroBinding.bind(view)

    //Aquesta funció pot tenir qualsevol nom i es cridarà per cada item de la llista autmaticament
    fun render(superHeroModel:SuperHero, onClickListener:(SuperHero)->Unit)
    {
        binding.tvSuperHeroName.text = superHeroModel.superhero
        binding.tvRealName.text = superHeroModel.realName
        binding.tvRealPublisher.text = superHeroModel.publisher

        //Fem servir la llibreria Glide per importar fotos a partir d'una URL
        Glide.with(binding.ivSuperHero).load(superHeroModel.photo).into(binding.ivSuperHero)
        //itemView fa referènia a tota la superficie de l'item
        itemView.setOnClickListener{onClickListener(superHeroModel)}
    }
}