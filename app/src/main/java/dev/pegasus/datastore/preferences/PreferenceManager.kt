package dev.pegasus.datastore.preferences

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.asLiveData
import dev.pegasus.datastore.utils.GeneralUtils.TAG
import dev.pegasus.datastore.utils.GeneralUtils.showToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * @Author: SOHAIB AHMED
 * @Date: 27,April,2023
 * @Accounts
 *      -> https://github.com/epegasus
 *      -> https://stackoverflow.com/users/20440272/sohaib-ahmed
 */

class PreferenceManager(private val context: Context) {

    // Instance
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferencesDataStore")

    // Keys
    private val nameKey = stringPreferencesKey("name")
    private val scoreKey = intPreferencesKey("score")
    private val passKey = booleanPreferencesKey("pass")

    // Live Data
    val namePrefs = getNamePrefs().asLiveData()
    val scorePrefs = getScorePrefs().asLiveData()
    val passPrefs = getResultPrefs().asLiveData()

    // Fetch data
    private fun getNamePrefs(): Flow<String> = context.dataStore.data
        .catch {
            Log.e(TAG, "namePrefs: ", it)
            showToast(context, it)
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map {
            // No type safety.
            return@map it[nameKey] ?: "nill"
        }

    private fun getScorePrefs(): Flow<Int> = context.dataStore.data
        .map {
            // No type safety.
            return@map it[scoreKey] ?: 0
        }
        .catch {
            Log.e(TAG, "scorePrefs: ", it)
            showToast(context, it)
            if (it is IOException) {
                emit(-1)
            } else {
                throw it
            }
        }

    private fun getResultPrefs(): Flow<Boolean> = context.dataStore.data
        .map {
            // No type safety.
            return@map it[passKey] ?: false
        }
        .catch {
            Log.e(TAG, "passPrefs: ", it)
            showToast(context, it)
            if (it is IOException) {
                emit(false)
            } else {
                throw it
            }
        }

    // Save Data to Preferences
    fun save(name: String, scores: Int, result: Boolean) {
        CoroutineScope(Dispatchers.Main).launch {
            context.dataStore.edit {
                it[nameKey] = name
                it[scoreKey] = scores
                it[passKey] = result
            }
        }
    }
}