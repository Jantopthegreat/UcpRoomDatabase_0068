package com.example.ucp2_pam.ui.viewmodel

import com.example.ucp2_pam.Data.Entity.Barang





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
        id = id,
        nama_brg = nama_brg,
        deskripsi = deskripsi,
        harga = harga,
        stok = stok,
        nama_Suplier = nama_Suplier
    )
}





