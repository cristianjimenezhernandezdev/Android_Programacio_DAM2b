package com.example.ex01

import android.content.Context
import androidx.core.content.edit

class Prefs(context: Context) {
    val SHARED_NAME = "ex01_prefs"
    val SHARED_USER_NAME = "nom"
    val SHARED_USER_COGNOM = "cognoms"
    val SHARED_USER_LINK = "link"
    val SHARED_VIP = "vip"

    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    // Funcions per guardar
    fun saveName(name: String) {
        storage.edit { putString(SHARED_USER_NAME, name) }
    }

    fun saveCognom(name: String) {
        storage.edit { putString(SHARED_USER_COGNOM, name) }
    }

    fun saveLink(link: String) {
        storage.edit { putString(SHARED_USER_LINK, link) }
    }

    fun saveVIP(vip: Boolean) {
        storage.edit { putBoolean(SHARED_VIP, vip) }
    }

    // Funcions per recuperar
    fun getName(): String {
        return storage.getString(SHARED_USER_NAME, "")!!
    }

    fun getCognom(): String {
        return storage.getString(SHARED_USER_COGNOM, "")!!
    }

    fun getLink(): String {
        return storage.getString(SHARED_USER_LINK, "")!!
    }

    fun getVip(): Boolean {
        return storage.getBoolean(SHARED_VIP, false)
    }

    // Netejar totes les dades guardades
    fun wipe() {
        storage.edit { clear() }
    }

}