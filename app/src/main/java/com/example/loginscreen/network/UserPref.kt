package com.example.loginscreen.network

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPref (context: Context){

    private val appContext = context.applicationContext
    var userToken: String? = null

    companion object {
        private const val USER_PREFERENCES_NAME = "user_data_store"
        val KEY_AUTH = stringPreferencesKey("key_auth")

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES_NAME)
    }

    val authToken: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            userToken = preferences[KEY_AUTH]
            //Log.d("DEBUG_LOG_PRINT", "USER_AUTH_TOKEN: $userToken")
            preferences[KEY_AUTH]
        }

 /*   suspend fun saveAuthToken(authToken: String) {
        appContext.dataStore.edit { preferences ->
            preferences[KEY_AUTH] = authToken
        }
    }

    suspend fun removeAuthToken() {
        appContext.dataStore.edit {
            if(it.contains(KEY_AUTH)) {
                it.remove(KEY_AUTH)
            }
        }
    }

    suspend fun removeAll() {
        appContext.dataStore.edit {
            it.clear()
        }
    }*/
}