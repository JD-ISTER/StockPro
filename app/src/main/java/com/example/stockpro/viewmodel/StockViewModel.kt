package com.example.stockpro.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.stockpro.model.Producto

class StockViewModel : ViewModel() {
    val productos = mutableStateListOf<Producto>(
        Producto(1, "Laptop", "Computadora portatil", 800.0, 10),
        Producto(2, "Mouse", "Mouse inalambrico", 25.0, 3),
        Producto(3, "Teclado", "Teclado mecanico", 50.0, 0),
        Producto(4, "Monitor", "Monitor 24 pulgadas", 150.0, 8),
        Producto(5, "Cable HDMI", "Cable de video", 10.0, 2),
        Producto(6, "Webcam", "Camara web HD", 40.0, 15)
    )

    fun obtenerProducto(id: Int): Producto? {
        return productos.find { it.id == id }
    }

    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        val index = productos.indexOfFirst { it.id == id }
        if (index != -1) {
            val productoActual = productos[index]
            productos[index] = productoActual.copy(stockActual = nuevaCantidad)
        }
    }

    fun calcularValorTotalInventario(): Double {
        return productos.sumOf { it.precio * it.stockActual }
    }

    fun obtenerProductosEnRiesgo(): List<Producto> {
        return productos.filter { it.stockActual < 5 }
    }

    fun obtenerProductosConStockCero(): Int {
        return productos.count { it.stockActual == 0 }
    }
}