package com.example.ucp2_pam.Repository

import com.example.ucp2_pam.Data.Dao.SupplierDao
import com.example.ucp2_pam.Data.Entity.Supplier
import kotlinx.coroutines.flow.Flow

class LocalRepositorySpl(private val supplierDao: SupplierDao) : RepositorySpl {

    override suspend fun insertSpl(supplier: Supplier) {
        supplierDao.insertSpl(supplier)
    }

    override fun getAllSpl(): Flow<List<Supplier>> {
        return supplierDao.getAllSpl()
    }

    override fun getSpl(id_sply: String): Flow<Supplier> {
        return supplierDao.getSpl(id_sply)
    }



}