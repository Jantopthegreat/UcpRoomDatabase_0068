package com.example.ucp2_pam.ui.view.Barang

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_pam.ui.customwidget.TopAppBar
import com.example.ucp2_pam.ui.navigation.AlamatNavigasi
import com.example.ucp2_pam.ui.viewmodel.Barang.BarangEvent
import com.example.ucp2_pam.ui.viewmodel.Barang.BarangFormErrorState
import com.example.ucp2_pam.ui.viewmodel.Barang.BarangUiState
import com.example.ucp2_pam.ui.viewmodel.Barang.BarangViewModel
import com.example.ucp2_pam.ui.viewmodel.PenyediaViewModel
import com.example.ucp2_pam.ui.viewmodel.Supplier.HomeSplViewModel
import kotlinx.coroutines.launch

object  DestinasiInsert : AlamatNavigasi{
    override val route: String = "insert_mhs"
}

@Composable
fun InsertBarangView (
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    onLogoClick: () -> Unit = { },
    viewModel: BarangViewModel = viewModel(factory = PenyediaViewModel.Factory)
){

    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackBarMessage){
        uiState.snackBarMessage?.let{ message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }
    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)}
    ){ padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ){
            TopAppBar(
                judul = "Daftar Barang",
                showBackButton = true,
                onBack = onBack,
                onLogoClick = onLogoClick,
                modifier = modifier
            )
            InsertBodyBarang (
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    viewModel.saveData()
                    onNavigate()
                }
            )
        }
    }
}

@Composable
fun InsertBodyBarang(
    modifier: Modifier = Modifier,
    onValueChange: (BarangEvent) -> Unit,
    uiState: BarangUiState,
    onClick: () -> Unit
) {
    Column(
        modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormBarang(
            barangEvent = uiState.barangEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(10.dp))
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
fun FormBarang(
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = {},
    errorState: BarangFormErrorState = BarangFormErrorState(),
    HomeSplViewModel: HomeSplViewModel = viewModel(factory = PenyediaViewModel.Factory),
    modifier: Modifier = Modifier
) {
    val suplierui by HomeSplViewModel.splUiState.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.id_brg,
            onValueChange = {
                onValueChange(barangEvent.copy(id_brg = it))
            },
            label = { Text("ID Barang") },
            isError = errorState.id_brg != null,
            placeholder = { Text("Masukan ID Barang") },
        )
        Text(
            text = errorState.id_brg ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.nama_brg,
            onValueChange = {
                onValueChange(barangEvent.copy(nama_brg = it))
            },
            label = { Text("Nama Barang") },
            isError = errorState.nama_brg != null,
            placeholder = { Text("Masukan Nama Barang") },
        )
        Text(
            text = errorState.nama_brg ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.deskripsi,
            onValueChange = {
                onValueChange(barangEvent.copy(deskripsi = it))
            },
            label = { Text("Deskripsi Barang") },
            isError = errorState.deskripsi != null,
            placeholder = { Text("Masukan Deskripsi Barang") },
        )

        Text(
            text = errorState.deskripsi ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.harga,
            onValueChange = {
                onValueChange(barangEvent.copy(harga = it))
            },
            label = { Text("Harga Barang") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = errorState.harga != null,
            placeholder = { Text("Masukan Harga Barang") },
        )
        Text(
            text = errorState.harga ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.stok.toString(),
            onValueChange = {
                val stokValue = it.toIntOrNull() ?: 0
                onValueChange(barangEvent.copy(stok = stokValue))
            },
            label = { Text("Stok") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = errorState.stok != null,
            placeholder = { Text("Masukan Stok Barang") },
        )
        Text(
            text = errorState.stok ?: "",
            color = Color.Red
        )

        DropDownSupllier(
            tittle = "Pilih Supplier",
            options = suplierui.listSpl.map { it.nama_spl },
            selectedOption = barangEvent.nama_Suplier,
            onOptionSelected = {
                selectedSpl -> onValueChange(barangEvent.copy(nama_Suplier = selectedSpl))
            },
            isError = errorState.nama_Suplier != null,
            errorMessage = errorState.nama_Suplier
        )
    }
}

@Composable
fun DropDownSupllier(
    tittle: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    isError: Boolean = false,
    errorMessage: String? = null
){
    var expanded by remember { mutableStateOf(false) }
    var currentSelected by remember { mutableStateOf(selectedOption) }

    Column {
        OutlinedTextField(
            value = currentSelected,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = tittle) },
            trailingIcon = {
                androidx.compose.material3.IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ArrowDropDown else Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ){
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        onOptionSelected(option)
                        currentSelected = option
                        expanded = false
                    }
                )
            }
        }
        if (isError && errorMessage != null) {
            Text(
                text = errorMessage,
                color = Color.Red
            )

        }
    }
}
