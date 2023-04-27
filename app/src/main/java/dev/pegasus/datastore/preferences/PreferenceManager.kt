package dev.pegasus.datastore.preferences

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dev.pegasus.datastore.utils.GeneralUtils.TAG
import dev.pegasus.datastore.utils.GeneralUtils.showToast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * @Author: SOHAIB AHMED
 * @Date: 27,April,2023
 * @Accounts
 *      -> https://github.com/epegasus
 *      -> https://stackoverflow.com/users/20440272/sohaib-ahmed
 */

class PreferenceManager(context: Context) {

    // Instance
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferencesDataStore")

    // Keys
    private val name = stringPreferencesKey("name")
    private val score = intPreferencesKey("score")
    private val pass = booleanPreferencesKey("pass")

    // Fetch data
    val namePrefs: Flow<String> = context.dataStore.data
        .map {
            // No type safety.
            it[name] ?: "nill"
        }
        .catch {
            Log.e(TAG, "namePrefs: ", it)
            showToast(context, it)
        }

    val scorePrefs: Flow<Int> = context.dataStore.data
        .map {
            // No type safety.
            it[score] ?: 0
        }
        .catch {
            Log.e(TAG, "scorePrefs: ", it)
            showToast(context, it)
        }

    val passPrefs: Flow<Boolean> = context.dataStore.data
        .map {
            // No type safety.
            it[pass] ?: false
        }
        .catch {
            Log.e(TAG, "passPrefs: ", it)
            showToast(context, it)
        }
}