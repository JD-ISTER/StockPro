package com.example.stockpro.userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun PantallaReporte(
    viewModel: StockViewModel,
    onVolver: () -> Unit
) {
    val valorTotal = viewModel.calcularValorTotalInventario()
    val productosCero = viewModel.obtenerProductosConStockCero()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Reporte Financiero", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Capital Invertido Total", fontSize = 20.sp)
        Text(
            text = String.format(java.util.Locale.US, "$ %.2f", valorTotal),
            fontSize = 36.sp,
            color = Color.Green
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Total de productos con stock en cero: $productosCero",
            fontSize = 18.sp,
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onVolver) {
            Text("Volver al Catalogo")
        }
    }
}