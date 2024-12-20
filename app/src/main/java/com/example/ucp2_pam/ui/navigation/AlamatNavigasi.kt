package com.example.ucp2_pam.ui.navigation

interface AlamatNavigasi {
    val route: String
}
object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}

object DestinasiHomeBrg : AlamatNavigasi {
    override val route = "homeBrg"
}

object DestinasiDetailBrg : AlamatNavigasi {
    override val route = "detailBrg"
    const val ID = "id_brg"
    val routesWithArg = "$route/{$ID}"
}

object DestinasiUpdateBrg : AlamatNavigasi {
    override val route = "updateBrg"
    const val ID = "id_brg"
    val routesWithArg = "$route/{$ID}"
}