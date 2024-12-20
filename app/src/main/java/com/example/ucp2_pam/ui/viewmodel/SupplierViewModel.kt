package com.example.ucp2_pam.ui.viewmodel

import com.example.ucp2_pam.Data.Entity.Supplier


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