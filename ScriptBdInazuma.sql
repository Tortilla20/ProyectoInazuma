SET FOREIGN_KEY_CHECKS=0;
DROP DATABASE IF EXISTS InazumaEleven;
CREATE DATABASE InazumaEleven;
USE InazumaEleven;

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
	FOREIGN KEY (ID_ATRIBUTO) REFERENCES ATRIBUTO(ID)
);

CREATE TABLE EQUIPO (
	ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	NOMBRE VARCHAR(35) NOT NULL,
	REGION VARCHAR(25) NOT NULL,
	ESCUDO VARCHAR(200),
	ID_CAPITAN INT,
	ID_ENTRENADOR INT,
	FOREIGN KEY (ID_CAPITAN) REFERENCES PERSONAJE(ID),
	FOREIGN KEY (ID_ENTRENADOR) REFERENCES PERSONAJE(ID)
);

CREATE TABLE SUPERTECNICA (
	ID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	NOMBRE VARCHAR(35) NOT NULL,
	COORDINADA BOOLEAN NOT NULL,
	POTENCIA VARCHAR(25) NOT NULL,
	TIPO VARCHAR(35) NOT NULL,
	ID_ATRIBUTO INT,
	FOREIGN KEY (ID_ATRIBUTO) REFERENCES ATRIBUTO(ID)
);

CREATE TABLE JUEGA (
	ID_PERSONAJE INT,
	ID_EQUIPO INT,
	PRIMARY KEY (ID_PERSONAJE, ID_EQUIPO),
	FOREIGN KEY (ID_PERSONAJE) REFERENCES PERSONAJE(ID),
	FOREIGN KEY (ID_EQUIPO) REFERENCES EQUIPO(ID)
);

CREATE TABLE TIENE (
	ID_PERSONAJE INT,
	ID_SUPERTECNICA INT,
	PRIMARY KEY (ID_PERSONAJE, ID_SUPERTECNICA),
	FOREIGN KEY (ID_PERSONAJE) REFERENCES PERSONAJE(ID),
	FOREIGN KEY (ID_SUPERTECNICA) REFERENCES SUPERTECNICA(ID)
);


CREATE TABLE USUARIO (
	ID INT AUTO_INCREMENT PRIMARY KEY,
	NOMBRE VARCHAR(30) NOT NULL,
	CONTRASENHA VARCHAR(60)
);



INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, IMAGEN, ID_ATRIBUTO) VALUES ('Xene', 'Xene', 'El guerrero definitivo, nacido de la Operación Génesis. Sus disparos surcan el cielo como cometas', 'FW', 'https://dxi4wb638ujep.cloudfront.net/1/k/j/q/jqm20m_shls.webp', 1);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, IMAGEN, ID_ATRIBUTO) VALUES ('Spencer Gates', 'Cosplay', 'Disfruta de novelas cursis y participa en concursos de escritura cada mes', 'DF', 'https://dxi4wb638ujep.cloudfront.net/1/k/v/p/vpocwdtqids.webp', 2);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, IMAGEN, ID_ATRIBUTO) VALUES ('Marvin Murdock', 'Marvin', 'El mayor de tres hermanos y capitán de Kirkwood. Un perfeccionista que considera la victoria sagrada', 'FW', 'https://dxi4wb638ujep.cloudfront.net/1/k/p/c/pcfzrjfd6jk.webp', 3);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, IMAGEN, ID_ATRIBUTO) VALUES ('Byron Love', 'Aphrody', 'Cautiva a sus rivales con una gracia artística, jugando con el aura de una deidad desde lo alto', 'MF', 'https://dxi4wb638ujep.cloudfront.net/1/k/d/y/dykb3jbxeis.webp', 4);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, IMAGEN, ID_ATRIBUTO) VALUES ('Sean Lavender', 'Lavender', 'Ama la jardinería y cree firmemente en el poder calmante de la lavanda', 'FW', 'https://dxi4wb638ujep.cloudfront.net/1/k/n/0/n0ogwdtbj1m.webp', 4);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, IMAGEN, ID_ATRIBUTO) VALUES ('Eaton Rampage', 'Rampage', 'El chiste de la dieta de "ver comida" fue hecho para él. Come cualquier cosa que vea', 'FW', 'https://dxi4wb638ujep.cloudfront.net/1/k/1/m/1mbqxxoacwk.webp', 3);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, IMAGEN, ID_ATRIBUTO) VALUES ('Ian Smith', 'Smith', 'No hay dónde correr ni dónde esconderse de su mirada afilada como la de un halcón', 'DF', 'https://dxi4wb638ujep.cloudfront.net/1/k/6/s/6ssmkasv0uu.webp', 2);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, IMAGEN, ID_ATRIBUTO) VALUES ('Changsu Choi', 'Choi', 'Un creador de juego nato cuyo control preciso puede incluso rivalizar con Jude', 'MF', 'https://dxi4wb638ujep.cloudfront.net/1/k/l/h/lhrjdsednmc.webp', 1);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, IMAGEN, ID_ATRIBUTO) VALUES ('Mac Robi', 'Robingo', 'Capitán de la selección nacional de Brasil, The Kingdom. Apodado el Rey del Gol', 'FW', 'https://dxi4wb638ujep.cloudfront.net/1/k/x/a/xay52ifu7qm.webp', 1);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, IMAGEN, ID_ATRIBUTO) VALUES ('Nuel', 'Nuel', 'Sus poderes mágicos resultan muy útiles en el campo, y además todas las chicas lo adoran', 'MF', 'https://dxi4wb638ujep.cloudfront.net/1/k/q/x/qxayjjoaoge.webp', 2);
INSERT INTO PERSONAJE (NOMBRE, ALIAS, DESCRIPCION, POSICION, IMAGEN, ID_ATRIBUTO) VALUES ('Cole Tivator', 'Tivator', 'Intenta recuperar terrenos baldíos y convertirlos en tierras de cultivo productivas', 'DF', 'https://dxi4wb638ujep.cloudfront.net/1/k/f/v/fvcwrm8-ele.webp', 3);

INSERT INTO ATRIBUTO (NOMBRE) VALUES ('Fuego', 'https://static.wikia.nocookie.net/inazuma-eleven/images/6/61/Fire_icon_%28VR%29.png');
INSERT INTO ATRIBUTO (NOMBRE) VALUES ('Viento', 'https://static.wikia.nocookie.net/inazuma-eleven/images/6/68/Wind_icon_%28VR%29.png');
INSERT INTO ATRIBUTO (NOMBRE) VALUES ('Montaña', 'https://static.wikia.nocookie.net/inazuma-eleven/images/6/6a/Mountain_icon_%28VR%29.png');
INSERT INTO ATRIBUTO (NOMBRE) VALUES ('Bosque', 'https://static.wikia.nocookie.net/inazuma-eleven/images/0/0b/Forest_icon_%28VR%29.png');


INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO) VALUES ('Raimon', 'Japón', 'IMAGE');
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO) VALUES ('Zeus', 'Grecia', 'IMAGE');
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO) VALUES ('Royal Academy', 'Japón', 'IMAGE');
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO) VALUES ('Kirkwood', 'Japón', 'IMAGE');
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO) VALUES ('Genesis', 'Japón', 'IMAGE');
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO) VALUES ('The Kingdom', 'Brasil', 'IMAGE');
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO) VALUES ('Little Gigant', 'África', 'IMAGE');
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO) VALUES ('Orpheus', 'Italia', 'IMAGE');
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO) VALUES ('Unicorn', 'Estados Unidos', 'IMAGE');
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO) VALUES ('Fire Dragon', 'Corea', 'IMAGE');
INSERT INTO EQUIPO (NOMBRE, REGION, ESCUDO) VALUES ('Desert Lion', 'Qatar', 'IMAGE');

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


SET FOREIGN_KEY_CHECKS=1;

