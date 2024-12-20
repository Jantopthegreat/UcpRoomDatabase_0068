package com.example.ucp2_pam.ui.viewmodel.Barang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_pam.Data.Entity.Barang
import com.example.ucp2_pam.Repository.RepositoryBrg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeBrgViewModel(private val repositoryBrg: RepositoryBrg) : ViewModel() {

    val brgUiState: StateFlow<BrgUiState> = repositoryBrg.getAllBrg()
        .filterNotNull()
        .map { listBarang ->
            BrgUiState(
                listBrg = listBarang,
                cardColors = listBarang.map { barang -> getCardColor(barang.stok) },
                isLoading = false
            )
        }
        .onStart {
            emit(BrgUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                BrgUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = BrgUiState(
                isLoading = true
            )
        )

    private fun getCardColor(stok: Int): String {
        return when {
            stok == 0 -> "gray"
            stok in 1..10 -> "red"
            else -> "green"
        }
    }
}

data class BrgUiState(
    val listBrg: List<Barang> = listOf(),
    val cardColors: List<String> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
