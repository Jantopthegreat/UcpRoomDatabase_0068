package com.example.ucp2_pam.ui.viewmodel.Supplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_pam.Data.Entity.Supplier
import com.example.ucp2_pam.Repository.RepositorySpl
import kotlinx.coroutines.launch

class SupplierViewModel (private val repositorySpl: RepositorySpl): ViewModel() {

    var uiState by mutableStateOf(SupplierUiState())

    fun updateState(supplierEvent: SupplierEvent){
        uiState = uiState.copy(
            supplierEvent = supplierEvent,

        )
    }

    fun validateFields(): Boolean{
        val event = uiState.supplierEvent
        val errorState = SupplierFormErrorState(
            id_spl = if (event.id_spl.isNotEmpty()) null else "ID tidak boleh kosong",
            nama_spl = if (event.nama_spl.isNotEmpty()) null else "Nama Supplier tidak boleh kosong",
            kontak = if (event.kontak.isNotEmpty()) null else "kontak tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()

    }
    fun saveData(){
        val currentEvent = uiState.supplierEvent

        if (validateFields()){
            viewModelScope.launch{
                try {
                    repositorySpl.insertSpl(currentEvent.toSupplierEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil Disimpan",
                        supplierEvent = SupplierEvent(),
                        isEntryValid = SupplierFormErrorState()
                    )

                }
                catch (e: Exception) {
                    uiState = uiState.copy(
                        snackBarMessage = " Data gagal Disimpan"
                    )
                }
            }
        } else {
            uiState = uiState.copy(
                snackBarMessage = " Input tidak valid Periksa kembali Data Anda!"
            )
        }
    }

    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }
}



data class SupplierFormErrorState(
    val id_spl: String? = null,
    val nama_spl: String? = null,
    val kontak: String? = null,
    val alamat: String? = null

) {

    fun isValid(): Boolean{
        return id_spl == null
                && nama_spl == null
                && kontak == null
                && alamat == null
    }
}

data class SupplierUiState(
    val supplierEvent: SupplierEvent = SupplierEvent(),
    val isEntryValid: SupplierFormErrorState = SupplierFormErrorState(),
    val snackBarMessage: String? = null
)

data class SupplierEvent (
    val id_spl: String = "",
    val nama_spl: String = "",
    val kontak: String = "",
    val alamat: String = ""
)



fun SupplierEvent.toSupplierEntity() : Supplier = Supplier (
    id_spl = id_spl,
    nama_spl = nama_spl,
    kontak = kontak,
    alamat = alamat
)