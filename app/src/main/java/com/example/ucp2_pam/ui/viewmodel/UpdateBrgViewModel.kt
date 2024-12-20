package com.example.ucp2_pam.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_pam.Data.Entity.Barang
import kotlinx.coroutines.launch


fun Barang.toUIStateBrg () : BarangUiState = BarangUiState (
    barangEvent = this.toDetailBrgUiEvent (),
)