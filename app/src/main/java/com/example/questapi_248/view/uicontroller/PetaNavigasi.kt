package com.example.questapi_248.uicontroller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.questapi_248.view.EntrySiswaScreen
import com.example.questapi_248.view.HomeScreen
import com.example.questapi_248.view.DetailSiswaScreen
import com.example.questapi_248.view.EditSiswaScreen
import com.example.questapi_248.view.route.DestinasiDetail
import com.example.questapi_248.view.route.DestinasiEdit
import com.example.questapi_248.view.route.DestinasiEntry
import com.example.questapi_248.view.route.DestinasiHome

@Composable
fun DataSiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    HostNavigasi(navController = navController, modifier = modifier)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {

        // =====================
        // HOME
        // =====================
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntry.route)
                },
                navigateToItemUpdate = { id ->
                    navController.navigate("${DestinasiDetail.route}/$id")
                }
            )
        }

        // =====================
        // ENTRY
        // =====================
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // =====================
        // DETAIL
        // =====================
        composable(
            route = DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetail.itemIdArg) {
                    type = NavType.IntType
                }
            )
        ) {
            DetailSiswaScreen(
                navigateBack = {
                    navController.popBackStack()
                },
                navigateToEditItem = { id ->
                    navController.navigate("${DestinasiEdit.route}/$id")
                }
            )
        }

        // =====================
        // EDIT  (INI YANG DIPERBAIKI)
        // =====================
        composable(
            route = DestinasiEdit.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiEdit.itemIdArg) {
                    type = NavType.IntType
                }
            )
        ) {
            EditSiswaScreen(
                navigateBack = {
                    navController.navigate(DestinasiHome.route) {
                        popUpTo(DestinasiHome.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigateUp = {
                    navController.popBackStack()
                }
            )
        }
    }
}
