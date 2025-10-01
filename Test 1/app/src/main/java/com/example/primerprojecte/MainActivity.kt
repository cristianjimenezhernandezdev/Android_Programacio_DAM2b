package com.example.primerprojecte

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//1- Mostreu per consola  La taula de multiplicar del 5
//
//2- Feu una funció que ens mostri Fred, Tebi o Calent segons una temperatura passada per paràmetre.
//
//3- Feu una funció que retorni l'arrel quadrada d'un número passat per paràmetre.

        setContentView(R.layout.activity_main)
        println("Hello, world!")
       //1
        val MultiplicarNumeros = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        for (num in MultiplicarNumeros) {
            val resultat=num*5

            println("multi: $resultat")

         //2- Feu una funció que ens mostri Fred, Tebi o Calent segons una temperatura passada per paràmetre.
         fun Sensaciotermica(temp: Int): String {
             if (temp<10)
             {
              return "Fred"
             }
             else if (temp>10&&temp<25)
                 return "Tebi"
             else
                 return "Calent"

         }
            println("La sensacio a 3 Graus és: ${Sensaciotermica(3)}")
            println("La sensacio a 20 Graus és: ${Sensaciotermica(20)}")
            println("La sensacio a 30 Graus és: ${Sensaciotermica(30)}")

            //3- Feu una funció que retorni l'arrel quadrada d'un número passat per paràmetre.

                fun Arrel(num: Int): Int {
                    var i = 1
                    while (i * i <= num) {
                        i++
                    }
                    return i-1
                }
                    println("Arrel  de 12 és: ${Arrel(12)}")
                    println("Arrel  de 20 és: ${Arrel(20)}")
                    println("Arrel  de 30 és: ${Arrel(30)}")






    }
}}


