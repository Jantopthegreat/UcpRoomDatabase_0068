package com.example.ucp2_pam.ui.view.Supplier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_pam.ui.customwidget.TopAppBar
import com.example.ucp2_pam.ui.viewmodel.PenyediaViewModel
import com.example.ucp2_pam.ui.viewmodel.Supplier.SupplierEvent
import com.example.ucp2_pam.ui.viewmodel.Supplier.SupplierFormErrorState
import com.example.ucp2_pam.ui.viewmodel.Supplier.SupplierUiState
import com.example.ucp2_pam.ui.viewmodel.Supplier.SupplierViewModel
import kotlinx.coroutines.launch


@Composable
fun InsertSupplierView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    onLogoClick: () -> Unit = { },
    modifier: Modifier = Modifier,
    viewModel: SupplierViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Tambah Supplier",
                onLogoClick = onLogoClick
            )

            InsertBodySupplier (
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    viewModel.saveData()
                    onNavigate()
                },
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}
@Composable
fun InsertBodySupplier(
    modifier: Modifier = Modifier,
    onValueChange: (SupplierEvent) -> Unit,
    uiState: SupplierUiState,
    onClick: () -> Unit
) {
    Column(
        modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormSupplier(
            supplierEvent = uiState.supplierEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4C0062)
            )
        ) {
            Text("Simpan")
        }
    }
}

@Composable
fun FormSupplier(
    supplierEvent: SupplierEvent = SupplierEvent(),
    onValueChange: (SupplierEvent) -> Unit = {},
    errorState: SupplierFormErrorState = SupplierFormErrorState(),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.id_spl,
            onValueChange = {
               onValueChange(supplierEvent.copy(id_spl=it))
            },
            label = { Text("ID Supplier") },
            isError = errorState.id_spl != null,
            placeholder = { Text("Masukan ID Supplier") },
        )
        Text(
            text = errorState.id_spl ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.nama_spl,
            onValueChange = {
                onValueChange(supplierEvent.copy(nama_spl = it))
            },
            label = { Text("Nama Supplier") },
            isError = errorState.nama_spl != null,
            placeholder = { Text("Masukan Nama Supplier") },
        )
        Text(
            text = errorState.nama_spl ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.kontak,
            onValueChange = {
                onValueChange(supplierEvent.copy(kontak = it))
            },
            label = { Text("Kontak Supplier") },
            isError = errorState.kontak != null,
            placeholder = { Text("Masukan Nomor Kontak Supplier") },
        )
        Text(
            text = errorState.alamat ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.alamat,
            onValueChange = {
                onValueChange(supplierEvent.copy(alamat = it))
            },
            label = { Text("Alamat Supplier") },
            isError = errorState.alamat != null,
            placeholder = { Text("Masukan Alamat Supplier") },
        )
        Text(
            text = errorState.alamat ?: "",
            color = Color.Red
        )
    }
}