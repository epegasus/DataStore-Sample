package dev.pegasus.datastore.preferences

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.pegasus.datastore.databinding.ActivityPreferencesBinding

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
        preferenceManager.namePrefs.observe(this) { "Name: $it".also { binding.mtvName.text = it } }
        preferenceManager.scorePrefs.observe(this) { "Score: $it".also { binding.mtvScore.text = it } }
        preferenceManager.passPrefs.observe(this) { "Pass: $it".also { binding.mtvPass.text = it } }
    }

    private fun onSaveClick() {
        if (binding.etName.text?.isEmpty() == true || binding.etScore.text?.isEmpty() == true) {
            Toast.makeText(this, "Fields can't be empty", Toast.LENGTH_SHORT).show()
            return
        }
        val name = binding.etName.text.toString().trim()
        val scores = binding.etScore.text.toString().trim().toIntOrNull() ?: 0
        val result = binding.msResult.isChecked

        preferenceManager.save(name, scores, result)
    }
}