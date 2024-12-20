package com.example.ucp2_pam.ui.viewmodel

import com.example.ucp2_pam.Data.Entity.Barang



data class BarangEvent (
    val id: String = "",
    val nama_brg: String = "",
    val deskripsi: String = "",
    val harga: String = "",
    val stok: Int = 0,
    val nama_Suplier: String = "",
)

fun BarangEvent.toBarangEntiti(): Barang = Barang (
    id = id,
    nama_brg = nama_brg,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    nama_Suplier = nama_Suplier

)