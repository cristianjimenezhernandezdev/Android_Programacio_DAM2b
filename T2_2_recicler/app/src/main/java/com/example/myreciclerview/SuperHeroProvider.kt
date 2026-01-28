package com.example.myreciclerview

//Classe que ens servirà per definir tots els objectes SuperHero i que ens retornarà el llistat dins la variable superHeroList
class SuperHeroProvider {
    companion object{
        val superHeroList = listOf<SuperHero>(
            SuperHero("Rmart247", "Campalans", "Rubén Martínez", "https://agora.xtec.cat/iesrafaelcampalans/wp-content/uploads/usu1643/2017/09/logo_ribet_trans.png"),
            SuperHero("Daredevil", "Marvell", "MAtthew Michael Murdok", "https://cursokotlin.com/wp-content/uploads/2017/07/daredevil.jpg"),
            SuperHero("Wolverine", "Marvel", "James Howlett", "https://cursokotlin.com/wp-content/uploads/2017/07/logan.jpeg"),
            SuperHero("Flash", "DC", "Jay Garrick", "https://cursokotlin.com/wp-content/uploads/2017/07/flash.png"),
            SuperHero("Thor", "Marvel", "Thor Odinson", "https://cursokotlin.com/wp-content/uploads/2017/07/thor.jpg"))
    }
}