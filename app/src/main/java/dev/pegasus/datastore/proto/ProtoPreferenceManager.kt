package dev.pegasus.datastore.proto

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import dev.pegasus.datastore.SearchRequest
import dev.pegasus.datastore.utils.protoDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class ProtoPreferenceManager(private val context: Context) {

    // Expose data as Flow
    val searchRequestData: Flow<SearchRequest> = context.protoDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(SearchRequest.getDefaultInstance()) // Default instance in case of error
            } else {
                throw exception
            }
        }

    // Save Data
    suspend fun saveSearchRequest(query: String, pageNumber: Int, resultsPerPage: Int) {
        context.protoDataStore.updateData { currentRequest ->
            currentRequest.toBuilder()
                .setQuery(query)
                .setPageNumber(pageNumber)
                .setResultsPerPage(resultsPerPage)
                .build()
        }
    }

    // Serializer for SearchRequest Proto
    object SearchRequestSerializer : Serializer<SearchRequest> {
        override val defaultValue: SearchRequest = SearchRequest.getDefaultInstance()

        override suspend fun readFrom(input: InputStream): SearchRequest {
            return try {
                SearchRequest.parseFrom(input)
            } catch (exception: IOException) {
                throw CorruptionException("Cannot read proto.", exception)
            }
        }

        override suspend fun writeTo(t: SearchRequest, output: OutputStream) = t.writeTo(output)
    }
}