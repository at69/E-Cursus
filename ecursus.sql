-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 05 Mars 2014 à 20:25
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `ecursus`
--
CREATE DATABASE IF NOT EXISTS `ecursus` DEFAULT CHARACTER SET utf8 COLLATE utf8_swedish_ci;
USE `ecursus`;

-- --------------------------------------------------------

--
-- Structure de la table `certificate`
--

CREATE TABLE IF NOT EXISTS `certificate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `courseId` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

-- --------------------------------------------------------

--
-- Structure de la table `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(200) NOT NULL,
  `path` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `image` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `course`
--

INSERT INTO `course` (`id`, `category`, `path`, `name`, `image`) VALUES
(1, 'DROIT', 'courses/Droit.txt', 'Protection des données', 'images/personalDataProtection.jpg'),
(2, 'ARCHITECTURE', 'courses/Résumé AD.txt', 'Active Directory', 'images/activeDirectory.jpg'),
(3, 'ARCHITECTURE', 'courses/Sharepoint.txt', 'Sharepoint', 'images/sharepoint.jpg'),
(4, 'ARCHITECTURE', 'courses/Virtualisation.txt', 'Virtualisation', 'images/virtu.jpg'),
(5, 'DEVELOPPEMENT', 'courses/notesjavaee.txt', 'Java Enterprise Edition', 'images/java.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `courseId` int(11) NOT NULL,
  `wording` varchar(200) NOT NULL,
  `answersArray` varchar(400) NOT NULL,
  `correctAnswer` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Contenu de la table `question`
--

INSERT INTO `question` (`id`, `courseId`, `wording`, `answersArray`, `correctAnswer`) VALUES
(1, 1, 'Qu''est-ce qu''une donnée à caractère personnel?', 'une information qui relève de la sphère intime/une information permettant de nous identifier facilement', 2),
(2, 1, 'La conservation de données personnelles d''un individu est autorisée si:', 'le responsable du traitement a obtenu un accord écrit signé de l''individu/le responsable du traitement n''a pas besoin d''autorisation', 1),
(3, 1, 'La collecte illicite de données personnelles est punie par:', '300 000 euros d''amende/5 ans d''emprisonnement (multiplié par 5 pour les personnes morales)/les deux', 3),
(4, 1, 'La loi du 6 janvier 1978 est la loi dite de l''informatique et des libertés', 'Vrai/Faux', 1),
(5, 1, 'Que veux dire l''acronyme CNIL?', 'Commission Nationale de l''Information et des Libertés/Commission Nationale de l''Informatique et des Libertés/Commission Nationale de l''Informatique et des Litiges', 2),
(6, 2, 'LDAP est l''acronyme pour...', 'Lightweight Directory Alban Protocol/Light Directory Access Protocol/Light Data Access Protocol/Lightweight Directory Access Protocol', 4),
(7, 2, 'Quel est le nom de l''outil de vérification du fonctionnement d''Active Directory?', 'Active Directory Functionality Check/Active Directory Best Practice Analyser/Active Directory Check/Active Directory Works Fine', 2),
(8, 2, 'Il y a 4 rôles FSMO pour Active Directory', 'Faux/Vrai', 1),
(9, 2, 'Qu''est-ce une forêt?', 'Un regroupement d''arbres où l''air sent la nature/Un regroupement de règles que l''on applique à un groupe d''objets Active Directory/Un regroupement de domaines', 3),
(10, 2, 'Un controleur de domaine est composé d''un service DNS et d''Active Directory Data Service', 'Vrai/Faux', 2),
(11, 3, 'Sharepoint a été développé par la communauté Linux', 'Faux/Vrai', 1),
(12, 3, 'Sharepoint est un produit de la marque Microsoft', 'Vrai/Faux', 1),
(13, 3, 'Parmi ces termes, sélectionnez celui qui est représenté dans la roue Sharepoint', 'Iptables/Skype/Business Intelligence', 3),
(14, 3, 'OWA signifie...', 'Outlook Web Access/Office Web Access/Only Write Access/Outlook Wep Application', 2),
(15, 3, 'Le(s) type(s) de métadonnée(s) est/sont:', 'Toxonomie/Folksonomie/les deux/aucun des deux', 3),
(16, 4, 'Il est impossible de virtualiser un espace de stockage', 'Vrai/Faux', 2),
(17, 4, 'Choisissez le bon ordre d''apparition des 3 générations de virtualisation', 'Hardware-assisted, Para, Full/Full, Hardware-assisted, Para/Para, Hardware-assisted, Full/Full, Para, Hardware-assisted', 4),
(18, 4, 'Qu''est-ce que le Green IT?', 'Planter un arbre à chaque fois qu''une machine virtuelle crash/Suivre un certain nombre de principes écologiques pour son service informatique/Une marque informatique', 2),
(19, 4, 'La haute disponibilité c''est...', 'Quand un serveur crash, les machines virtuelles dessus sont redémarrées sur un autre serveur/Quand l''informaticien boit beaucoup de café/Quand on donne la priorité à un processus Windows dans le gestionnaire des tâches', 1),
(20, 4, 'Multipathing signifie que...', 'Le serveur a deux écrans/On a plusieurs chemins pour se rendre au datacenter/Le serveur a plusieurs cartes réseau', 3),
(21, 5, 'Java Entreprise Edition réduit le temps passé à coder par rapport à Java Standard Edition.', 'Vrai/Faux', 1),
(22, 5, 'JPA est l''acronyme de...', 'Java Product Assistance/Java Persistence API/Java Persistence Access/Java Product API', 2),
(23, 5, 'JMS est l''acronyme de...', 'Java Manipulation Server/Java Manipulation Service/Java Messaging Server/Java Message Service', 4),
(24, 5, 'EJB est l''acronyme de...', 'Enterprise Java Bean/Enterprise Java Bin/Extracted Java Bean/Entitled Java Bin', 1),
(25, 5, 'Une NamedQuery est une requête nommée dans le langage de requêtage Java Persistence. Elle est liée à l''unité de persistence.', 'Faux/Vrai', 2);

-- --------------------------------------------------------

--
-- Structure de la table `readcourse`
--

CREATE TABLE IF NOT EXISTS `readcourse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `courseId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(200) NOT NULL,
  `lastName` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `connectionToken` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
