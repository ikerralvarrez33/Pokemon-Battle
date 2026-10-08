⚡ Pokémon Battle Manager

Aplicación desarrollada en Java para la gestión de entrenadores, Pokémon y combates. El proyecto simula un sistema de batallas Pokémon en el que los jugadores pueden formar sus equipos, gestionar movimientos y tipos, y enfrentarse en combates por turnos.

🎮 Características
👤 Creación y gestión de entrenadores.
🐾 Gestión de Pokémon.
⚡ Asignación y gestión de tipos.
💥 Gestión de movimientos y ataques.
🎒 Creación y gestión de equipos Pokémon.
⚔️ Sistema de combate por turnos.
❤️ Gestión de vida, daño y defensa.
🎯 Selección de movimientos durante el combate.
🧪 Pruebas unitarias mediante JUnit.

🛠️ Tecnologías utilizadas
Java
Eclipse IDE
JUnit

⚔️ Sistema de combate

El proyecto incluye un sistema de combate en el que dos entrenadores pueden enfrentarse utilizando los Pokémon de sus respectivos equipos.

Durante el combate, el jugador puede seleccionar diferentes acciones, como utilizar movimientos de ataque. Cada acción tiene consecuencias sobre las estadísticas del Pokémon rival, como sus puntos de vida y defensa.

El combate continúa por turnos hasta que uno de los entrenadores se queda sin Pokémon disponibles para luchar.

🧩 Estructura del proyecto

El proyecto está organizado para separar las diferentes responsabilidades de la aplicación, facilitando su mantenimiento y ampliación.

src/
├── main/
│   └── java/
│       └── ...
│
└── test/
    └── java/
        └── ...


La carpeta de pruebas contiene los diferentes tests unitarios realizados con JUnit, utilizados para comprobar el funcionamiento de las principales clases y funcionalidades.

🧪 Pruebas

Para garantizar el correcto funcionamiento de la aplicación se han desarrollado pruebas unitarias utilizando JUnit.

Estas pruebas permiten comprobar aspectos como:

Creación de entrenadores.
Creación y gestión de Pokémon.
Funcionamiento de los movimientos.
Cálculo del daño.
Gestión de la vida y defensa.
Funcionamiento de los combates.
Gestión de los equipos Pokémon.


📚 Objetivo del proyecto

El objetivo principal es desarrollar una aplicación orientada a objetos que permita poner en práctica conceptos de programación en Java, como clases, objetos, encapsulamiento, herencia, relaciones entre clases, gestión de colecciones y pruebas unitarias.

Además, el proyecto busca representar de forma sencilla la lógica básica de un sistema de combates Pokémon.
