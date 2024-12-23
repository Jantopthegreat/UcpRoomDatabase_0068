package com.example.ucp2_pam.ui.navigation

interface AlamatNavigasi {
    val route: String
}

object DestinasiMulai : AlamatNavigasi {
    override val route = "mulai"
}

object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}

object DestinasiHomeBrg : AlamatNavigasi {
    override val route = "homeBrg"
}

object DestinasiInsertBrg : AlamatNavigasi {
    override val route = "insertBrg"
}

object DestinasiDetailBrg : AlamatNavigasi {
    override val route = "detailBrg"
    const val IDBrg = "id_brg"
    val routesWithArg = "$route/{$IDBrg}"
}

object DestinasiUpdateBrg : AlamatNavigasi {
    override val route = "updateBrg"
    const val IDBrg = "id_brg"
    val routesWithArg = "$route/{$IDBrg}"
}

object DestinasiHomeSpl : AlamatNavigasi {
    override val route = "homeSpl"
}

object DestinasiInsertSpl : AlamatNavigasi {
    override val route = "insertSpl"
}
