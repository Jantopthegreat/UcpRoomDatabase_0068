package com.example.ucp2_pam.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ucp2_pam.Data.Entity.Barang
import com.example.ucp2_pam.Repository.RepositoryBrg


class DetailBrgViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg,

    ) : ViewModel() {
    private val id_brg: String = checkNotNull(savedStateHandle[DestinasiDetail.NIM])



data class DetailBrgUiState(
    val detailBrgUiEvent: BarangEvent = BarangEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailBrgUiEvent == BarangEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailBrgUiEvent != BarangEvent()
}

fun Barang.toDetailBrgUiEvent(): BarangEvent {
    return BarangEvent(
        id_brg = id_brg,
        nama_brg = nama_brg,
        deskripsi = deskripsi,
        harga = harga,
        stok = stok,
        nama_Suplier = nama_Suplier
    )
}





