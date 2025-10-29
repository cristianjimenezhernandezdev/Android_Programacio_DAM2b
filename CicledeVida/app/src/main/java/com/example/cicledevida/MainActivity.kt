package com.example.cicledevida

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
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
    fun afegir(view: View){
        Log.d("Missatge",view.tag.toString())
        var textView: TextView
        textView=findViewById(R.id.textView)
        textView.text=textView.text.toString()+view.tag.toString()
    }
    fun esborrar(view: View){
        var textView: TextView
        textView=findViewById(R.id.textView)
        textView.text=""}
}