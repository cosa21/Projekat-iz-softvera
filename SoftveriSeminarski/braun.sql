-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 06, 2018 at 07:44 PM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `lilidrogerija`
--

-- --------------------------------------------------------

--
-- Table structure for table `dobavljac`
--

CREATE TABLE `dobavljac` (
  `dobavljacID` int(11) NOT NULL,
  `nazivDobavljaca` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `PIB` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `maticniBroj` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tekuciRacun` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `banka` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `grad` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `drzavaID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `dobavljac`
--

INSERT INTO `dobavljac` (`dobavljacID`, `nazivDobavljaca`, `PIB`, `maticniBroj`, `tekuciRacun`, `banka`, `grad`, `drzavaID`) VALUES
(2, 'MaxFactor', '26266585', '555989962', '26526-225', 'Erste bank', 'Beograd', 1),
(3, 'Rimmel', '256156151', '2615889663', '6266-155', 'Erste', 'Pariz', 2),
(9, 'Avon', '45454', '5156156', '165215-14', 'UniCredit', 'Berlin', 4),
(10, 'Bourjois', '2621516', '666266363', '2626-626', 'Erste', 'Pariz', 2),
(11, 'Nyx', '53620626', '22586255', '62515-1502', 'UniCredit', 'Novi Sad', 1),
(14, 'Max', '262626', '266161', '26161-26', 'Erste', 'Arilje', 1);

-- --------------------------------------------------------

--
-- Table structure for table `drzava`
--

CREATE TABLE `drzava` (
  `drzavaID` int(11) NOT NULL,
  `nazivDrzave` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `drzava`
--

INSERT INTO `drzava` (`drzavaID`, `nazivDrzave`) VALUES
(1, 'Srbija'),
(2, 'Francuska'),
(3, 'Italija'),
(4, 'Nemacka'),
(5, 'Belgija'),
(6, 'Svedska');

-- --------------------------------------------------------

--
-- Table structure for table `igrac`
--

CREATE TABLE `igrac` (
  `igracID` int(11) NOT NULL,
  `ime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prezime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `timID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `igrac`
--

INSERT INTO `igrac` (`igracID`, `ime`, `prezime`, `timID`) VALUES
(1, 'mjkxda', 'cmska', 2);

-- --------------------------------------------------------

--
-- Table structure for table `menadzer`
--

CREATE TABLE `menadzer` (
  `menadzerID` int(11) NOT NULL,
  `ime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prezime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sifraMenadzera` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `menadzer`
--

INSERT INTO `menadzer` (`menadzerID`, `ime`, `prezime`, `sifraMenadzera`) VALUES
(1, 'Maja', 'Radosavljevic', 'maja995'),
(2, 'Ana', 'Markovic', 'ancina');

-- --------------------------------------------------------

--
-- Table structure for table `narudzbenica`
--

CREATE TABLE `narudzbenica` (
  `narudzbenicaID` int(11) NOT NULL,
  `datum` date NOT NULL,
  `ukupniIznos` decimal(10,0) NOT NULL,
  `menadzerID` int(11) NOT NULL,
  `dobavljacID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `narudzbenica`
--

INSERT INTO `narudzbenica` (`narudzbenicaID`, `datum`, `ukupniIznos`, `menadzerID`, `dobavljacID`) VALUES
(8, '2018-02-09', '1958', 2, 2),
(10, '2018-02-02', '1950', 1, 2),
(11, '1995-11-11', '658', 2, 11),
(13, '2005-11-11', '1500', 2, 3),
(15, '2018-09-04', '1200', 2, 10),
(16, '2018-09-04', '658', 2, 9),
(17, '2018-09-04', '1966', 1, 9),
(18, '2018-09-04', '650', 1, 10),
(20, '2018-09-05', '4850', 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `proizvod`
--

CREATE TABLE `proizvod` (
  `proizvodID` int(11) NOT NULL,
  `nazivProizvoda` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `cena` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `proizvod`
--

INSERT INTO `proizvod` (`proizvodID`, `nazivProizvoda`, `cena`) VALUES
(1, 'Highligter Sun&Shine', '500'),
(2, 'Red lipstick', '658'),
(3, 'Mascara 3 in 1 perfect for night', '650'),
(4, 'Fit me matt foundation', '1200');

-- --------------------------------------------------------

--
-- Table structure for table `stavkanarudzbenice`
--

CREATE TABLE `stavkanarudzbenice` (
  `sifraStavke` int(11) NOT NULL,
  `narudzbenicaID` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL,
  `vrednost` decimal(10,0) NOT NULL,
  `proizvodID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `stavkanarudzbenice`
--

INSERT INTO `stavkanarudzbenice` (`sifraStavke`, `narudzbenicaID`, `kolicina`, `vrednost`, `proizvodID`) VALUES
(1, 8, 1, '658', 2),
(1, 10, 2, '1300', 3),
(1, 11, 1, '658', 2),
(1, 13, 3, '1500', 1),
(1, 15, 1, '1200', 4),
(1, 16, 1, '658', 2),
(1, 17, 1, '650', 3),
(1, 18, 1, '650', 3),
(1, 20, 2, '1300', 3),
(2, 8, 2, '1300', 3),
(2, 10, 1, '650', 3),
(2, 15, 1, '1200', 4),
(2, 16, 1, '650', 3),
(2, 17, 2, '1316', 2),
(2, 20, 2, '2400', 4),
(3, 20, 1, '500', 1),
(4, 20, 1, '650', 3);

-- --------------------------------------------------------

--
-- Table structure for table `tim`
--

CREATE TABLE `tim` (
  `timID` int(11) NOT NULL,
  `nazivTima` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tim`
--

INSERT INTO `tim` (`timID`, `nazivTima`) VALUES
(1, 'Crvena Zvezda'),
(2, 'Partizan'),
(3, 'FK Rad'),
(4, 'Buducnost');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dobavljac`
--
ALTER TABLE `dobavljac`
  ADD PRIMARY KEY (`dobavljacID`),
  ADD KEY `drzavaID` (`drzavaID`),
  ADD KEY `drzavaID_2` (`drzavaID`);

--
-- Indexes for table `drzava`
--
ALTER TABLE `drzava`
  ADD PRIMARY KEY (`drzavaID`);

--
-- Indexes for table `igrac`
--
ALTER TABLE `igrac`
  ADD PRIMARY KEY (`igracID`),
  ADD KEY `timID` (`timID`);

--
-- Indexes for table `menadzer`
--
ALTER TABLE `menadzer`
  ADD PRIMARY KEY (`menadzerID`);

--
-- Indexes for table `narudzbenica`
--
ALTER TABLE `narudzbenica`
  ADD PRIMARY KEY (`narudzbenicaID`),
  ADD KEY `dobavljacID` (`dobavljacID`),
  ADD KEY `menadzerID` (`menadzerID`);

--
-- Indexes for table `proizvod`
--
ALTER TABLE `proizvod`
  ADD PRIMARY KEY (`proizvodID`);

--
-- Indexes for table `stavkanarudzbenice`
--
ALTER TABLE `stavkanarudzbenice`
  ADD PRIMARY KEY (`sifraStavke`,`narudzbenicaID`),
  ADD KEY `proizvodID` (`proizvodID`),
  ADD KEY `narudzbenicaID` (`narudzbenicaID`);

--
-- Indexes for table `tim`
--
ALTER TABLE `tim`
  ADD PRIMARY KEY (`timID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dobavljac`
--
ALTER TABLE `dobavljac`
  MODIFY `dobavljacID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `drzava`
--
ALTER TABLE `drzava`
  MODIFY `drzavaID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `igrac`
--
ALTER TABLE `igrac`
  MODIFY `igracID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `menadzer`
--
ALTER TABLE `menadzer`
  MODIFY `menadzerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `narudzbenica`
--
ALTER TABLE `narudzbenica`
  MODIFY `narudzbenicaID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `proizvod`
--
ALTER TABLE `proizvod`
  MODIFY `proizvodID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tim`
--
ALTER TABLE `tim`
  MODIFY `timID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dobavljac`
--
ALTER TABLE `dobavljac`
  ADD CONSTRAINT `dobavljac_ibfk_1` FOREIGN KEY (`drzavaID`) REFERENCES `drzava` (`DrzavaID`) ON UPDATE CASCADE;

--
-- Constraints for table `igrac`
--
ALTER TABLE `igrac`
  ADD CONSTRAINT `igrac_ibfk_1` FOREIGN KEY (`timID`) REFERENCES `tim` (`timID`);

--
-- Constraints for table `narudzbenica`
--
ALTER TABLE `narudzbenica`
  ADD CONSTRAINT `narudzbenica_ibfk_1` FOREIGN KEY (`dobavljacID`) REFERENCES `dobavljac` (`dobavljacID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `narudzbenica_ibfk_2` FOREIGN KEY (`menadzerID`) REFERENCES `menadzer` (`menadzerID`) ON UPDATE CASCADE;

--
-- Constraints for table `stavkanarudzbenice`
--
ALTER TABLE `stavkanarudzbenice`
  ADD CONSTRAINT `stavkanarudzbenice_ibfk_1` FOREIGN KEY (`narudzbenicaID`) REFERENCES `narudzbenica` (`narudzbenicaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `stavkanarudzbenice_ibfk_2` FOREIGN KEY (`proizvodID`) REFERENCES `proizvod` (`proizvodID`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
