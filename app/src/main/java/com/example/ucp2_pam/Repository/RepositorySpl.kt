package com.example.ucp2_pam.Repository


import com.example.ucp2_pam.Data.Entity.Supplier
import kotlinx.coroutines.flow.Flow

interface RepositorySpl {

    suspend fun insertSpl (supplier: Supplier)

    fun getAllSpl() : Flow<List<Supplier>>

    fun getSpl (id_spl: String): Flow<Supplier>
}