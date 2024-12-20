package com.example.ucp2_pam.ui.viewmodel

import com.example.ucp2_pam.Data.Entity.Supplier


data class SupplierUiState(
    val supplierEvent: SupplierEvent,
    val isEntryValid: SupplierFormErrorState = SupplierFormErrorState(),
    val snackBarMessage: String? = null
)

data class SupplierEvent (
    val id: String = "",
    val nama_spl: String = "",
    val kontak: String = "",
    val alamat: String = ""
)



fun SupplierEvent.toSupplierEntity() : Supplier = Supplier (
    id = id,
    nama_spl = nama_spl,
    kontak = kontak,
    alamat = alamat
)