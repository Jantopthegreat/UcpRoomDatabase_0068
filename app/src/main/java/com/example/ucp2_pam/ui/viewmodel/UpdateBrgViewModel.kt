package com.example.ucp2_pam.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_pam.Data.Entity.Barang
import com.example.ucp2_pam.Repository.RepositoryBrg
import com.example.ucp2_pam.ui.navigation.DestinasiUpdateBrg
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMhsViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg
) : ViewModel() {

    var updateUIState by mutableStateOf(BarangUiState())
        private set

    private val _id: String = checkNotNull(savedStateHandle[DestinasiUpdateBrg.ID])

    init {
        viewModelScope.launch {
            updateUIState = repositoryBrg.getBrg(_id)
                .filterNotNull()
                .first()
                .toUiStateBrg()
        }
    }

    fun updateState(barangEvent: BarangEvent) {
        updateUIState = updateUIState.copy(
            barangEvent = BarangEvent,
        )
    }



fun Barang.toUiStateBrg () : BarangUiState = BarangUiState (
    barangEvent = this.toDetailBrgUiEvent (),
)