package com.example.ucp2_pam.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp2_pam.BrgSplApp
import com.example.ucp2_pam.ui.viewmodel.Barang.BarangViewModel
import com.example.ucp2_pam.ui.viewmodel.Barang.DetailBrgViewModel
import com.example.ucp2_pam.ui.viewmodel.Barang.HomeBrgViewModel
import com.example.ucp2_pam.ui.viewmodel.Barang.UpdateBrgViewModel
import com.example.ucp2_pam.ui.viewmodel.Supplier.HomeSplViewModel
import com.example.ucp2_pam.ui.viewmodel.Supplier.SupplierViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            BarangViewModel(
                brgSplApp().containerApp.repositoryBrg
            )
        }
        initializer {
            HomeBrgViewModel(
                brgSplApp().containerApp.repositoryBrg
            )
        }
        initializer {
            DetailBrgViewModel(
                createSavedStateHandle(),
                brgSplApp().containerApp.repositoryBrg
            )
        }
        initializer {
            UpdateBrgViewModel(
                createSavedStateHandle(),
                brgSplApp().containerApp.repositoryBrg
            )
        }
        initializer {
            SupplierViewModel(
                brgSplApp().containerApp.repositorySpl
            )
        }
        initializer {
            HomeSplViewModel(
                brgSplApp().containerApp.repositorySpl
            )
        }
    }
}

fun CreationExtras.brgSplApp(): BrgSplApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BrgSplApp)