package com.example.ucp2_pam

import android.app.Application
import com.example.ucp2_pam.DependeciesInjection.ContainerApp

class brgSplApp: Application() {

    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        //Membuat interface container app

        containerApp = ContainerApp(this)
        //Instance adalah object yang dibuat dari class
    }

}