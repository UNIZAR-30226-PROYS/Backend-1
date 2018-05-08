-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-04-2018 a las 16:32:25
-- Versión del servidor: 10.1.28-MariaDB
-- Versión de PHP: 7.0.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `wolfic`
--

#CREATE USER 'root'@'%' IDENTIFIED BY '';
#GRANT ALL PRIVILEGES ON *.* TO 'root'@'%';

-- SET PASSWORD FOR 'root'@'%' = OLD_PASSWORD('mysql');

USE wolfsound;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `album`
--

CREATE TABLE `Album` (
  `idAlbum` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `idUser` varchar(50) NOT NULL,
  `fechaCreacion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='albunes de canciones';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cancion`
--

CREATE TABLE `Cancion` (
  `idCancion` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `genero` varchar(100) NOT NULL,
  `duracion` int(11) NOT NULL,
  `fechaSubida` date NOT NULL,
  `numRep` int(11) NOT NULL,
  `idUser` varchar(50) NOT NULL,
  `idAlbum` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cancioneslista`
--

CREATE TABLE `Cancioneslista` (
  `idCancLista` int(11) NOT NULL,
  `listaRep` int(11) NOT NULL,
  `idCancion` int(11) NOT NULL,
  `fechaIntroduccion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

CREATE TABLE `Comentario` (
  `idComentario` int(11) NOT NULL,
  `idCancion` int(11) NOT NULL,
  `idUser` varchar(50) NOT NULL,
  `cuerpo` varchar(500) NOT NULL,
  `fechaSubida` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `listarep`
--

CREATE TABLE `Listarep` (
  `idLista` int(11) NOT NULL,
  `idUser` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `numElementos` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suscribir`
--

CREATE TABLE `Suscribir` (
  `idSuscripcion` int(11) NOT NULL,
  `idSuscrito` varchar(50) NOT NULL,
  `idSuscriptor` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `Usuario` (
  `idUser` varchar(50) NOT NULL,
  `NomAp` varchar(100) NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Conexion` varchar(100) NOT NULL,
  `UltRep` int(11) NOT NULL,
  `Contrasenya` varchar(100) NOT NULL,
  `Publico` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `album`
--
ALTER TABLE `Album`
  ADD PRIMARY KEY (`idAlbum`),
  ADD KEY `idUser` (`idUser`);

--
-- Indices de la tabla `cancion`
--
ALTER TABLE `Cancion`
  ADD PRIMARY KEY (`idCancion`),
  ADD UNIQUE KEY `idCancion` (`idCancion`),
  ADD KEY `idUser` (`idUser`),
  ADD KEY `idAlbum` (`idAlbum`);

--
-- Indices de la tabla `cancioneslista`
--
ALTER TABLE `Cancioneslista`
  ADD PRIMARY KEY (`idCancLista`),
  ADD KEY `idCancion` (`idCancion`),
  ADD KEY `listaRep` (`listaRep`);

--
-- Indices de la tabla `comentario`
--
ALTER TABLE `Comentario`
  ADD PRIMARY KEY (`idComentario`),
  ADD KEY `idCancion` (`idCancion`),
  ADD KEY `idUser` (`idUser`);

--
-- Indices de la tabla `listarep`
--
ALTER TABLE `Listarep`
  ADD PRIMARY KEY (`idLista`),
  ADD KEY `idUser` (`idUser`);

--
-- Indices de la tabla `suscribir`
--
ALTER TABLE `Suscribir`
  ADD PRIMARY KEY (`idSuscripcion`),
  ADD KEY `idSuscriptor` (`idSuscriptor`),
  ADD KEY `idSuscrito` (`idSuscrito`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `Usuario`
  ADD PRIMARY KEY (`idUser`),
  ADD UNIQUE KEY `idUser` (`idUser`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `album`
--
ALTER TABLE `Album`
  MODIFY `idAlbum` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cancioneslista`
--
ALTER TABLE `Cancioneslista`
  MODIFY `idCancLista` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `comentario`
--
ALTER TABLE `Comentario`
  MODIFY `idComentario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `listarep`
--
ALTER TABLE `Listarep`
  MODIFY `idLista` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `suscribir`
--
ALTER TABLE `Suscribir`
  MODIFY `idSuscripcion` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `album`
--
ALTER TABLE `Album`
  ADD CONSTRAINT `album_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `usuario` (`idUser`);

--
-- Filtros para la tabla `cancion`
--
ALTER TABLE `Cancion`
  ADD CONSTRAINT `cancion_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `usuario` (`idUser`),
  ADD CONSTRAINT `cancion_ibfk_2` FOREIGN KEY (`idAlbum`) REFERENCES `album` (`idAlbum`);

--
-- Filtros para la tabla `cancioneslista`
--
ALTER TABLE `Cancioneslista`
  ADD CONSTRAINT `cancioneslista_ibfk_1` FOREIGN KEY (`idCancion`) REFERENCES `cancion` (`idCancion`),
  ADD CONSTRAINT `cancioneslista_ibfk_2` FOREIGN KEY (`listaRep`) REFERENCES `listarep` (`idLista`);

--
-- Filtros para la tabla `comentario`
--
ALTER TABLE `Comentario`
  ADD CONSTRAINT `comentario_ibfk_1` FOREIGN KEY (`idCancion`) REFERENCES `cancion` (`idCancion`),
  ADD CONSTRAINT `comentario_ibfk_2` FOREIGN KEY (`idUser`) REFERENCES `usuario` (`idUser`);

--
-- Filtros para la tabla `listarep`
--
ALTER TABLE `Listarep`
  ADD CONSTRAINT `listarep_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `usuario` (`idUser`);

--
-- Filtros para la tabla `suscribir`
--
ALTER TABLE `Suscribir`
  ADD CONSTRAINT `suscribir_ibfk_1` FOREIGN KEY (`idSuscriptor`) REFERENCES `usuario` (`idUser`),
  ADD CONSTRAINT `suscribir_ibfk_2` FOREIGN KEY (`idSuscrito`) REFERENCES `usuario` (`idUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
