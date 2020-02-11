-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 11-02-2020 a las 09:54:37
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_guerreros`
--
CREATE DATABASE IF NOT EXISTS `bd_guerreros` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bd_guerreros`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clan`
--

CREATE TABLE `clan` (
  `ID_CLAN` int(1) NOT NULL,
  `NOMBRE_CLAN` varchar(20) NOT NULL,
  `PAIS` char(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clan`
--

INSERT INTO `clan` (`ID_CLAN`, `NOMBRE_CLAN`, `PAIS`) VALUES
(1, 'VIKINGOS', 'GROEN'),
(2, 'SAMURAIS', 'JAPON'),
(3, 'CABALLEROS', 'GER'),
(4, 'WULING', 'CHINA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `guerrero`
--

CREATE TABLE `guerrero` (
  `ID_GUERRERO` int(2) NOT NULL,
  `ID_CLAN` int(1) NOT NULL,
  `NOMBRE_GUERRERO` varchar(20) NOT NULL,
  `EDAD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `guerrero`
--

INSERT INTO `guerrero` (`ID_GUERRERO`, `ID_CLAN`, `NOMBRE_GUERRERO`, `EDAD`) VALUES
(1, 1, 'ARTURO', 25),
(2, 1, 'ELISA', 21),
(3, 1, 'GEORGE', 34),
(4, 2, 'RAGNAR', 25),
(5, 2, 'KRIS', 21),
(6, 2, 'THOR', 27),
(7, 3, 'YATO', 22),
(8, 3, 'UMI', 19),
(9, 3, 'RYU', 34),
(10, 4, 'JIANG', 43),
(11, 4, 'LEE', 26),
(12, 4, 'TIANDI', 28);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `localizacion`
--

CREATE TABLE `localizacion` (
  `PAIS` char(5) NOT NULL,
  `CONTINENTE` char(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `localizacion`
--

INSERT INTO `localizacion` (`PAIS`, `CONTINENTE`) VALUES
('CHINA', 'ASIA'),
('GER', 'EUROPA'),
('GROEN', 'EUROPA'),
('JAPON', 'ASIA');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clan`
--
ALTER TABLE `clan`
  ADD PRIMARY KEY (`ID_CLAN`),
  ADD KEY `PAIS` (`PAIS`);

--
-- Indices de la tabla `guerrero`
--
ALTER TABLE `guerrero`
  ADD PRIMARY KEY (`ID_GUERRERO`),
  ADD KEY `ID_CLAN` (`ID_CLAN`);

--
-- Indices de la tabla `localizacion`
--
ALTER TABLE `localizacion`
  ADD PRIMARY KEY (`PAIS`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clan`
--
ALTER TABLE `clan`
  ADD CONSTRAINT `clan_ibfk_1` FOREIGN KEY (`PAIS`) REFERENCES `localizacion` (`PAIS`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `guerrero`
--
ALTER TABLE `guerrero`
  ADD CONSTRAINT `guerrero_ibfk_1` FOREIGN KEY (`ID_CLAN`) REFERENCES `clan` (`ID_CLAN`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
