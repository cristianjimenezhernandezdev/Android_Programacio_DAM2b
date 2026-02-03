package com.example.myreciclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myreciclerview.CiutatsXina
import  com.example.myreciclerview.R

// L'Adapter és el pont entre les dades (Llista) i el RecyclerView.
// Rep la llista de ciutats i una funció 'lambda' per gestionar el clic (callback a MainActivity).
class CiutatAdapter(private val ciutatsXinaList:List<CiutatsXina>, private val onClickListener:(CiutatsXina)->Unit):RecyclerView.Adapter<CiutatViewHolder>() {

    // Aquest mètode es crida quan el RecyclerView necessita un nou ViewHolder (una nova casella visual).
    // Això passa només per als elements que caben en pantalla + un petit buffer. Després es reciclen.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CiutatViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        // Infla el layout XML de l'ítem individual (item_ciutats.xml) i crea el ViewHolder.
        return CiutatViewHolder(layoutInflater.inflate(R.layout.item_ciutats,parent,false))
    }

    // Aquest mètode connecta les dades d'una posició concreta de la llista amb un ViewHolder existent.
    // Es crida moltes vegades (cada cop que fem scroll i apareix un element nou).
    override fun onBindViewHolder(holder: CiutatViewHolder, position: Int) {
        val item = ciutatsXinaList[position]

        // Deleguem al ViewHolder la tasca de "pintar" les dades a les vistes.
        // També passem el listener perquè el ViewHolder sàpiga què fer quan es clica.
        holder.render(item,onClickListener)
    }

    // Indica al RecyclerView quants elements totals té la llista.
    override fun getItemCount(): Int = ciutatsXinaList.size

}