package com.example.myreciclerview.adapter

import android.content.DialogInterface.OnClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myreciclerview.SuperHero
import  com.example.myreciclerview.R

//Adjuntem una lambda expressió per poder adjuntar la funció per cada item
class SuperHeroAdapter(private val superHeroList:List<SuperHero>,private val onClickListener:(SuperHero)->Unit):RecyclerView.Adapter<SuperHeroViewHolder>() {
    //Retornarà un ViewHolder per cada item de la llista superHeroList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        //Li assignem el layout definit específicament per cada element
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero,parent,false))
    }

    //Per cada item el que farà és cridar el mètode render
    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        val item = superHeroList[position]

        //Cridem el mètode definit al ViewHolder
        holder.render(item,onClickListener)
    }

    override fun getItemCount(): Int = superHeroList.size

}