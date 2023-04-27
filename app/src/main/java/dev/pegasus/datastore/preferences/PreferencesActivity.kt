package dev.pegasus.datastore.preferences

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.pegasus.datastore.databinding.ActivityPreferencesBinding
import kotlinx.coroutines.flow.onEach

@SuppressLint("SetTextI18n")
class PreferencesActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPreferencesBinding.inflate(layoutInflater) }
    private val preferenceManager by lazy { PreferenceManager(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fetchData()

        binding.mtContainer.setNavigationOnClickListener { finish() }
    }

    private fun fetchData() {
        preferenceManager.namePrefs.onEach { binding.mtvName.text = "Name: $it" }
        preferenceManager.scorePrefs.onEach { binding.mtvScore.text = "Score: $it" }
        preferenceManager.passPrefs.onEach { binding.mtvPass.text = "Pass: $it" }
    }
}