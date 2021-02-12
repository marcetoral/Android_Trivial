<!--
*** Plantilla de readme hecha por othneildrew
-->


<!-- PROJECT SHIELDS -->
![platform-shield]
![release-shield]
[![Contributors][contributors-shield]][contributors-url] 
[![Issues][issues-shield]][issues-url]
[![Contact][discord-shield]][discord-url]




<!-- PROJECT LOGO -->
<br />
<p align="center">
  <h3 align="center">Proyecto Trivial</h3>

  <p align="center">
    Un simple quiz para Android
    <br />
    ·
    <a href="https://github.com/Pikass0/Android_Trivial/issues">Report Bug</a>
    ·
    <a href="https://github.com/Pikass0/Android_Trivial/issues">Request Feature</a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Contenido</summary>
  <ol>
    <li>
      <a href="#sobre-el-proyecto">Sobre el proyecto</a>
      <ul>
        <li><a href="#tecnologías">Tecnologías usadas</a></li>
      </ul>
    </li>
    <li>
      <a href="#probar-el-proyecto">Probar el proyecto</a>
      <ul>
        <li><a href="#requisitos">Requisitos</a></li>
        <li><a href="#instalación">Instalación</a></li>
      </ul>
    </li>
    <li><a href="#uso">Uso</a></li>
    <li><a href="#contribución">Contrubución</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## Sobre el proyecto

<!--(https://example.com) por si se quiere metir link-->

Quiz de Android con preguntas verdaderas y falsas.

¿Cómo jugar?
* Escribes tu nombre para que se guarde el récord con tu nombre en vez de "Guest"
* Responde las preguntas antes de que se te acabe el tiempo. Cuanto antes respondas, más puntos.
* Tras responder, falles o no, te aparecerá un popup con la explicación. Algo aprenderás.
* Si terminas de responder todas, tu récord se guardará en la base de datos
* Puedes consultar el top de récords, quitar la música y hacer mantenimiento de preguntas: crear nuevas, modificar existentes, eliminar.

![pantallas]


### Tecnologías

Herramientas usadas:
* Android Studio
* He puesto en práctica lo siguiente:
  -Ciclo de vida de Activities utilizando Servicios
  -Bases de datos SQLite
  -Animaciones
  -RecyclerView y Adapters
  -AlertDialog y Dialogs personalizados con layouts
  -Internacionalización (excepto las preguntas, ya que se pueden crear nuevas y estas no tendrían traducción)
  -Themes
  -Menú normal y contextual
  -Multimedia
  


<!-- GETTING STARTED -->
## Probar el proyecto

Cómo ejecutar el proyecto:

### Requisitos

Un dispositivo Android o emulador


### Instalación

1. Instalar el archivo **trivial.apk** (la app en sí) en tu dispositivo o emulador


<!-- USAGE EXAMPLES -->
## Uso

* El juego no tiene mucha complicación, así que explico el mantenimiento de preguntas
* Para ir a mantenimiento, ve a Ajustes > Mantenimiento.
* Si quieres modificar una pregunta, dale click a la que quieras modificar y abajo se te cargará el formulario con la pregunta seleccionada.
  Edita lo necesario y dale a **Modificar**
* Para crear una nueva pregunta, rellena el formulario y dale a **Crear**
* Para eliminar una pregunta, dale a la papelera correspondiente de la pregunta.
* Ir a girhub.com/Pikass0 y darme una estrellita por el logo tan profesional que me he marcado




<!-- CONTRIBUTING -->
## Contribución

Si quieres contribuir:

1. Fork el proyecto
2. Crea tu rama (`git checkout -b feature/AmazingFeature`)
3. Commit los cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un [Pull request](https://github.com/Pikass0/Android_Trivial/pulls)






<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[discord-shield]: https://img.shields.io/badge/chat-on%20discord-7289da.svg?style=flat&logo=discord
[discord-url]: https://www.discord.com/users/290575161869205504
[platform-shield]: https://img.shields.io/badge/platform-black?style=flat&logo=android
[release-shield]: https://img.shields.io/badge/release%20date-March%202018-lightgrey
[contributors-shield]: https://img.shields.io/github/contributors/Pikass0/Android_Trivial?color=green
[contributors-url]: https://github.com/Pikass0/Android_Trivial/graphs/contributors
[issues-shield]: https://img.shields.io/github/issues-raw/Pikass0/Android_Trivial?color=orange
[issues-url]: https://github.com/Pikass0/Android_Trivial/issues
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat&logo=linkedin&color=blue
[linkedin-url]: https://www.linkedin.com/in/marcelo-toral-martínez-573735176/
[menu-ss]: readme/menu.jpg
[pantallas]: readme/pantallas.png
[pregunta-ss]: readme/pregunta.jpg
