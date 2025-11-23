package com.example.uservip

import android.app.Application

//Aquesta aplicació s'executarà abans que l'activityMaiun
class UserVipApplication:Application() {

    //crearem una instancia de prefs peruqè tothom pugui accedir
    companion object{
        lateinit var prefs: Prefs
    }
    //Reinplementem l'oncreate
    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(applicationContext)

    }
}