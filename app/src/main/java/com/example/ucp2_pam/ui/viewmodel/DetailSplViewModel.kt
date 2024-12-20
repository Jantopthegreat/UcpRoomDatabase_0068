package com.example.ucp2_pam.ui.viewmodel

import com.example.ucp2_pam.Data.Entity.Supplier




fun Supplier.toDetailSplUiEvent(): SupplierEvent {
    return SupplierEvent(
        id_spl = id_spl,
        nama_spl = nama_spl,
        kontak = kontak,
        alamat = alamat
    )
}