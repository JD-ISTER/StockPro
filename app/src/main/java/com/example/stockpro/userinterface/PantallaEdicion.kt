package com.example.stockpro.userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockpro.viewmodel.StockViewModel

@Composable
fun PantallaEdicion(
    productoId: Int,
    viewModel: StockViewModel,
    onVolver: () -> Unit
) {
    val producto = viewModel.obtenerProducto(productoId) ?: return

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = producto.nombre, fontSize = 28.sp)
        Text(text = producto.descripcion, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Stock Actual: " + producto.stockActual,
            fontSize = 32.sp
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                viewModel.actualizarStock(producto.id, producto.stockActual + 1)
            }) {
                Text("+1")
            }
            Button(
                onClick = {
                    viewModel.actualizarStock(producto.id, producto.stockActual - 1)
                },
                enabled = producto.stockActual > 0
            ) {
                Text("-1")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar y Volver")
        }
    }
}