package com.example.myreciclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myreciclerview.adapter.CiutatAdapter
import com.example.myreciclerview.databinding.ActivityMainBinding
import android.content.Intent

class MainActivity : AppCompatActivity() {
    // ViewBinding per fer el binding entre el layout i l'Activity.
    // Això evita fer 'findViewById' per a cada cosa.
    private lateinit var binding: ActivityMainBinding

    companion object {
        // Constants per saber les dades que passem entre Activities amb el Intent (EXTRAS).
        const val EXTRA_CIUTAT = "extra_city"
        const val EXTRA_PROVINCIA = "extra_province"
        const val EXTRA_POBLACIO = "extra_population"
        const val EXTRA_PHOTO = "extra_photo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflem el layout utilitzant Binding.
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Iniciem la configuració del llistat (RecyclerView)
        initRecyclerView()
    }

    // Mètode encarregat del RecyclerView amb  dades i adapter
    fun initRecyclerView() {
        // Assignem un LayoutManager: LinearLayoutManager mostra els elements en una llista vertical.
        binding.recyclerSuperHero.layoutManager = LinearLayoutManager(this)

        // Assignem l'Adapter: és qui connecta les dades (Llista de Ciutats) amb la vista (RecyclerView).

        // La llista: CiutatDataProvider.ciutatsXinaLists

        binding.recyclerSuperHero.adapter = CiutatAdapter(CiutatDataProvider.ciutatsXinaLists, { superHero -> onItemSelected(superHero) })
    }

    // Per quan es fa click a sobre d'una ciutat
    // 'ciutatsXina' és l'objecte de corresponent a l'element que es fa click.
    fun onItemSelected(ciutatsXina: CiutatsXina) {
        // Decidim quina Activity obrir
        val intent = when (ciutatsXina.ciutat) {
            "Beijing" -> Intent(this, BeijingActivity::class.java)
            "Shanghai" -> Intent(this, ShanghaiActivity::class.java)
            "Chongqing" -> Intent(this, ChongqingActivity::class.java)
            "Chengdu" -> Intent(this, ChengduActivity::class.java)
            "Guangzhou" -> Intent(this, GuangzhouActivity::class.java)
            else -> Intent(this, BeijingActivity::class.java) // Activity per defecte o gestió d'errors
        }

        // Passem les dades de la ciutat a la nova Activity amb els "Extras" dins l'Intent.
        intent.putExtra(EXTRA_CIUTAT, ciutatsXina.ciutat)
        intent.putExtra(EXTRA_PROVINCIA, ciutatsXina.provincia)
        intent.putExtra(EXTRA_POBLACIO, ciutatsXina.habitants)
        intent.putExtra(EXTRA_PHOTO, ciutatsXina.photo)

        // Iniciem la nova Activity.
        startActivity(intent)
    }
}