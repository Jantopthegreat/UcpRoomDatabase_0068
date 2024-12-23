package com.example.ucp2_pam

import android.app.Application
import com.example.ucp2_pam.DependeciesInjection.ContainerApp

class BrgSplApp: Application() {

    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()

        containerApp = ContainerApp(this)

    }

}