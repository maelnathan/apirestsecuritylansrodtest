-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Mar 31, 2021 at 07:29 PM
-- Server version: 5.7.26
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `apirestlansrod_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `contrat`
--

CREATE TABLE `contrat` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `contrat`
--

INSERT INTO `contrat` (`id`, `description`, `nom`) VALUES
(1, 'Contrat à Durée Indéterminée', 'CDI'),
(2, 'Contrat à Durée Déterminée', 'CDD'),
(3, 'Pour formation', 'ALTERNANCE');

-- --------------------------------------------------------

--
-- Table structure for table `entreprise`
--

CREATE TABLE `entreprise` (
  `id` bigint(20) NOT NULL,
  `adresse` varchar(100) DEFAULT NULL,
  `raison_sociale` varchar(100) NOT NULL,
  `siren` varchar(255) NOT NULL,
  `siret` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `entreprise`
--

INSERT INTO `entreprise` (`id`, `adresse`, `raison_sociale`, `siren`, `siret`) VALUES
(1, 'Yopougon, Figayo', 'NAYOMI', '123000879', '12300087900012'),
(2, 'Riviera, Bonoumin', 'THV Technologie', '123000879', '12300087900012'),
(3, 'Yaoussoukro, Habitat Château d\'eau', 'Hôte Tranquille', '12345678900987', '6565789443469909876'),
(4, 'Abidjan, Yopougon Carrefour Tiken Dja', 'FERRARI Bar', '0909098776666', '64575386863');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `name` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(7, 'ROLE_PM'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `salarie`
--

CREATE TABLE `salarie` (
  `id` bigint(20) NOT NULL,
  `date_embauche` datetime NOT NULL,
  `nom` varchar(100) NOT NULL,
  `nss` varchar(255) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `salaire` double DEFAULT NULL,
  `entreprise_id` bigint(20) NOT NULL,
  `contrat_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `salarie`
--

INSERT INTO `salarie` (`id`, `date_embauche`, `nom`, `nss`, `prenom`, `salaire`, `entreprise_id`, `contrat_id`) VALUES
(1, '1999-03-30 13:05:57', 'AMANI', '1234567891234', 'AMENAN', 500000, 1, 1),
(3, '2000-03-30 17:00:52', 'OULLA', '76567897655', 'Kouakou', 2000000, 3, 1),
(4, '2000-03-30 17:00:52', 'GUEU', '76567897655', 'Urbain', 2000000, 2, 2),
(5, '2000-03-30 17:00:52', 'KOUAME', '009998877', 'Doudou', 2500000, 2, 1),
(6, '2000-03-30 17:00:52', 'DANHO', '98786565', 'Flavien', 1500000, 2, 2),
(7, '2000-03-30 17:00:52', 'DAMIEN', '111122233', 'Dev', 1500000, 2, 3),
(8, '2000-03-30 17:00:52', 'N\'DRI', '111122233', 'Corine', 1500000, 1, 2),
(9, '2000-03-30 17:00:52', 'SIBO', '111122233', 'Jeacques', 100000, 4, 1),
(10, '2000-03-30 17:00:52', 'DOLPHO', '111122233', 'Sangôh', 100000, 4, 2),
(11, '2000-03-30 17:00:52', 'N\'DRI', '111122233', 'Corine', 1500000, 1, 3),
(12, '2000-03-30 17:00:52', 'N\'DRI', '111122233', 'Corine', 1500000, 1, 1),
(13, '2020-03-31 11:49:40', 'KOUASSI', '98976543', 'ISAAC', 156000, 2, 1),
(14, '2020-03-31 11:49:40', 'MOUSSA', '989765431', 'TRAORE', 100000, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `name`, `password`, `username`) VALUES
(1, 'oullajean@gmail.com', 'OULLA', 'P@55word', 'Ratcho'),
(2, 'jeanhonorat@gmail.com', 'Yao', '$2a$10$RQwndCY7eeYKhuVekGQTAuXCm/b70R98STMSfRH5AOoksc3rUCiZm', 'oulla');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(2, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contrat`
--
ALTER TABLE `contrat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `entreprise`
--
ALTER TABLE `entreprise`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_nb4h0p6txrmfc0xbrd1kglp9t` (`name`);

--
-- Indexes for table `salarie`
--
ALTER TABLE `salarie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKia1oe07dcipm37xbknho5v678` (`entreprise_id`),
  ADD KEY `FKk7o4fyoux6n7o82mbv8tecoyp` (`contrat_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- Indexes for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contrat`
--
ALTER TABLE `contrat`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `entreprise`
--
ALTER TABLE `entreprise`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `salarie`
--
ALTER TABLE `salarie`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `salarie`
--
ALTER TABLE `salarie`
  ADD CONSTRAINT `FKia1oe07dcipm37xbknho5v678` FOREIGN KEY (`entreprise_id`) REFERENCES `entreprise` (`id`),
  ADD CONSTRAINT `FKk7o4fyoux6n7o82mbv8tecoyp` FOREIGN KEY (`contrat_id`) REFERENCES `contrat` (`id`);

--
-- Constraints for table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
