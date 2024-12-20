package com.example.ucp2_pam.ui.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_pam.Repository.RepositoryBrg
import com.example.ucp2_pam.Repository.RepositorySpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import java.time.LocalTime

class HomeViewModel : ViewModel() {
    private val _greeting = MutableStateFlow("Selamat")
    val greeting: StateFlow<String> get() = _greeting

    init {
        updateGreeting()
    }

    private fun updateGreeting() {
        val hour = LocalTime.now().hour
        _greeting.value = when (hour) {
            in 5..11 -> "Selamat Pagi"
            in 12..15 -> "Selamat Siang"
            in 16..18 -> "Selamat Sore"
            else -> "Selamat Malam"
        }
    }
}