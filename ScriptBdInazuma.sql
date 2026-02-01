SET FOREIGN_KEY_CHECKS=0;
DROP DATABASE IF EXISTS InazumaEleven;
CREATE DATABASE InazumaEleven;
USE InazumaEleven;

CREATE TABLE USUARIO (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	NOMBRE VARCHAR(30) NOT NULL,
	CONTRASENHA VARCHAR(60)
);

CREATE TABLE ATRIBUTO (
	ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	NOMBRE VARCHAR(35) NOT NULL,
	IMAGEN_TIPO_ATRIBUTO VARCHAR(200)
);

CREATE TABLE PERSONAJE(
	ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	NOMBRE VARCHAR(35) NOT NULL,
	ALIAS VARCHAR(25),
	DESCRIPCION VARCHAR(100),
	POSICION VARCHAR(5),
	GENERO VARCHAR(20),
	IMAGEN VARCHAR(200),
	ID_ATRIBUTO INT,
	ID_USUARIO INT,
	FOREIGN KEY (ID_ATRIBUTO) REFERENCES ATRIBUTO(ID) ON DELETE SET NULL ON UPDATE CASCADE,
	FOREIGN KEY (ID_USUARIO) REFERENCES USUARIO(ID) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE EQUIPO (
	ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	NOMBRE VARCHAR(35) NOT NULL,
	REGION VARCHAR(25) NOT NULL,
	ESCUDO VARCHAR(200),
	ID_CAPITAN INT,
	ID_ENTRENADOR INT,
	FOREIGN KEY (ID_CAPITAN) REFERENCES PERSONAJE(ID) ON DELETE SET NULL ON UPDATE CASCADE,
	FOREIGN KEY (ID_ENTRENADOR) REFERENCES PERSONAJE(ID) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE SUPERTECNICA (
	ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	NOMBRE VARCHAR(35) NOT NULL,
	COORDINADA BOOLEAN NOT NULL,
	POTENCIA VARCHAR(25) NOT NULL,
	TIPO VARCHAR(35) NOT NULL,
	ID_ATRIBUTO INT,
	FOREIGN KEY (ID_ATRIBUTO) REFERENCES ATRIBUTO(ID) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE JUEGA (
	ID_PERSONAJE INT,
	ID_EQUIPO INT,
	FOREIGN KEY (ID_PERSONAJE) REFERENCES PERSONAJE(ID) ON DELETE SET NULL ON UPDATE CASCADE,
	FOREIGN KEY (ID_EQUIPO) REFERENCES EQUIPO(ID) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX (ID_PERSONAJE, ID_EQUIPO)
);

CREATE TABLE TIENE (
	ID_PERSONAJE INT,
	ID_SUPERTECNICA INT,
	FOREIGN KEY (ID_PERSONAJE) REFERENCES PERSONAJE(ID) ON DELETE SET NULL ON UPDATE CASCADE,
	FOREIGN KEY (ID_SUPERTECNICA) REFERENCES SUPERTECNICA(ID) ON DELETE SET NULL ON UPDATE CASCADE,
    INDEX (ID_PERSONAJE, ID_SUPERTECNICA)
);

INSERT INTO JUEGA(ID_PERSONAJE, ID_EQUIPO) VALUES (1,1);
INSERT INTO JUEGA(ID_PERSONAJE, ID_EQUIPO) VALUES (2,1);
INSERT INTO JUEGA(ID_PERSONAJE, ID_EQUIPO) VALUES (1,3);
INSERT INTO JUEGA(ID_PERSONAJE, ID_EQUIPO) VALUES (2,3);

INSERT INTO TIENE(ID_PERSONAJE, ID_SUPERTECNICA) VALUES (1,1);
INSERT INTO TIENE(ID_PERSONAJE, ID_SUPERTECNICA) VALUES (2,1);
INSERT INTO TIENE(ID_PERSONAJE, ID_SUPERTECNICA) VALUES (1,3);
INSERT INTO TIENE(ID_PERSONAJE, ID_SUPERTECNICA) VALUES (2,3);

INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, GENERO ,IMAGEN, ID_ATRIBUTO, ID_USUARIO) VALUES ('Xene', 'Xene', 'El guerrero definitivo, nacido de la Operación Génesis. Sus disparos surcan el cielo como cometas', 'FW','Hombre', 'https://dxi4wb638ujep.cloudfront.net/1/k/j/q/jqm20m_shls.webp', 1,2);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, GENERO ,IMAGEN, ID_ATRIBUTO, ID_USUARIO) VALUES ('Spencer Gates', 'Cosplay', 'Disfruta de novelas cursis y participa en concursos de escritura cada mes', 'DF','Neutro' ,'https://dxi4wb638ujep.cloudfront.net/1/k/v/p/vpocwdtqids.webp', 2,2);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, GENERO ,IMAGEN, ID_ATRIBUTO, ID_USUARIO) VALUES ('Marvin Murdock', 'Marvin', 'El mayor de tres hermanos y capitán de Kirkwood. Un perfeccionista que considera la victoria sagrada', 'FW', 'Hombre','https://dxi4wb638ujep.cloudfront.net/1/k/p/c/pcfzrjfd6jk.webp', 3,2);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, GENERO ,IMAGEN, ID_ATRIBUTO, ID_USUARIO) VALUES ('Byron Love', 'Aphrody', 'Cautiva a sus rivales con una gracia artística, jugando con el aura de una deidad desde lo alto', 'MF', 'Hombre','https://dxi4wb638ujep.cloudfront.net/1/k/d/y/dykb3jbxeis.webp', 4,2);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, GENERO ,IMAGEN, ID_ATRIBUTO, ID_USUARIO) VALUES ('Sean Lavender', 'Lavender', 'Ama la jardinería y cree firmemente en el poder calmante de la lavanda', 'FW', 'Neutro','https://dxi4wb638ujep.cloudfront.net/1/k/n/0/n0ogwdtbj1m.webp', 4,2);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, GENERO ,IMAGEN, ID_ATRIBUTO, ID_USUARIO) VALUES ('Eaton Rampage', 'Rampage', 'El chiste de la dieta de "ver comida" fue hecho para él. Come cualquier cosa que vea', 'FW', 'Hombre','https://dxi4wb638ujep.cloudfront.net/1/k/1/m/1mbqxxoacwk.webp', 3,2);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, GENERO ,IMAGEN, ID_ATRIBUTO, ID_USUARIO) VALUES ('Ian Smith', 'Smith', 'No hay dónde correr ni dónde esconderse de su mirada afilada como la de un halcón', 'DF', 'Hombre','https://dxi4wb638ujep.cloudfront.net/1/k/6/s/6ssmkasv0uu.webp', 2,2);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, GENERO ,IMAGEN, ID_ATRIBUTO, ID_USUARIO) VALUES ('Changsu Choi', 'Choi', 'Un creador de juego nato cuyo control preciso puede incluso rivalizar con Jude', 'MF', 'Hombre','https://dxi4wb638ujep.cloudfront.net/1/k/l/h/lhrjdsednmc.webp', 1,2);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, GENERO ,IMAGEN, ID_ATRIBUTO, ID_USUARIO) VALUES ('Mac Robi', 'Robingo', 'Capitán de la selección nacional de Brasil, The Kingdom. Apodado el Rey del Gol', 'FW', 'Hombre','https://dxi4wb638ujep.cloudfront.net/1/k/x/a/xay52ifu7qm.webp', 1,2);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, GENERO ,IMAGEN, ID_ATRIBUTO, ID_USUARIO) VALUES ('Nuel', 'Nuel', 'Sus poderes mágicos resultan muy útiles en el campo, y además todas las chicas lo adoran', 'MF', 'Hombre','https://dxi4wb638ujep.cloudfront.net/1/k/q/x/qxayjjoaoge.webp', 2,2);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, GENERO ,IMAGEN, ID_ATRIBUTO, ID_USUARIO) VALUES ('Cole Tivator', 'Tivator', 'Intenta recuperar terrenos baldíos y convertirlos en tierras de cultivo productivas', 'DF', 'Hombre','https://dxi4wb638ujep.cloudfront.net/1/k/f/v/fvcwrm8-ele.webp', 3,2);

INSERT INTO ATRIBUTO (NOMBRE, IMAGEN_TIPO_ATRIBUTO) VALUES ('Fuego', 'https://static.wikia.nocookie.net/inazuma-eleven/images/6/61/Fire_icon_%28VR%29.png');
INSERT INTO ATRIBUTO (NOMBRE, IMAGEN_TIPO_ATRIBUTO) VALUES ('Viento', 'https://static.wikia.nocookie.net/inazuma-eleven/images/6/68/Wind_icon_%28VR%29.png');
INSERT INTO ATRIBUTO (NOMBRE, IMAGEN_TIPO_ATRIBUTO) VALUES ('Montaña', 'https://static.wikia.nocookie.net/inazuma-eleven/images/6/6a/Mountain_icon_%28VR%29.png');
INSERT INTO ATRIBUTO (NOMBRE, IMAGEN_TIPO_ATRIBUTO) VALUES ('Bosque', 'https://static.wikia.nocookie.net/inazuma-eleven/images/0/0b/Forest_icon_%28VR%29.png');

INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO, ID_CAPITAN, ID_ENTRENADOR) VALUES ('Raimon', 'Japón', 'https://static.wikia.nocookie.net/inazuma/images/e/e1/Raimon_FF_Emblema.png/revision/latest?cb=20210620190405&path-prefix=es', 1, 2);
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO, ID_CAPITAN, ID_ENTRENADOR) VALUES ('Zeus', 'Grecia', 'https://static.wikia.nocookie.net/inazuma/images/f/ff/Escudo_Zeus_FF.png/revision/latest?cb=20210620190427&path-prefix=es', 2, 4);
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO, ID_CAPITAN, ID_ENTRENADOR) VALUES ('Royal Academy', 'Japón', 'https://static.wikia.nocookie.net/inazuma/images/5/52/Escudo_-_Royal_Academy_%28SD%29.png/revision/latest?cb=20231225133321&path-prefix=es', 3, 6);
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO, ID_CAPITAN, ID_ENTRENADOR) VALUES ('Kirkwood', 'Japón', NULL, 4, 1);
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO, ID_CAPITAN, ID_ENTRENADOR) VALUES ('Genesis', 'Japón', 'https://static.wikia.nocookie.net/inazuma/images/9/93/Escudo_G%C3%A9nesis_%28IE_HVR%29.png/revision/latest?cb=20240401112105&path-prefix=es', 5, 3);
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO, ID_CAPITAN, ID_ENTRENADOR) VALUES ('The Kingdom', 'Brasil', 'https://static.wikia.nocookie.net/inazuma/images/0/0c/Escudo_-_Os_Reis_%28SD%29.png/revision/latest?cb=20240918154716&path-prefix=es', 6, 5);
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO, ID_CAPITAN, ID_ENTRENADOR) VALUES ('Little Gigant', 'África', 'https://static.wikia.nocookie.net/inazuma/images/d/d1/Logo_peque%C3%B1os_gigantes.png/revision/latest?cb=20110617155403&path-prefix=es', 5, 2);
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO, ID_CAPITAN, ID_ENTRENADOR)VALUES ('Orpheus', 'Italia', 'https://static.wikia.nocookie.net/inazuma/images/8/8a/Escudo_-_Orfeo_%28SD%29.png/revision/latest?cb=20240906212620&path-prefix=es', 4, 4);
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO, ID_CAPITAN, ID_ENTRENADOR) VALUES ('Unicorn', 'Estados Unidos', 'https://static.wikia.nocookie.net/inazuma/images/1/1b/Unicorn_Emblema.png/revision/latest?cb=20240906134136&path-prefix=es', 3, 6);
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO, ID_CAPITAN, ID_ENTRENADOR) VALUES ('Fire Dragon', 'Corea', 'https://static.wikia.nocookie.net/inazuma/images/8/87/Escudo_-_Dragones_de_Fuego_%28Strikers%29.png/revision/latest?cb=20240815214436&path-prefix=es', 2, 1);
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO, ID_CAPITAN, ID_ENTRENADOR) VALUES ('Desert Lion', 'Qatar', NULL, 1, 3);

INSERT INTO SUPERTECNICA (NOMBRE, COORDINADA, POTENCIA, TIPO, ID_ATRIBUTO) VALUES ('Tornado de Fuego', 0, 'Alta', 'Disparo', 1);
INSERT INTO SUPERTECNICA (NOMBRE, COORDINADA, POTENCIA, TIPO, ID_ATRIBUTO) VALUES ('Corte del Viento', 0, 'Media', 'Disparo', 2);
INSERT INTO SUPERTECNICA (NOMBRE, COORDINADA, POTENCIA, TIPO, ID_ATRIBUTO) VALUES ('Muro de Rocas', 0, 'Alta', 'Defensa', 3);
INSERT INTO SUPERTECNICA (NOMBRE, COORDINADA, POTENCIA, TIPO, ID_ATRIBUTO) VALUES ('Bosque Encantado', 1, 'Media', 'Regate', 4);
INSERT INTO SUPERTECNICA (NOMBRE, COORDINADA, POTENCIA, TIPO, ID_ATRIBUTO) VALUES ('Rayo Celestial', 0, 'Alta', 'Disparo', 4);
INSERT INTO SUPERTECNICA (NOMBRE, COORDINADA, POTENCIA, TIPO, ID_ATRIBUTO) VALUES ('Muralla Helada', 0, 'Media', 'Defensa', 3);
INSERT INTO SUPERTECNICA (NOMBRE, COORDINADA, POTENCIA, TIPO, ID_ATRIBUTO) VALUES ('Sombra Mortal', 1, 'Alta', 'Disparo', 2);
INSERT INTO SUPERTECNICA (NOMBRE, COORDINADA, POTENCIA, TIPO, ID_ATRIBUTO) VALUES ('Luz Divina', 0, 'Alta', 'Parada', 1);
INSERT INTO SUPERTECNICA (NOMBRE, COORDINADA, POTENCIA, TIPO, ID_ATRIBUTO) VALUES ('Cañón de Acero', 0, 'Alta', 'Disparo', 2);
INSERT INTO SUPERTECNICA (NOMBRE, COORDINADA, POTENCIA, TIPO, ID_ATRIBUTO) VALUES ('Terremoto', 1, 'Alta', 'Defensa', 3);
INSERT INTO SUPERTECNICA (NOMBRE, COORDINADA, POTENCIA, TIPO, ID_ATRIBUTO) VALUES ('Marea Gigante', 1, 'Alta', 'Disparo', 1);

INSERT INTO USUARIO (NOMBRE,CONTRASENHA) VALUES ('admin','abc123.');
INSERT INTO USUARIO (NOMBRE,CONTRASENHA) VALUES ('invitado','abc123.');

SET FOREIGN_KEY_CHECKS=1;






