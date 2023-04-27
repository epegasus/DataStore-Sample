package dev.pegasus.datastore.proto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.pegasus.datastore.databinding.ActivityProtoBinding

class ProtoActivity : AppCompatActivity() {

    private val binding by lazy { ActivityProtoBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}