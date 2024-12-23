package com.example.ucp2_pam.ui.viewmodel.Barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_pam.Data.Entity.Barang
import com.example.ucp2_pam.Repository.RepositoryBrg
import kotlinx.coroutines.launch


class BarangViewModel (private val repositoryBrg: RepositoryBrg): ViewModel() {

    var uiState by mutableStateOf(BarangUiState())

    fun updateState(barangEvent: BarangEvent){
        uiState = uiState.copy(
            barangEvent = barangEvent,
        )
    }


    fun validateFields(): Boolean {
        val event = uiState.barangEvent
        val errorState = BarangFormErrorState(
            id_brg = if (event.id_brg.isNotEmpty()) null else "ID tidak boleh kosong",
            nama_brg = if (event.nama_brg.isNotEmpty()) null else "Nama Barang tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga tidak boleh kosong",
            nama_Suplier = if (event.nama_Suplier.isNotEmpty()) null else "Nama Supplier tidak boleh kosong",
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }


    fun saveData(){
        val currentEvent = uiState.barangEvent

        if (validateFields()){
            viewModelScope.launch{
                try {
                    repositoryBrg.insertBrg(currentEvent.toBarangEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data Berhasil Disimpan",
                        barangEvent = BarangEvent(),
                        isEntryValid = BarangFormErrorState()
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


data class BarangFormErrorState(
    val id_brg: String? = null,
    val nama_brg: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val nama_Suplier: String? = null,
) {

    fun isValid(): Boolean{
        return id_brg == null && nama_brg == null && deskripsi == null &&
                harga == null && stok == null && nama_Suplier == null
    }
}

data class BarangUiState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: BarangFormErrorState = BarangFormErrorState(),
    val snackBarMessage: String? = null,
)

data class BarangEvent (
    val id_brg: String = "",
    val nama_brg: String = "",
    val deskripsi: String = "",
    val harga: String = "",
    val stok: Int = 0,
    val nama_Suplier: String = "",
)

fun BarangEvent.toBarangEntity(): Barang = Barang (
    id_brg = id_brg,
    nama_brg = nama_brg,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    nama_Suplier = nama_Suplier

)