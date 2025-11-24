Proyecto de DAM

**Yo Apaño** es una aplicación para Android que tiene como objetivo el servir como red social para ayudar a los usuarios a organizar, publicar y registrarse en eventos. La aplicación presenta una pantalla de inicio de sesión
 y registro. Una vez que el usuario ha iniciado sesión, puede ver una lista de eventos existentes y agregar nuevos eventos a través de un formulario.

### Características Principales:

*   **Autenticación de Usuarios:** Sistema de registro e inicio de sesión.
*   **Gestión de Eventos:**
    *
   Visualización de una lista de eventos.
    *   Creación de nuevos eventos.

### Arquitectura y Tecnologías:

El proyecto sigue una arquitectura moderna de Android (MVVM) y utiliza las siguientes tecnologías:

*   **Lenguaje:** Kotlin
*   **Interfaz de Usuario:** Jetpack Compose para una
 interfaz de usuario declarativa y moderna.
*   **Arquitectura:**
    *   **ViewModel:** Para separar la lógica de la interfaz de usuario de la vista. Se utilizan `LoginViewModel` y `EventoViewModel`.
    *   **Repository:** Para abstraer las fuentes de datos (`UsuarioRepository`,
`EventoRepository`).
    *   **Inyección de Dependencias (manual):** La clase `YoApanoApplication` se encarga de instanciar y proveer las dependencias necesarias, como los repositorios.
*   **Persistencia de Datos:**
    *   **Room:** Para la base de datos local
 que almacena la información de los eventos.
*   **Navegación:** La navegación se gestiona de forma condicional dentro de la `MainActivity` en función del estado de autenticación del usuario.

En resumen, es una aplicación de eventos bien estructurada que utiliza componentes modernos de Android para ofrecer una experiencia de
 usuario fluida y un código base mantenible.
