package dev.pegasus.datastore.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.withCreated
import androidx.lifecycle.withResumed
import androidx.lifecycle.withStarted
import kotlinx.coroutines.launch

/**
 *   Developer: Sohaib Ahmed
 *   Date: 11/29/2024
 *   Profile:
 *     -> github.com/epegasus
 *     -> linkedin.com/in/epegasus
 */

fun LifecycleOwner.launchWhenCreated(callback: () -> Unit) {
    lifecycleScope.launch { lifecycle.withCreated(callback) }
}

fun LifecycleOwner.launchWhenStarted(callback: () -> Unit) {
    lifecycleScope.launch { lifecycle.withStarted(callback) }
}

fun LifecycleOwner.launchWhenResumed(callback: () -> Unit) {
    lifecycleScope.launch { lifecycle.withResumed(callback) }
}