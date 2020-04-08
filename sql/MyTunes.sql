-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Apr 08, 2020 at 08:49 AM
-- Server version: 5.5.62
-- PHP Version: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `MyTunes`
--

-- --------------------------------------------------------

--
-- Table structure for table `Album`
--

CREATE TABLE `Album` (
  `id` int(11) NOT NULL,
  `nomAlbum` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Artiste`
--

CREATE TABLE `Artiste` (
  `id` int(11) NOT NULL,
  `nomArtiste` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Chanson`
--

CREATE TABLE `Chanson` (
  `id` int(11) NOT NULL,
  `titre` varchar(50) DEFAULT NULL,
  `nomFichier` varchar(60) NOT NULL,
  `idAlbum` int(11) DEFAULT NULL,
  `idArtiste` int(11) DEFAULT NULL,
  `idGenre` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ContientPlaylist`
--

CREATE TABLE `ContientPlaylist` (
  `idPlaylist` int(11) NOT NULL,
  `idChanson` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Genre`
--

CREATE TABLE `Genre` (
  `id` int(11) NOT NULL,
  `nomGenre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Playlist`
--

CREATE TABLE `Playlist` (
  `id` int(11) NOT NULL,
  `nomPlaylist` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Album`
--
ALTER TABLE `Album`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Artiste`
--
ALTER TABLE `Artiste`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Chanson`
--
ALTER TABLE `Chanson`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idAlbum` (`idAlbum`),
  ADD KEY `idArtiste` (`idArtiste`),
  ADD KEY `idGenre` (`idGenre`);

--
-- Indexes for table `ContientPlaylist`
--
ALTER TABLE `ContientPlaylist`
  ADD KEY `idChanson` (`idChanson`),
  ADD KEY `idPlaylist` (`idPlaylist`);

--
-- Indexes for table `Genre`
--
ALTER TABLE `Genre`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Playlist`
--
ALTER TABLE `Playlist`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Album`
--
ALTER TABLE `Album`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Artiste`
--
ALTER TABLE `Artiste`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Chanson`
--
ALTER TABLE `Chanson`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Genre`
--
ALTER TABLE `Genre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `Playlist`
--
ALTER TABLE `Playlist`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Chanson`
--
ALTER TABLE `Chanson`
  ADD CONSTRAINT `Chanson_ibfk_1` FOREIGN KEY (`idAlbum`) REFERENCES `Album` (`id`),
  ADD CONSTRAINT `Chanson_ibfk_2` FOREIGN KEY (`idArtiste`) REFERENCES `Artiste` (`id`),
  ADD CONSTRAINT `Chanson_ibfk_3` FOREIGN KEY (`idGenre`) REFERENCES `Genre` (`id`);

--
-- Constraints for table `ContientPlaylist`
--
ALTER TABLE `ContientPlaylist`
  ADD CONSTRAINT `ContientPlaylist_ibfk_1` FOREIGN KEY (`idChanson`) REFERENCES `Chanson` (`id`),
  ADD CONSTRAINT `ContientPlaylist_ibfk_2` FOREIGN KEY (`idPlaylist`) REFERENCES `Playlist` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
