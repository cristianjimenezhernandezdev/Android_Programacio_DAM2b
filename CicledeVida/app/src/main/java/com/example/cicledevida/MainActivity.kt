package com.example.cicledevida

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        println("OnCreate")
    }

    override fun onStart() {
        super.onStart()

        Log.e("Missatge","OnStart()");
    }

    override fun onRestart() {
        super.onRestart()
        println("OnRestart")
    }

    override fun onStop() {
        super.onStop()
        println("OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("OnDestroy")
    }

    override fun onPause() {
        super.onPause()
        println("OnPause")
    }

    override fun onResume() {
        super.onResume()
        println("OnResume")

    }
}