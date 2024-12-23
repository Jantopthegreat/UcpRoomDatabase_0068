package com.example.ucp2_pam.Data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2_pam.Data.Dao.BarangDao
import com.example.ucp2_pam.Data.Dao.SupplierDao
import com.example.ucp2_pam.Data.Entity.Barang
import com.example.ucp2_pam.Data.Entity.Supplier


@Database (entities = [Barang::class, Supplier::class], version = 1, exportSchema = false)

abstract class DatabaseBrgSpl : RoomDatabase() {

    abstract fun barangDao(): BarangDao
    abstract fun supplierDao(): SupplierDao

    companion object {
        @Volatile

        private var Instance: DatabaseBrgSpl? = null

        fun getDatabase(context: Context): DatabaseBrgSpl {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    DatabaseBrgSpl::class.java,
                    "DatabaseBrgSpl"
                )
                    .build().also { Instance = it }

            })
        }
    }
}
