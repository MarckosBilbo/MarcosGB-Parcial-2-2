package com.example.marcosgb_parcial_2_2

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.*
import androidx.work.*
import com.example.marcosgb_parcial_2_2.modelo.VistaModeloEvento
import com.example.marcosgb_parcial_2_2.service.ConnectivityWorker
import com.example.marcosgb_parcial_2_2.ui.theme.MarcosGBParcial2_2Theme
import com.example.marcosgb_parcial_2_2.vista.*
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        val savedLanguage = sharedPreferences.getString("language", Locale.getDefault().language)
        configureLanguage(savedLanguage ?: Locale.getDefault().language)

        auth = FirebaseAuth.getInstance()

        setContent {
            MarcosGBParcial2_2Theme {
                val navController = rememberNavController()

                // Verifica si hay un usuario autenticado
                val startDestination = if (auth.currentUser != null) {
                    scheduleConnectivityWorker() // Sincronizar datos si hay sesión activa
                    "main"
                } else {
                    "login"
                }

                NavHost(navController, startDestination = startDestination) {
                    composable("login") { PantallaLogin(navController, auth) }
                    composable("registro") { PantallaRegistro(navController, auth) }
                    composable("main") {
                        scheduleConnectivityWorker() // Sincronizar al acceder a la pantalla principal
                        PantallaPrincipal(navController, auth)
                    }
                    composable("registro_evento") {
                        PantallaRegistroEvento(navController, VistaModeloEvento(application))
                    }
                }

                // Añadir el selector de idioma
                LanguageSelector { language ->
                    sharedPreferences.edit().putString("language", language).apply()
                    configureLanguage(language)
                    recreate() // Reiniciar la actividad para aplicar el nuevo idioma
                }
            }
        }
    }

    // Configurar el idioma de la aplicación
    private fun configureLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
    }

    // Configuración del ConnectivityWorker como tarea única
    private fun scheduleConnectivityWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED) // Solo ejecutar si hay conexión
            .build()

        val workRequest = OneTimeWorkRequestBuilder<ConnectivityWorker>()
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }

    // Configuración del ConnectivityWorker como tarea periódica (opcional)
    private fun schedulePeriodicConnectivityWorker() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val periodicWorkRequest = PeriodicWorkRequestBuilder<ConnectivityWorker>(15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "SyncWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
    }
}