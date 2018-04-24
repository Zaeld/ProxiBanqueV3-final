-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 22 avr. 2018 à 10:12
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

--
-- Base de données :  `proxibanque`
--
CREATE DATABASE IF NOT EXISTS `proxibanque` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `proxibanque`;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `proxibanquev2`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `idClient` int(11) NOT NULL AUTO_INCREMENT,
  `adresse` varchar(255) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `codePostal` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `idConseiller` int(10) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `soldeTotal` int(11) NOT NULL,
  PRIMARY KEY (`idClient`),
  KEY `fk_conseiller` (`idConseiller`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`idClient`, `adresse`, `nom`, `prenom`, `codePostal`, `ville`, `email`, `idConseiller`, `telephone`, `soldeTotal`) VALUES
(1, '1 cours Lafayette', 'Gaillard', 'Pierre', '69006', 'Lyon', 'pierre@gmail.com', 1, '123456789', 0),
(2, '2 cours Lafayette', 'Barbier', 'Paul', '69006', 'Lyon', 'paul@gmail.com', 1, '134567892', 0),
(3, '20 cours Lafayette', 'Agostini', 'Jean', '69006', 'Lyon', 'jean@gmail.com', 2, '134565842', 0),
(4, '18 cours Lafayette', 'Raoult', 'Alain', '69006', 'Lyon', 'alain@gmail.com', 1, '134562536', 0),
(5, '150 cours Lafayette', 'Fabrette', 'Valériette', '69006', 'Lyon', 'valérie@gmail.com', 1, '134561274', 0),
(6, '87 cours Lafayette', 'Domingues', 'Laurence', '69006', 'Lyon', 'laurence@gmail.com', 2, '134560325', 0),
(7, '65 cours Lafayette', 'Bouyer', 'Louis', '69006', 'Lyon', 'louis@gmail.com', 1, '134569012', 0),
(8, '42 cours Lafayette', 'Franck', 'David', '69006', 'Lyon', 'david@gmail.com', 1, '134566590', 0),
(9, '15 cours Lafayette', 'Kieffer', 'Nolwen', '69006', 'Lyon', 'nolwen@gmail.com', 2, '134563673', 0);

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `idCompte` int(10) NOT NULL AUTO_INCREMENT,
  `numeroCompte` int(10) NOT NULL,
  `decouvertAutorise` decimal(15,2) DEFAULT NULL,
  `tauxInteret` decimal(4,2) DEFAULT NULL,
  `solde` decimal(15,2) DEFAULT NULL,
  `typeCarte` varchar(255) DEFAULT NULL,
  `typeDeCompte` varchar(255) DEFAULT 'courant',
  `idClient` int(10) NOT NULL,
  PRIMARY KEY (`idCompte`),
  KEY `fk_client` (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`idCompte`, `numeroCompte`, `decouvertAutorise`, `tauxInteret`, `solde`, `typeCarte`, `typeDeCompte`, `idClient`) VALUES
(1, 17838547, '-1000.00', NULL, '-8000.00', 'ELECTRON', 'courant', 1),
(2, 12457896, NULL, '3.00', '100000.00', NULL, 'epargne', 2),
(3, 79461325, '-1000.00', NULL, '100000.00', 'PREMIER', 'courant', 3),
(4, 46937145, NULL, '3.00', '800000.00', NULL, 'epargne', 3),
(5, 26845248, '-1000.00', NULL, '2500.00', 'ELECTRON', 'courant', 4),
(6, 12541236, NULL, '3.00', '8000.00', NULL, 'epargne', 4),
(7, 521436712, '-1000.00', NULL, '100000.00', 'PREMIER', 'courant', 5),
(8, 12352458, NULL, '3.00', '300000.00', NULL, 'epargne', 5),
(9, 142541325, '-1000.00', NULL, '500.00', 'ELECTRON', 'courant', 6),
(10, 236521451, NULL, '3.00', '700000.00', NULL, 'epargne', 7),
(11, 1365857412, '-1000.00', NULL, '100000.00', 'ELECTRON', 'courant', 8),
(12, 41257895, '-1000.00', NULL, '-61000.00', 'PREMIER', 'courant', 9);

-- --------------------------------------------------------

--
-- Structure de la table `conseiller`
--

DROP TABLE IF EXISTS `conseiller`;
CREATE TABLE IF NOT EXISTS `conseiller` (
  `idConseiller` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idConseiller`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `conseiller`
--

INSERT INTO `conseiller` (`idConseiller`, `nom`, `prenom`) VALUES
(1, 'Jackson', 'Michael'),
(2, 'Solo', 'Han'),
(3, 'Skywalker', 'Luke');

-- --------------------------------------------------------

--
-- Structure de la table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `idLogin` int(10) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) DEFAULT NULL,
  `motDePasse` varchar(255) DEFAULT NULL,
  `idConseiller` int(10) DEFAULT NULL,
  PRIMARY KEY (`idLogin`),
  KEY `fk_conseiller_login` (`idConseiller`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `login`
--

INSERT INTO `login` (`idLogin`, `login`, `motDePasse`, `idConseiller`) VALUES
(1, 'theboss', '412563', 1),
(2, 'championdu69', '782541', 2),
(3, 'laforce', '584589', 3);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
