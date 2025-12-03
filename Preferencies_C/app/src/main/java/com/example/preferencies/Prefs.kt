package com.example.preferencies

import android.content.Context

class Prefs(val context: Context) {
    //Necessitem el context de l'aplicació però el context només el tenen les classes propies d'android
    //Creem una constant amb el nom de la base de dades
    val SHARED_NAME ="Mydtb"
    val SHARED_USER_NAME ="nom"
    val SHARED_USER_COGNOM ="Cognom"
    val SHARED_USER_LINK ="Link"
    val SHARED_VIP="Vip"

    //Instrucció per recuperar les preferències
    val storage = context.getSharedPreferences(SHARED_NAME,0)


    //Funcións per guardar
    fun saveCognom(name:String){
        //Guardem el cognom
        storage.edit().putString(SHARED_USER_COGNOM,name).apply()
    }
    fun saveLink(name:String){
        //Guardem el link
        storage.edit().putString(SHARED_USER_LINK,name).apply()
    }
    fun saveName(name:String){
        //Guardem el nom
        storage.edit().putString(SHARED_USER_NAME,name).apply()
    }

    //Funció per guardar el vip (en el nostre cas formal)
    fun saveVIP(vip:Boolean){
        //Guardem a la nostra SharedPreference
        storage.edit().putBoolean(SHARED_VIP,vip).apply()
    }

    //Funcions per recuperar dades


    fun getName():String{
        //Posem el !! perquè sinó detecta que podria ser null. També podriem canviar el retorn a String?
        return storage.getString(SHARED_USER_NAME,"")!!
    }
    fun getCognom():String{
        return storage.getString(SHARED_USER_COGNOM,"")!!
    }
    fun getLink():String{
        return storage.getString(SHARED_USER_LINK,"")!!
    }

    //Funció per recuperar el vip (en el nostre cas modo formal)
    fun getVip():Boolean{
        return storage.getBoolean(SHARED_VIP,false)
    }

    //Netejar les dades guardades
    fun wipe(){
        storage.edit().clear().apply()
    }
}