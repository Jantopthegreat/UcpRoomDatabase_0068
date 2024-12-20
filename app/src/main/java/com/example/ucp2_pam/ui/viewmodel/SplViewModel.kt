package com.example.ucp2_pam.ui.viewmodel

import com.example.ucp2_pam.Data.Entity.Barang

data class SplUiState (
    val listBrg : List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)