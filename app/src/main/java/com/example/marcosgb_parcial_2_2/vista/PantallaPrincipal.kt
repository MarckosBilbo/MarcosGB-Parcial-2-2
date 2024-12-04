package com.example.marcosgb_parcial_2_2.vista

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.marcosgb_parcial_2_2.modelo.Evento
import com.example.marcosgb_parcial_2_2.modelo.VistaModeloEvento
import com.google.firebase.auth.FirebaseAuth


@Composable
fun PantallaPrincipal(navController: NavController, auth: FirebaseAuth, vistaModelo: VistaModeloEvento = viewModel()) {
    val eventos by vistaModelo.obtenerEventos().observeAsState(emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("registro_evento") }) {
                Text("+")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(eventos) { evento ->
                    EventoItem(evento)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    auth.signOut() // Cierra sesión en Firebase
                    navController.navigate("login") {
                        popUpTo("main") { inclusive = true } // Borra el stack de navegación
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Cerrar Sesión")
            }
        }
    }
}

@Composable
fun EventoItem(evento: Evento) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Nombre: ${evento.nombre}", style = MaterialTheme.typography.titleLarge)
            Text("Descripción: ${evento.descripcion}", style = MaterialTheme.typography.bodyMedium)
            Text("Precio: ${evento.precio} €", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
