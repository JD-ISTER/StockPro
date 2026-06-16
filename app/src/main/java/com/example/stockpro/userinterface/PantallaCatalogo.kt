package com.example.stockpro.userinterface

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun PantallaCatalogo(
    nombreOperario: String,
    viewModel: StockViewModel,
    onProductoClick: (Int) -> Unit,
    onReporteClick: () -> Unit
) {
    var mostrarCritico by remember { mutableStateOf(false) }

    val listaProductos = if (mostrarCritico) {
        viewModel.obtenerProductosEnRiesgo()
    } else {
        viewModel.productos
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Operario: $nombreOperario", fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { mostrarCritico = false }) {
                Text("Ver Todo")
            }
            Button(onClick = { mostrarCritico = true }) {
                Text("Stock Critico")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(listaProductos) { producto ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp).clickable {
                        onProductoClick(producto.id)
                    },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = producto.nombre, fontSize = 18.sp)
                        Text(text = "Precio: $" + producto.precio)
                        val colorStock = if (producto.stockActual < 5) Color.Red else Color.Black
                        Text(text = "Stock: " + producto.stockActual, color = colorStock)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onReporteClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver Reporte Financiero")
        }
    }
}