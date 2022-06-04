-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 04 Jun 2022 pada 21.20
-- Versi server: 10.4.14-MariaDB
-- Versi PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rentloyo`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `email` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`email`, `username`, `password`) VALUES
('ryzal@gmail.com', 'Ryzal Fadhillah', 'ryzal'),
('viery@gmail.com', 'Viery Abdirohman', 'viery');

-- --------------------------------------------------------

--
-- Struktur dari tabel `motor`
--

CREATE TABLE `motor` (
  `nopol` varchar(10) NOT NULL,
  `merk` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `tahun` year(4) NOT NULL,
  `harga` int(50) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `motor`
--

INSERT INTO `motor` (`nopol`, `merk`, `type`, `tahun`, `harga`, `status`) VALUES
('AB 01 AA', 'Honda', 'Vario', 2018, 80000, 'ada'),
('AB 02 AB', 'Yamaha', 'Mio', 2015, 50000, 'ada'),
('AB 03 AC', 'Honda', 'Beat', 2017, 60000, 'ada'),
('AB 04 AD', 'Suzuki', 'Nex', 2019, 75000, 'ada'),
('AB 05 AE', 'Honda', 'Revo', 2015, 50000, 'ada'),
('AB 06 AF', 'Yamaha', 'FreeGo', 2020, 75000, 'ada'),
('AB 07 AG', 'Honda', 'Vario', 2020, 80000, 'ada');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` int(10) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `nik` bigint(100) NOT NULL,
  `noTelp` bigint(50) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `nopol` varchar(10) NOT NULL,
  `durasi` int(2) NOT NULL,
  `harga` int(20) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `nama`, `nik`, `noTelp`, `alamat`, `nopol`, `durasi`, `harga`, `status`) VALUES
(21, 'Doni Salmanan', 3211455689997222, 852877876565, 'Jakarta', 'AB 01 AA', 7, 560000, 'selesai'),
(22, 'Joe Biden', 1111222233334444, 81111112222, 'New York', 'AB 01 AA', 2, 160000, 'selesai'),
(23, 'Uus Dahlia', 321145337655, 82276117834, 'Jakarta Kidul', 'AB 03 AC', 3, 180000, 'selesai');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`email`);

--
-- Indeks untuk tabel `motor`
--
ALTER TABLE `motor`
  ADD PRIMARY KEY (`nopol`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
