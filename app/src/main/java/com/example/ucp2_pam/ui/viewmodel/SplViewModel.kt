package com.example.ucp2_pam.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_pam.Data.Entity.Barang
import com.example.ucp2_pam.Data.Entity.Supplier
import com.example.ucp2_pam.Repository.RepositoryBrg
import com.example.ucp2_pam.Repository.RepositorySpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class SplViewModel ( private val repositorySpl: RepositorySpl) : ViewModel() {

    val splUiState: StateFlow<SplUiState> = repositorySpl.getAllSpl()
        .filterNotNull()
        .map {
            SplUiState(
                listSpl = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit (SplUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                SplUiState(
                    isLoading = false,
                    isError = false,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SplUiState(
                isLoading = true,
            )
        )
}

data class SplUiState (
    val listSpl : List<Supplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)