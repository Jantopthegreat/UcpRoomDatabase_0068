package com.example.ucp2_pam.DependeciesInjection

import android.content.Context
import com.example.ucp2_pam.Data.Database.DatabaseBrgSpl
import com.example.ucp2_pam.Repository.LocalRepositoryBrg
import com.example.ucp2_pam.Repository.LocalRepositorySpl
import com.example.ucp2_pam.Repository.RepositoryBrg
import com.example.ucp2_pam.Repository.RepositorySpl


interface InterfaceContainerApp {
val repositoryBrg : RepositoryBrg
val repositorySpl : RepositorySpl
}


class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val repositorySpl: RepositorySpl by lazy {
        LocalRepositorySpl(DatabaseBrgSpl.getDatabase(context).supplierDao())
    }

        override val repositoryBrg: RepositoryBrg by lazy {
            LocalRepositoryBrg(DatabaseBrgSpl.getDatabase(context).barangDao())

    }

}