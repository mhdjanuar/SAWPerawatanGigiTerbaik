-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 31, 2025 at 11:04 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `saw_perawatan_gigi_terbaik`
--

-- --------------------------------------------------------

--
-- Table structure for table `alternatif`
--

CREATE TABLE `alternatif` (
  `id` int(11) NOT NULL,
  `id_peserta` int(11) NOT NULL,
  `id_sub_kreteria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `alternatif`
--

INSERT INTO `alternatif` (`id`, `id_peserta`, `id_sub_kreteria`) VALUES
(39, 7, 54),
(40, 7, 57),
(41, 7, 61),
(42, 7, 64),
(43, 7, 67),
(44, 8, 56),
(45, 8, 60),
(46, 8, 63),
(47, 8, 66),
(48, 8, 69),
(49, 9, 55),
(50, 9, 60),
(51, 9, 63),
(52, 9, 66),
(53, 9, 68);

-- --------------------------------------------------------

--
-- Table structure for table `criteria`
--

CREATE TABLE `criteria` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `type` enum('benefit','cost') NOT NULL,
  `bobot` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `criteria`
--

INSERT INTO `criteria` (`id`, `nama`, `type`, `bobot`) VALUES
(21, 'Biaya Perawatan', 'cost', 3.00),
(22, 'Lama Waktu Perawatan', 'cost', 4.00),
(23, 'Tingkat Efektivitas Perawatan ', 'benefit', 2.00),
(24, 'Kenyamanan Pasien', 'benefit', 5.00),
(25, 'Risiko Efek Samping', 'cost', 1.00);

-- --------------------------------------------------------

--
-- Table structure for table `perawatan`
--

CREATE TABLE `perawatan` (
  `id` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `description` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `perawatan`
--

INSERT INTO `perawatan` (`id`, `nama`, `description`) VALUES
(7, 'Scaling Gigi', 'Pembersihan karang gigi dan plak untuk menjaga kebersihan mulut'),
(8, 'Tambal Gigi', 'Menambal gigi berlubang dengan bahan komposit/amalgam'),
(9, 'Cabut Gigi', 'Pencabutan gigi yang rusak atau tidak bisa dipertahankan'),
(10, 'Bleaching Gigi', 'Pemutihan gigi untuk estetika'),
(11, 'Veneer Gigi', 'Lapisan tipis yang ditempelkan di permukaan depan gigi');

-- --------------------------------------------------------

--
-- Table structure for table `sub_criteria`
--

CREATE TABLE `sub_criteria` (
  `id` int(11) NOT NULL,
  `id_kreteria` int(11) NOT NULL,
  `jumlah_bobot` decimal(5,2) NOT NULL,
  `deskripsi` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sub_criteria`
--

INSERT INTO `sub_criteria` (`id`, `id_kreteria`, `jumlah_bobot`, `deskripsi`) VALUES
(54, 21, 5.00, '< Rp250.000'),
(55, 21, 4.00, 'Rp250.000 – Rp500.000'),
(56, 21, 3.00, 'Rp500.000 – Rp750.000'),
(57, 22, 5.00, '< 15 menit'),
(59, 22, 4.00, '15 – 30 menit'),
(60, 22, 3.00, '31 – 60 menit'),
(61, 23, 5.00, 'Sangat efektif (95–100%)'),
(62, 23, 4.00, 'Efektif (85–94%)'),
(63, 23, 3.00, 'Cukup efektif (70–84%)'),
(64, 24, 5.00, 'Sangat nyaman'),
(65, 24, 4.00, 'Nyaman'),
(66, 24, 3.00, 'Cukup nyaman'),
(67, 25, 5.00, 'Tidak ada efek samping'),
(68, 25, 4.00, 'Efek samping sangat ringan'),
(69, 25, 3.00, 'Efek samping ringan');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `alamat` text DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `alamat`, `username`, `password`, `email`) VALUES
(1, 'admin', 'Jl. Merdeka No. 1, Jakarta', 'admin', 'admin', 'admin'),
(2, 'Budi Santoso', 'Jl. Soekarno Hatta No. 45, Bandung', 'budis', '123456', 'budi@example.com'),
(3, 'Citra Lestari', 'Jl. Gatot Subroto No. 23, Surabaya', 'citral', '123456', 'citra@example.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `alternatif`
--
ALTER TABLE `alternatif`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_peserta` (`id_peserta`),
  ADD KEY `id_sub_kreteria` (`id_sub_kreteria`);

--
-- Indexes for table `criteria`
--
ALTER TABLE `criteria`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `perawatan`
--
ALTER TABLE `perawatan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sub_criteria`
--
ALTER TABLE `sub_criteria`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_kreteria` (`id_kreteria`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `alternatif`
--
ALTER TABLE `alternatif`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `criteria`
--
ALTER TABLE `criteria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `perawatan`
--
ALTER TABLE `perawatan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `sub_criteria`
--
ALTER TABLE `sub_criteria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `alternatif`
--
ALTER TABLE `alternatif`
  ADD CONSTRAINT `alternatif_ibfk_1` FOREIGN KEY (`id_peserta`) REFERENCES `perawatan` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `alternatif_ibfk_2` FOREIGN KEY (`id_sub_kreteria`) REFERENCES `sub_criteria` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `sub_criteria`
--
ALTER TABLE `sub_criteria`
  ADD CONSTRAINT `sub_criteria_ibfk_1` FOREIGN KEY (`id_kreteria`) REFERENCES `criteria` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
