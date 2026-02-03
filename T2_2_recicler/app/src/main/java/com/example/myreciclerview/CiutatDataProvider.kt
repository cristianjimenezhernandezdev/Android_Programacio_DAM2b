package com.example.myreciclerview

//Classe que ens servirà per definir tots els objectes CiutatsXina i que ens retornarà el llistat dins la variable ciutatsXinaLists
class CiutatDataProvider {
    companion object{
        val ciutatsXinaLists = listOf<CiutatsXina>(
            CiutatsXina("Chongqing", "Sichuan", "32M", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Chongqing_Nightscape.jpg/1280px-Chongqing_Nightscape.jpg"),
            CiutatsXina("Shanghai", "Shanghai", "24.8M", "https://blog.chapkadirect.es/wp-content/uploads/2024/11/shanghai-imprescindibles-ver-1350x759.png"),
            CiutatsXina("Beijing", "Beijing", "22M", "https://www.iroamly.com/images/beijing-china-cover.webp"),
            CiutatsXina("Chengdu", "Sichuan", "21.5M", "https://www.civitatis.com/f/china/chengdu/chengdu.jpg"),
            CiutatsXina("Guangzhou", "Guangdong", "19M", "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/15/33/fd/6b/guangzhou.jpg?w=2400&h=1000&s=1"))
    }
}