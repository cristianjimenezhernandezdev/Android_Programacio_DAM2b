package com.example.ex01
import android.app.Application

class Preferences {
    companion object{
        lateinit var prefs: Prefs
    }
    //Reinplementem l'oncreate
    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(applicationContext)

    }
}