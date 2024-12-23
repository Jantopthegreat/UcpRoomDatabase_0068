package com.example.ucp2_pam.ui.navigation

import HomeView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ucp2_pam.ui.view.Barang.DetailBarangView
import com.example.ucp2_pam.ui.view.Barang.HomeBarangView
import com.example.ucp2_pam.ui.view.Barang.InsertBarangView
import com.example.ucp2_pam.ui.view.Barang.UpdateBarangView
import com.example.ucp2_pam.ui.view.SplashView
import com.example.ucp2_pam.ui.view.Supplier.HomeSupplierView
import com.example.ucp2_pam.ui.view.Supplier.InsertSupplierView


@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiMulai.route
    ) {
        composable(
            route = DestinasiMulai.route
        ){
            SplashView(
                onMulaiClick = {
                    navController.navigate(DestinasiHome.route)
                }
            )
        }

        composable(
            route = DestinasiHome.route
        ) {
            HomeView(

                onLihatBarangClick = {
                    navController.navigate(DestinasiHomeBrg.route)
                },
                onLihatSupplierClick = {
                    navController.navigate(DestinasiHomeSpl.route)
                },
                onTambahBarangClick = {
                    navController.navigate(DestinasiInsertBrg.route)
                },
                onTambahSupplierClick = {
                    navController.navigate(DestinasiInsertSpl.route)
                },
                onLogoClick = {
                    navController.navigate(DestinasiMulai.route)
                },
                modifier = modifier
            )
        }

        composable(
            route = DestinasiHomeSpl.route
        ){
            HomeSupplierView(
                onBack = {
                    navController.popBackStack()
                },
                onLogoClick = {
                    navController.navigate(DestinasiMulai.route)
                }
            )
        }

        composable(
            route = DestinasiInsertSpl.route
        ){
            InsertSupplierView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                onLogoClick = {
                    navController.navigate(DestinasiMulai.route)
                }
            )
        }

        composable(
            DestinasiHomeBrg.route
        ){
            HomeBarangView(
                onBack = {
                    navController.popBackStack()
                },
                onDetailClick = { idBrg ->
                    navController.navigate("${DestinasiDetailBrg.route}/$idBrg")
                },
                onLogoClick = {
                    navController.navigate(DestinasiMulai.route)
                }
            )
        }

        composable(
            DestinasiDetailBrg.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailBrg.IDBrg) {
                    type = NavType.StringType
                }
            )
        ) {
            val idBrg = it.arguments?.getString(DestinasiDetailBrg.IDBrg)

            idBrg?.let { idBrg ->
                DetailBarangView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateBrg.route}/$idBrg")
                    },
                    onDeleteClick = {
                        navController.popBackStack()
                    },
                    onLogoClick = {
                        navController.navigate(DestinasiMulai.route)
                    }
                )
            }
        }

        composable(
            DestinasiInsertBrg.route
        ){
            InsertBarangView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                onLogoClick = {
                    navController.navigate(DestinasiMulai.route)
                }
            )
        }

        composable(
            DestinasiUpdateBrg.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateBrg.IDBrg) {
                    type = NavType.StringType
                }
            )
        ){
            UpdateBarangView(
                onBack = {
                    navController.popBackStack()
                },
                onNavigate = {
                    navController.popBackStack()
                },
                onLogoClick = {
                    navController.navigate(DestinasiMulai.route)
                }
            )
        }
    }
}