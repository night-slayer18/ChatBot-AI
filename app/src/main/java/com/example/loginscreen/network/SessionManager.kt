package com.example.loginscreen.network

import android.content.Context
import android.content.SharedPreferences
import com.example.loginscreen.R

object SessionManager {

    const val USER_TOKEN = ""


    const val USER_NAME = ""


    //change if nec


    /**
     * Function to save auth token
     */
    fun saveAuthToken(context: Context, token: String) {
        saveString(context, USER_TOKEN, token)
    }
    fun saveUserName(context: Context,name: String){
        saveString2(context, USER_NAME,name)
    }


/*    fun saveFirstName(context: Context,name:String){
        saveString(context, FIRST_NAME,name)
    }
    fun saveLastName(context: Context,name:String){
        saveString(context, LAST_NAME,name)
    }

    fun getFirstName(context: Context): String?{
        return getString2(context, FIRST_NAME)
    }
    fun getLastName(context: Context): String?{
        return getString3(context, LAST_NAME)
    }*/


    /**
     * Function to fetch auth token
     */
    fun getToken(context: Context): String? {
        return getStrings(context, USER_TOKEN)
    }
    fun getUserName(context: Context): String?{
        return getString2(context, USER_NAME)
    }


    fun saveString(context: Context, key: String, value: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.sp1), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()

    }
    fun saveString2(context: Context, key: String, value: String) {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.sp2), Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()

    }


    fun getStrings(context: Context, key: String): String? {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.sp1), Context.MODE_PRIVATE)
        return prefs.getString(USER_TOKEN, null)
    }
    fun getString2(context: Context, key: String): String? {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.sp2), Context.MODE_PRIVATE)
        return prefs.getString(USER_NAME, null)
    }


    /*fun getString2(context: Context, key: String): String? {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getString(FIRST_NAME, null)
    }

    fun getString3(context: Context, key: String): String? {
        val prefs: SharedPreferences =
            context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        return prefs.getString(LAST_NAME, null)
    }*/

    fun clearData(context: Context){
        val editor = context?.getSharedPreferences(context.getString(R.string.sp1), Context.MODE_PRIVATE)?.edit()
        editor?.clear()
        editor?.apply()
    }
    fun clearData2(context: Context){
        val editor = context?.getSharedPreferences(context.getString(R.string.sp3), Context.MODE_PRIVATE)?.edit()
        editor?.clear()
        editor?.apply()
    }


}