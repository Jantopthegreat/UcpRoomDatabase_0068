package com.example.ucp2_pam.ui.viewmodel

import com.example.ucp2_pam.Data.Entity.Barang





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





