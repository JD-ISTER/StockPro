package com.example.stockpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stockpro.userinterface.PantallaCatalogo
import com.example.stockpro.userinterface.PantallaEdicion
import com.example.stockpro.userinterface.PantallaIngreso
import com.example.stockpro.userinterface.PantallaReporte
import com.example.stockpro.viewmodel.StockViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation(viewModel: StockViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "ingreso") {
        composable("ingreso") {
            PantallaIngreso { nombre ->
                navController.navigate("catalogo/$nombre")
            }
        }

        composable(
            route = "catalogo/{nombreOperario}",
            arguments = listOf(navArgument("nombreOperario") { type = NavType.StringType })
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombreOperario") ?: ""
            PantallaCatalogo(
                nombreOperario = nombre,
                viewModel = viewModel,
                onProductoClick = { id ->
                    navController.navigate("edicion/$id")
                },
                onReporteClick = {
                    navController.navigate("reporte")
                }
            )
        }

        composable(
            route = "edicion/{productoId}",
            arguments = listOf(navArgument("productoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("productoId") ?: 0
            PantallaEdicion(
                productoId = id,
                viewModel = viewModel,
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable("reporte") {
            PantallaReporte(
                viewModel = viewModel,
                onVolver = {
                    navController.popBackStack()
                }
            )
        }
    }
}