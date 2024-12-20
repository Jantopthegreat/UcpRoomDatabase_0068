package com.example.ucp2_pam.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "supplier")
data class Supplier(
    @PrimaryKey
    val id_spl: String,
    val nama_spl: String,
    val kontak: String,
    val alamat: String,

    )
