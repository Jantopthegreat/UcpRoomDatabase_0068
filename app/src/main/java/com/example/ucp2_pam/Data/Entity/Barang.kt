package com.example.ucp2_pam.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang")
data class Barang(
    @PrimaryKey
    val id: String,
    val nama_brg: String,
    val deskripsi: String,
    val Harga: String,
    val Stok: String,
    val Nama_Suplier: String,



)