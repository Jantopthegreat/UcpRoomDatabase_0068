package com.example.ucp2_pam.Data.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2_pam.Data.Entity.Barang
import kotlinx.coroutines.flow.Flow


@Dao
interface BarangDao {

    @Insert
    suspend fun insertBarang(barang: Barang)

    @Query("SELECT * FROM barang")
    fun getAllBrg(): Flow<List<Barang>>

    @Query("SELECT * FROM barang WHERE id = :id ")
    fun getBrg(id: String): Flow<Barang>

    @Delete
    suspend fun deleteBrg(barang: Barang)

    @Update
    suspend fun  updateBrg(barang: Barang)

}