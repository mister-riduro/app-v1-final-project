package com.example.final_project.remote.preferences

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.final_project.R
import com.example.final_project.remote.FinalProjectApplication
import com.example.final_project.remote.preferences.PrefConstants.PREF_EXPIRED_TIME
import com.example.final_project.remote.preferences.PrefConstants.PREF_KEY
import com.example.final_project.remote.preferences.PrefConstants.PREF_LOGGED_IN
import com.example.final_project.remote.preferences.PrefConstants.PREF_TOKEN
import com.example.final_project.remote.preferences.PrefConstants.PREF_USER_ID
import java.util.Calendar

class Preferences private constructor() {
    private val mPrefs: SharedPreferences
    private val mEdit: SharedPreferences.Editor

    val token: String?
        get() = instance.mPrefs.getString(PREF_TOKEN, "")

    val userID: String?
        get() = instance.mPrefs.getString(PREF_USER_ID, "")

    val loggedIn: Boolean
        get() = instance.mPrefs.getBoolean(PREF_LOGGED_IN, false)

    val expiredTime: Long
        get() = instance.mPrefs.getLong(PREF_EXPIRED_TIME, 0L)

    fun setToken(value: String) {
        mEdit.putString(PREF_TOKEN, value)
        mEdit.apply()
    }

    fun setUserID(value: String) {
        mEdit.putString(PREF_USER_ID, value)
        mEdit.apply()
    }

    fun setExpirationTime(value: Int) {
        val cal = Calendar.getInstance()
        cal.add(Calendar.SECOND, value)
        mEdit.putLong(PREF_EXPIRED_TIME, cal.timeInMillis)
        mEdit.apply()
    }

    fun isLoggedIn(value: Boolean) {
        mEdit.putBoolean(PREF_LOGGED_IN, value)
        mEdit.apply()
    }

    fun clearData(context: Context){
        val editor = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.apply()
    }

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