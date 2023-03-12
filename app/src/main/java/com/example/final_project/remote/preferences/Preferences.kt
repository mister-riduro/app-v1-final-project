package com.example.final_project.remote.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.final_project.R
import com.example.final_project.remote.FinalProjectApplication
import com.example.final_project.remote.preferences.PrefConstants.PREF_KEY
import com.example.final_project.remote.preferences.PrefConstants.PREF_TOKEN
import com.example.final_project.remote.preferences.PrefConstants.USER_ID

object Preferences {
    fun setToken(context: Context, token: String) {
        saveString(context, PREF_TOKEN, token)
    }

    fun getToken(context: Context): String? {
        return getString(context, PREF_TOKEN)
    }

    fun setUserID(context: Context, userID: String) {
        saveString(context, USER_ID, userID)
    }

    fun getUserID(context: Context): String? {
        return getString(context, USER_ID)
    }

    fun saveString(context: Context, key: String, value: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(context: Context, key: String): String? {
        val prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getString(PREF_TOKEN, null)
    }

    fun clearData(context: Context){
        val editor = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }
}

//class Preferences private constructor(){
//    private val mPrefs: SharedPreferences
//    private val mEdit: SharedPreferences.Editor
//
//    val token: String?
//        get() = instance.mPrefs.getString(PREF_TOKEN, "")
//
//    companion object {
//        var INSTANCE: Preferences? = null
//        val instance: Preferences
//            get() {
//                if (INSTANCE == null) INSTANCE = Preferences()
//                return INSTANCE as Preferences
//            }
//    }
//
//    init {
//        val app: Application = FinalProjectApplication.instance
//        mPrefs = app.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
//        mEdit = mPrefs.edit()
//    }
//}