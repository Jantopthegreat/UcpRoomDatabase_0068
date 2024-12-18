package com.example.ucp2_pam.DependeciesInjection

import android.content.Context
import com.example.ucp2_pam.Repository.RepositoryBrg
import com.example.ucp2_pam.Repository.RepositorySpl


interface InterfaceContainerApp {
val repositoryBrg : RepositoryBrg
val repositorySpl : RepositorySpl
}


class ContainerApp(private val context: Context) : InterfaceContainerApp{
    override val repositorySpl: RepositorySpl by lazy {

    }

}