package com.example.ucp2_pam.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_pam.Data.Entity.Barang
import com.example.ucp2_pam.Repository.RepositoryBrg
import kotlinx.coroutines.launch


class BarangViewModel (private  val repositoryBrg: RepositoryBrg): ViewModel() {

    var uiState by mutableStateOf(BarangUiState())

    fun updateState(barangEvent: BarangEvent){
        uiState = uiState.copy(
            barangEvent = barangEvent,
        )
    }


data class FormErrorState(
    val id: String? = null,
    val nama_brg: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val nama_Suplier: String? = null,
) {

    fun isValid(): Boolean{
        return id == null && nama_brg == null && deskripsi == null &&
                harga == null && stok == null && nama_Suplier == null
    }
}

data class BarangUiState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null,
)

data class BarangEvent (
    val id: String = "",
    val nama_brg: String = "",
    val deskripsi: String = "",
    val harga: String = "",
    val stok: Int = 0,
    val nama_Suplier: String = "",
)

fun BarangEvent.toBarangEntity(): Barang = Barang (
    id = id,
    nama_brg = nama_brg,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    nama_Suplier = nama_Suplier

)