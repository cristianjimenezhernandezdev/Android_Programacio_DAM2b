package com.example.preferencies

import android.app.Application

//Aquesta aplicació s'executarà abans que l'activityMaiun
class PreferenciesApp:Application() {

    //crearem una instancia de prefs perquè tothom pugui accedir
    companion object{
        lateinit var prefs: Prefs
    }
    //Reinplementem l'oncreate
    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(applicationContext)

    }
}