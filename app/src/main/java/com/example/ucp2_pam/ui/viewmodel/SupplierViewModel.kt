package com.example.ucp2_pam.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.ucp2_pam.Data.Entity.Supplier
import com.example.ucp2_pam.Repository.RepositoryBrg
import com.example.ucp2_pam.Repository.RepositorySpl

class SupplierViewModel (private val repositorySpl: RepositorySpl): ViewModel() {

    var uiState by mutableStateOf(SupplierUiState())

    fun updateState(barangEvent: BarangEvent){
        uiState = uiState.copy(
            supplierEvent = supplierEvent,
        )
    }


data class SupplierFormErrorState(
    val id: String? = null,
    val nama_spl: String? = null,
    val kontak: String? = null,
    val alamat: String? = null

) {

    fun isValid(): Boolean{
        return id == null && nama_spl == null && kontak == null &&
                alamat == null
    }
}

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