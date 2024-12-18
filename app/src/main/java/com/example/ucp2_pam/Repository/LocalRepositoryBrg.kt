package com.example.ucp2_pam.Repository

import com.example.ucp2_pam.Data.Dao.BarangDao
import com.example.ucp2_pam.Data.Entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepositoryBrg (private val barangDao : BarangDao): RepositoryBrg


    {
        override suspend fun insertBrg(barang: Barang) {
            barangDao.insertBarang(barang)
        }

        override fun getAllBrg(): Flow<List<Barang>> {
            return barangDao.getAllBrg()
        }

        override fun getBrg(id: String): Flow<Barang> {
            return barangDao.getBrg(id)
        }

        override suspend fun deleteBrg(barang: Barang) {
            barangDao.deleteBrg(barang)
        }

        override suspend fun updateBrg(barang: Barang) {
            barangDao.updateBrg(barang)
        }

    }







