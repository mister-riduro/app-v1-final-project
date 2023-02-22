package com.example.final_project.remote.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.final_project.remote.FinalProjectApplication
import com.example.final_project.remote.preferences.PrefConstants.PREF_KEY
import com.example.final_project.remote.preferences.PrefConstants.PREF_TOKEN

class Preferences private constructor(){
    private val mPrefs: SharedPreferences
    private val mEdit: SharedPreferences.Editor

    val token: String?
        get() = instance.mPrefs.getString(PREF_TOKEN, "")

    companion object {
        var INSTANCE: Preferences? = null
        val instance: Preferences
            get() {
                if (INSTANCE == null) INSTANCE = Preferences()
                return INSTANCE as Preferences
            }
    }

    init {
        val app: Application = FinalProjectApplication.instance
        mPrefs = app.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE)
        mEdit = mPrefs.edit()
    }
}