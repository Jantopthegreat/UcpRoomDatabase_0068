package com.example.ucp2_pam.ui.viewmodel

import com.example.ucp2_pam.Data.Entity.Supplier


data class DetailSplUiState(
    val detailSplUiEvent: SupplierEvent = SupplierEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailSplUiEvent == SupplierEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailSplUiEvent != SupplierEvent()
}

fun Supplier.toDetailSplUiEvent(): SupplierEvent {
    return SupplierEvent(
        id_spl = id_spl,
        nama_spl = nama_spl,
        kontak = kontak,
        alamat = alamat
    )
}