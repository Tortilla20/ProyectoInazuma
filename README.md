<h1 align="center">INACODEX</h1>
<p align="center">Christian Castro Iglesias                 Adán Otero López</p>
<p align="center">Adán Otero López</p>
<p align="center">Iván Duro Fernández</p>

## Índice
(INTRODUCIR ÍNDICE AL TERMINAR README)

## Introducción
Este es un programa / app que gestiona con consultas los algunos de los personajes
de la serie Inazuma Eleven mediante una base de datos en MySQL.

El usuario al iniciar la aplicación tendrá tres opciones, registrarse, en la que
tendrá que indicar un nombre y una contraseña para poder acceder, iniciar sesión,
en la que tendrá que indicar sus credenciales para acceder y invitado que podrá acceder
sin ninguna credencial. 

Una vez dentro, podrá crear, modificar y eliminar personajes además
de consultar la información proporcionada por el programa en la base de datos 

---

## 1. Manual técnico para desarrolladores

### Requisitos
- **Java SE 17 o superior**: El proyecto se desarrollo usando Java 17, por lo cuál, se necesita
esta versión o superiores para poder iniciar el programa [(descargar)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- **Ant**: También, este proyecto se realizó en Java con Ant, por lo que se deberá tener Ant instalado
[(descargar)](https://ant.apache.org/)
- **MySQL**: Para la base de datos, se utilizó MySQL como sistema de gestión de bases de datos, si no se tiene,
habría que instalarlo [(descargar)](https://dev.mysql.com/downloads/mysql/)
- **IDE Utilizado**: Por último, el proyecto de desarrollo en el IDE Netbeans, si no se tiene instalado, no hace falta
instalarlo porque se puede utilizar cualquier otro IDE compatible con Java, pero se recomiendo utilizar el
anteriormente mencionado [(descargar)](https://netbeans.apache.org/front/main/index.html)

### Modelo Entidad-Relación
<p align="center">
  <img src="mediaReadme/Diagrama.png" alt="Alt">
</p>

Los requisitos del programa según el modelo entidad-relación son los siguientes:

Un **Usuario** (lista) se define por un ID, un nombre y una contraseña. Cada Lista contiene uno o varios personajes, y cada **Personaje** pertenece a una única Lista (relación 1:N Contiene).

Un **Personaje** se define por un ID, nombre, alias, descripción, posición, imagen y género.
Los **Personajes** se relacionan con Equipos de varias formas:
- Un **personaje** juega en uno o varios equipos y un equipo puede tener uno o varios personajes (relación N:M Juega).
- Un **personaje** puede capitanear uno o varios equipos, y cada equipo tiene un único capitán (relación 1:N Capitanea).
- Un **personaje** puede entrenar uno o varios equipos, y cada equipo tiene un único entrenador (relación 1:N Entrena).

Un **Equipo** se define por un ID, nombre, región y escudo.

Los **Personajes** pueden tener Supertécnicas:
- Un **personaje** puede tener una o varias supertécnicas, y una supertécnica puede pertenecer a uno o varios personajes (relación N:M Tiene).

Una **Supertécnica** se define por ID, nombre, potencia, coordenada y tipo.
Tanto los **Personajes** como las **Supertécnicas** se relacionan con Atributos:
- Un **personaje** pertenece a un único atributo, y un atributo puede estar asociado a varios personajes (relación N:1 Pertenece).
- Una **supertécnica** conlleva un único atributo, y un atributo puede estar asociado a varias supertécnicas (relación N:1 Conlleva).
- Un **Atributo** se define por un ID, nombre e imagen.

### Tablas

### ENTIDADES
Usuario: `3`
| NOMBRE | TIPO DE DATO | PERMITE NULL | CLAVE PRIMARIA | CLAVE FORANEA |
| --- | --- | --- | --- | --- |
| `ID` | INT | NO | SÍ | - |
| `NOMBRE` | VARCHAR(30) | NO | - | - |
| `CONTRASENHA` | VARCHAR(60) | SÍ | - | - |

ATRIBUTO: `3`
| NOMBRE | TIPO DE DATO | PERMITE NULL | CLAVE PRIMARIA | CLAVE FORANEA |
| --- | --- | --- | --- | --- |
| `ID` | INT | NO | SÍ | - |
| `NOMBRE` | VARCHAR(35) | NO | - | - |
| `IMAGEN_TIPO_ATRIBUTO` | VARCHAR(200) | SÍ | - | - |

Personaje: `9`
| NOMBRE | TIPO DE DATO | PERMITE NULL | CLAVE PRIMARIA | CLAVE FORANEA |
| --- | --- | --- | --- | --- |
| `ID` | INT | NO | SÍ | - |
| `NOMBRE` | VARCHAR(35) | NO | - | - |
| `ALIAS` | VARCHAR(25) | SÍ | - | - |
| `DESCRIPCION` | VARCHAR(100) | SÍ | - | - |
| `POSICION` | VARCHAR(5) | SÍ | - | - |
| `GENERO` | VARCHAR(20) | SÍ | - | - |
| `IMAGEN` | VARCHAR(200) | SÍ | - | - |
| `ID_ATRIBUTO` | INT | SÍ | - | SÍ |
| `ID_USUARIO` | INT | SÍ | - | SÍ |

EQUIPO: `6`
| NOMBRE | TIPO DE DATO | PERMITE NULL | CLAVE PRIMARIA | CLAVE FORANEA |
| --- | --- | --- | --- | --- |
| `ID` | INT | NO | SÍ | - |
| `NOMBRE` | VARCHAR(35) | NO | - | - |
| `REGION` | VARCHAR(25) | NO | - | - |
| `ESCUDO` | VARCHAR(200) | SÍ | - | - |
| `ID_CAPITAN` | INT | SÍ | - | SÍ |
| `ID_ENTRENADOR` | INT | SÍ | - | SÍ |

SUPERTECNICA: `6`
| NOMBRE | TIPO DE DATO | PERMITE NULL | CLAVE PRIMARIA | CLAVE FORANEA |
| --- | --- | --- | --- | --- |
| `ID` | INT | NO | SÍ | - |
| `NOMBRE` | VARCHAR(35) | NO | - | - |
| `COORDINADA` | BOOLEAN | NO | - | - |
| `POTENCIA` | VARCHAR(25) | NO | - | - |
| `TIPO` | VARCHAR(35) | NO | - | - |
| `ID_ATRIBUTO` | INT | SÍ | - | SÍ |

### RELACIONES N:M

JUEGA: `2`
| NOMBRE | TIPO DE DATO | PERMITE NULL | CLAVE PRIMARIA | CLAVE FORANEA |
| --- | --- | --- | --- | --- |
| `ID_PERSONAJE` | INT | NO | SÍ | PERSONAJE(ID) |
| `ID_EQUIPO` | INT | NO | SÍ | EQUIPO(ID) |

TIENE: `2`
| NOMBRE | TIPO DE DATO | PERMITE NULL | CLAVE PRIMARIA | CLAVE FORANEA |
| --- | --- | --- | --- | --- |
| `ID_PERSONAJE` | INT | NO | SÍ | PERSONAJE(ID) |
| `ID_SUPERTECNICA` | INT | NO | SÍ | SUPERTECNICA(ID) |
