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

    fun getUser(): User {
        val json: String = prefs.getString("user_model", "")!!
        val obj: User = gson.fromJson(json, User::class.java)
        return obj
    }

    fun setUser(user: User) {
        val gson = Gson()
        val json = gson.toJson(user)
        prefs.edit().putString("user_model", json).apply()
    }

}