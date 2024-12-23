package com.example.ucp2_pam.ui.viewmodel.Barang

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2_pam.Data.Entity.Barang
import com.example.ucp2_pam.Repository.RepositoryBrg
import com.example.ucp2_pam.ui.navigation.DestinasiDetailBrg
import com.example.ucp2_pam.ui.viewmodel.Supplier.SupplierEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class DetailBrgViewModel (
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg,

    ) : ViewModel() {
    private val id_brg: String = checkNotNull(savedStateHandle[DestinasiDetailBrg.IDBrg])


    val detailBrgUiState: StateFlow<DetailBrgUiState> = repositoryBrg.getBrg(id_brg)
        .filterNotNull()
        .map {
            DetailBrgUiState(
                detailBrgUiEvent = it.toDetailBrgUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailBrgUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailBrgUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailBrgUiState(
                isLoading = true,
            ),
        )
    fun deleteBrg() {
        detailBrgUiState.value.detailBrgUiEvent.toBarangEntity().let {
            viewModelScope.launch {
                repositoryBrg.deleteBrg(it)
            }
        }
    }
}


data class DetailBrgUiState(
    val detailBrgUiEvent: BarangEvent = BarangEvent(),
    val supplier: SupplierEvent = SupplierEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
) {
    val isUiEventEmpty: Boolean
        get() = detailBrgUiEvent == BarangEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailBrgUiEvent != BarangEvent()
}

fun Barang.toDetailBrgUiEvent(): BarangEvent {
    return BarangEvent(
        id_brg = id_brg,
        nama_brg = nama_brg,
        deskripsi = deskripsi,
        harga = harga,
        stok = stok,
        nama_Suplier = nama_Suplier
    )
}





