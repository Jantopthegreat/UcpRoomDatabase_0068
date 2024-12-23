package com.example.ucp2_pam.Data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2_pam.Data.Entity.Supplier
import kotlinx.coroutines.flow.Flow


@Dao
interface SupplierDao {

    @Insert
    suspend fun insertSpl (supplier: Supplier)

    @Query ("SELECT * FROM supplier")
    fun getAllSpl() : Flow<List<Supplier>>

    @Query ("SELECT * FROM supplier WHERE id_spl = :id_sply")
    fun getSpl (id_sply: String) : Flow<Supplier>

}