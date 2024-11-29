package dev.pegasus.datastore.utils


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dev.pegasus.datastore.SearchRequest
import dev.pegasus.datastore.proto.ProtoPreferenceManager

// Singleton for Proto DataStore
val Context.protoDataStore: DataStore<SearchRequest> by dataStore(
    fileName = "search_request.pb",
    serializer = ProtoPreferenceManager.SearchRequestSerializer
)

// Singleton for Preferences DataStore
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "preferencesDataStore"
)