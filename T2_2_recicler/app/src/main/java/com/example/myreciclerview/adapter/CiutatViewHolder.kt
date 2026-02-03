package com.example.myreciclerview.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myreciclerview.CiutatsXina
import com.bumptech.glide.Glide
import com.example.myreciclerview.databinding.ItemCiutatsBinding


// El ViewHolder manté les referències a les vistes de cada element de la llista.
// Utilitzem 'bind(view)' per generar la classe Binding a partir d'una vista ja inflada.
class CiutatViewHolder(view: View) :RecyclerView.ViewHolder(view){

    val binding = ItemCiutatsBinding.bind(view)

    // Funció encarregada d'assignar els valors de l'objecte (Model) als elements visuals (View/Binding).
    // Aquesta funció es crida automàticament per cada element de la llista.
    fun render(ciutatsXinaModel:CiutatsXina, onClickListener:(CiutatsXina)->Unit)
    {
        // Assignem textos
        binding.tvCiutat.text = ciutatsXinaModel.ciutat
        binding.tvHabitants.text = ciutatsXinaModel.habitants
        binding.tvProvincia.text = ciutatsXinaModel.provincia

        // Fem servir la llibreria Glide per carregar imatges des d'una URL de manera asíncrona i eficient.
        Glide.with(binding.ivCiutat.context).load(ciutatsXinaModel.photo).into(binding.ivCiutat)

        // itemView fa referència a tota l'arrel de l'item (tota la targeta).
        // Quan es clica, executem la funció lambda 'onClickListener' passant-li la ciutat actual.
        // Això envia l'esdeveniment cap amunt, fins a l'Adapter i finalment a la MainActivity.
        itemView.setOnClickListener{onClickListener(ciutatsXinaModel)}
    }
}