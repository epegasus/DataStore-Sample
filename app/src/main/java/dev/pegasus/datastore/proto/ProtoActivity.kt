package dev.pegasus.datastore.proto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dev.pegasus.datastore.databinding.ActivityProtoBinding
import dev.pegasus.datastore.utils.launchWhenStarted
import kotlinx.coroutines.launch


class ProtoActivity : AppCompatActivity() {

    private val binding by lazy { ActivityProtoBinding.inflate(layoutInflater) }
    private val protoPreferenceManager by lazy { ProtoPreferenceManager(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fetchData()

        binding.mtContainer.setNavigationOnClickListener { finish() }
        binding.mbSave.setOnClickListener { onSaveClick() }
    }

    private fun fetchData() {
        launchWhenStarted {
            lifecycleScope.launch {
                protoPreferenceManager.searchRequestData.collect { request ->
                    "Query: ${request.query}".also { binding.mtvQuery.text = it }
                    "Page Number: ${request.pageNumber}".also { binding.mtvPageNumber.text = it }
                    "Results Per Page: ${request.resultsPerPage}".also { binding.mtvResultsPerPage.text = it }
                }
            }
        }
    }

    private fun onSaveClick() {
        val query = binding.etQuery.text.toString().trim()
        val pageNumber = binding.etPageNumber.text.toString().trim().toIntOrNull() ?: 0
        val resultsPerPage = binding.etResultsPerPage.text.toString().trim().toIntOrNull() ?: 0

        lifecycleScope.launch {
            protoPreferenceManager.saveSearchRequest(query, pageNumber, resultsPerPage)
        }
    }
}