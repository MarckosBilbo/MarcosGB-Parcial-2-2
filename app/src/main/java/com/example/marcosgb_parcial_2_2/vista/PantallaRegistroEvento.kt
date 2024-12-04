package com.example.marcosgb_parcial_2_2.vista

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.marcosgb_parcial_2_2.modelo.Evento
import com.example.marcosgb_parcial_2_2.modelo.VistaModeloEvento
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PantallaRegistroEvento(navController: NavController, vistaModelo: VistaModeloEvento = viewModel()) {
    // Variables para almacenar los datos del evento
    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var direccion by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var aforo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Campos de entrada
        TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = descripcion, onValueChange = { descripcion = it }, label = { Text("Descripción") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = direccion, onValueChange = { direccion = it }, label = { Text("Dirección") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = precio, onValueChange = { precio = it }, label = { Text("Precio") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = fecha, onValueChange = { fecha = it }, label = { Text("Fecha (YYYY-MM-DD)") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = aforo, onValueChange = { aforo = it }, label = { Text("Aforo") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                try {
                    // Validate and convert the values
                    val precioDouble: Double = precio.toDoubleOrNull() ?: throw IllegalArgumentException("Precio invalido")
                    val aforoInt: Int = aforo.toIntOrNull() ?: throw IllegalArgumentException("Capacidad invalida")

                    // Create the Evento object
                    val nuevoEvento = Evento(
                        nombre,
                        descripcion,
                        direccion,
                        precioDouble,
                        fecha,
                        aforoInt
                    )

                    // Add the event to the ViewModel
                    vistaModelo.agregarEvento(nuevoEvento)

                    // Log for debugging
                    Log.d("PantallaRegistroEvento", "Evento creado: $nuevoEvento")

                    // Navigate back to the main screen
                    navController.popBackStack()
                } catch (e: Exception) {
                    Log.e("PantallaRegistroEvento", "Error al crear el evento: ${e.message}")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón para volver a la pantalla principal
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver a la pantalla principal")
        }
    }
}
