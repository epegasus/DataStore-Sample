package dev.pegasus.datastore.preferences

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.pegasus.datastore.databinding.ActivityPreferencesBinding

@SuppressLint("SetTextI18n")
class PreferencesActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPreferencesBinding.inflate(layoutInflater) }
    private val preferenceManager by lazy { PreferenceManager(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        fetchData()

        binding.mtContainer.setNavigationOnClickListener { finish() }
        binding.mbSave.setOnClickListener { onSaveClick() }
    }

    private fun fetchData() {
        preferenceManager.namePrefs.observe(this) { binding.mtvName.text = "Name: $it" }
        preferenceManager.scorePrefs.observe(this) { binding.mtvScore.text = "Score: $it" }
        preferenceManager.passPrefs.observe(this) { binding.mtvPass.text = "Pass: $it" }
    }

    private fun onSaveClick() {
        val name = binding.etName.text.toString().trim()
        val scores = binding.etScore.text.toString().trim().toInt()
        val result = binding.msResult.isChecked

        preferenceManager.save(name, scores, result)
    }
}