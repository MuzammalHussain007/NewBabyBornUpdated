package com.example.newbabyborn.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.example.newbabyborn.modal.User
import com.google.gson.Gson

object AppStorage {
    private lateinit var prefs: SharedPreferences
    private val gson = Gson()

    fun init(context: Context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun getUser(): Boolean {
        return prefs.getBoolean("user_model",false)

    }

    fun setUser(type: Boolean) {
        prefs.edit().putBoolean("user_model", type).apply()
    }

    fun getItemPosition(): Int {
        return prefs.getInt("item",-1)

    }

    fun setItemPosition(type: Int) {
        prefs.edit().putInt("item", type).apply()
    }
    fun clearPreference(){
        prefs.edit().clear().apply()
    }

}