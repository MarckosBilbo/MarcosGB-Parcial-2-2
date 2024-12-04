# MarcosGB-Parcial-2-2


REPOSITORIO--> https://github.com/Parcial-Eventos-2/MarcosGB-Parcial-2-2

# Aplicación de Gestión de Eventos

Esta es una aplicación de gestión de eventos construida con Kotlin y Jetpack Compose. La aplicación permite a los usuarios registrarse, iniciar sesión y gestionar eventos. Utiliza Firebase para la autenticación y la base de datos en tiempo real.

## Características

- Registro e inicio de sesión de usuarios
- Creación, visualización y eliminación de eventos
- Selección de idioma (español e inglés)
- Integración con Firebase para autenticación y base de datos
- Worker de conectividad para sincronización de datos

## Pantallas

- **Pantalla de Inicio de Sesión**: Permite a los usuarios iniciar sesión con su correo electrónico y contraseña.
- **Pantalla de Registro**: Permite a los nuevos usuarios registrarse.
- **Pantalla Principal**: Muestra una lista de eventos y permite a los usuarios cerrar sesión.
- **Pantalla de Registro de Eventos**: Permite a los usuarios crear nuevos eventos.

## Instalación

1. Clona el repositorio:
    ```sh
    git clone https://github.com/MarckosBilbo/event-management-app.git
    ```
2. Abre el proyecto en Android Studio.
3. Sincroniza el proyecto con los archivos de Gradle.
4. Configura Firebase para tu proyecto:
    - Añade tu archivo `google-services.json` en el directorio `app`.
    - Habilita la autenticación por correo electrónico/contraseña en Firebase Authentication.
    - Configura una base de datos en tiempo real de Firebase.

## Uso

1. Ejecuta la aplicación en un emulador o dispositivo físico.
2. Registra un nuevo usuario o inicia sesión con una cuenta existente.
3. Crea, visualiza y gestiona eventos.

## Estructura del Código

- `MainActivity.kt`: El punto de entrada principal de la aplicación.
- `PantallaLogin.kt`: Composable para la pantalla de inicio de sesión.
- `PantallaRegistro.kt`: Composable para la pantalla de registro.
- `PantallaPrincipal.kt`: Composable para la pantalla principal que muestra los eventos.
- `PantallaRegistroEvento.kt`: Composable para la pantalla de registro de eventos.
- `LanguageSelector.kt`: Composable para seleccionar el idioma de la aplicación.
- `Evento.java`: Modelo de datos para los eventos.
- `RepositorioEvento.java`: Repositorio para gestionar los datos de los eventos.
- `VistaModeloEvento.java`: ViewModel para los datos de los eventos.
- `ConnectivityWorker.java`: Worker para sincronizar los datos.

## Dependencias

- Jetpack Compose
- Firebase Authentication
- Firebase Realtime Database
- Android WorkManager

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más detalles.
