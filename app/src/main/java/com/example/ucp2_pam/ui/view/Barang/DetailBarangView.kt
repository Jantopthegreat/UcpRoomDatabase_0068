package com.example.ucp2_pam.ui.view.Barang

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2_pam.Data.Entity.Barang
import com.example.ucp2_pam.ui.customwidget.TopAppBar
import com.example.ucp2_pam.ui.viewmodel.Barang.DetailBrgUiState
import com.example.ucp2_pam.ui.viewmodel.Barang.DetailBrgViewModel
import com.example.ucp2_pam.ui.viewmodel.Barang.toBarangEntity
import com.example.ucp2_pam.ui.viewmodel.PenyediaViewModel



@Composable
fun ItemDetailBrg (
    modifier: Modifier = Modifier,
    barang: Barang,
){
    Card (
        modifier = modifier
            .fillMaxWidth (),
        colors = CardDefaults.cardColors (
            containerColor = Color(0xFFF9E7FD),
        )
    ){
        Column (
            modifier = Modifier.padding(16.dp)
        ){
            ComponentDetailMhs (judul = "ID Barang", isinya = barang. id_brg)
            Spacer (modifier = Modifier.padding(4.dp))

            ComponentDetailMhs (judul = "Nama Barang", isinya = barang. nama_brg)
            Spacer (modifier = Modifier.padding(4.dp))
            ComponentDetailMhs (judul = "Deskripsi Barang", isinya = barang.deskripsi)
            Spacer (modifier = Modifier.padding(4.dp))
            ComponentDetailMhs (judul = "Harga Barang", isinya = barang.harga)
            Spacer (modifier = Modifier.padding(4.dp))
            ComponentDetailMhs (judul = "Stok Barang", isinya = barang.stok.toString())
            Spacer (modifier = Modifier.padding(4.dp))
            ComponentDetailMhs (judul = "Nama Supplier", isinya = barang.nama_Suplier)
        }
    }
}

@Composable
fun ComponentDetailMhs (
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray

        )
        Text(
            text = isinya, fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun DeleteConfirmationDialog (
    onDeleteConfirm: () -> Unit, onDeleteCancel: () -> Unit, modifier: Modifier =
        Modifier
) {
    AlertDialog(onDismissRequest = { },
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data barang?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}