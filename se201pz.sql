-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 23, 2018 at 09:31 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 5.6.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `se201pz`
--

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `ID` int(11) NOT NULL,
  `USERNAME` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `PASSWORD` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `ROLE` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `ENABLED` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`ID`, `USERNAME`, `PASSWORD`, `ROLE`, `ENABLED`) VALUES
(1, 'petar', '123456', 'ROLE_ADMIN', 1),
(2, 'jovan', '123456', 'ROLE_USER', 1);

-- --------------------------------------------------------

--
-- Table structure for table `rasa_zivotinja`
--

CREATE TABLE `rasa_zivotinja` (
  `ID` int(11) NOT NULL,
  `VRSTA_ZIVOTINJA_ID` int(11) NOT NULL,
  `IME` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `rasa_zivotinja`
--

INSERT INTO `rasa_zivotinja` (`ID`, `VRSTA_ZIVOTINJA_ID`, `IME`) VALUES
(1, 2, 'Sijamska'),
(3, 1, 'Bernardinac'),
(4, 3, 'Africka zeba'),
(6, 1, 'Nemacki ovcar'),
(7, 1, 'Sarplaninac'),
(8, 1, 'Labrador'),
(9, 1, 'Buldog'),
(10, 4, 'Kunic');

-- --------------------------------------------------------

--
-- Table structure for table `vrsta_zivotinja`
--

CREATE TABLE `vrsta_zivotinja` (
  `ID` int(11) NOT NULL,
  `IME` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `vrsta_zivotinja`
--

INSERT INTO `vrsta_zivotinja` (`ID`, `IME`) VALUES
(1, 'Pas'),
(2, 'Macka'),
(3, 'Ptica'),
(4, 'Zec');

-- --------------------------------------------------------

--
-- Table structure for table `zivotinja`
--

CREATE TABLE `zivotinja` (
  `ID` int(11) NOT NULL,
  `RASA_ZIVOTINJA_ID` int(11) DEFAULT NULL,
  `VRSTA_ZIVOTINJA_ID` int(11) NOT NULL,
  `KORISNIK_ID` int(11) DEFAULT NULL,
  `IME` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `OPIS` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `DATUM_KREIRANJA` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `zivotinja`
--

INSERT INTO `zivotinja` (`ID`, `RASA_ZIVOTINJA_ID`, `VRSTA_ZIVOTINJA_ID`, `KORISNIK_ID`, `IME`, `OPIS`, `DATUM_KREIRANJA`) VALUES
(3, 6, 1, 2, 'Alex', 'Veseo pas, verovatno ima oko 2 godine.', '2018-06-23 17:42:33'),
(4, 1, 2, NULL, 'Tom', 'Potpuno bela, verovatno ima oko 3 godine.', '2018-06-23 17:43:57'),
(5, 4, 3, NULL, 'Eni', 'Braon boje, stara oko 6 meseci.', '2018-06-23 18:17:55'),
(7, 8, 1, NULL, 'Bobi', 'Kratkodlaki, crni, ima oko 4 godine.', '2018-06-23 18:25:15');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `rasa_zivotinja`
--
ALTER TABLE `rasa_zivotinja`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_SVAKA_RASA_ZIVOTINJA_MORA_IMATI_SVOJU_VRSTU_ZIVOTINJE` (`VRSTA_ZIVOTINJA_ID`);

--
-- Indexes for table `vrsta_zivotinja`
--
ALTER TABLE `vrsta_zivotinja`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `zivotinja`
--
ALTER TABLE `zivotinja`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_OGLAS_MOZE_IMATI_RASU_ZIVOTINJE_ALI_NE_MORA` (`RASA_ZIVOTINJA_ID`),
  ADD KEY `FK_SVAKA_ZIVOTINJA_MOZE_IMATI_KORISNIKA_KOJI_REZERVISE_ZIVOTINJU` (`KORISNIK_ID`),
  ADD KEY `FK_VRSTA_ZIVOTINJA_MORA_POSTOJATI_ZA_ZIVOTINJU` (`VRSTA_ZIVOTINJA_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `rasa_zivotinja`
--
ALTER TABLE `rasa_zivotinja`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `vrsta_zivotinja`
--
ALTER TABLE `vrsta_zivotinja`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `zivotinja`
--
ALTER TABLE `zivotinja`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `rasa_zivotinja`
--
ALTER TABLE `rasa_zivotinja`
  ADD CONSTRAINT `FK_SVAKA_RASA_ZIVOTINJA_MORA_IMATI_SVOJU_VRSTU_ZIVOTINJE` FOREIGN KEY (`VRSTA_ZIVOTINJA_ID`) REFERENCES `vrsta_zivotinja` (`ID`);

--
-- Constraints for table `zivotinja`
--
ALTER TABLE `zivotinja`
  ADD CONSTRAINT `FK_OGLAS_MOZE_IMATI_RASU_ZIVOTINJA_ALI_NE_MORA` FOREIGN KEY (`RASA_ZIVOTINJA_ID`) REFERENCES `rasa_zivotinja` (`ID`),
  ADD CONSTRAINT `FK_SVAKA_ZIVOTINJA_MOZE_IMATI_KORISNIKA_KOJI_PREUZIMA_ZIVOTINJU` FOREIGN KEY (`KORISNIK_ID`) REFERENCES `korisnik` (`ID`),
  ADD CONSTRAINT `FK_VRSTA_ZIVOTINJE_MORA_POSTOJATI_ZA_ZIVOTINJU` FOREIGN KEY (`VRSTA_ZIVOTINJA_ID`) REFERENCES `vrsta_zivotinja` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
