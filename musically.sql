-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 28, 2021 at 12:01 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `musically`
--

-- --------------------------------------------------------

--Table structure for table `albums`
--

CREATE TABLE `albums` (
  `ID_ALBUMS` int(11) NOT NULL,
  `NAME` varchar(250) NOT NULL,
  `GENRE` varchar(200) DEFAULT NULL,
  `ID_ARTIST` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- 

-- --------------------------------------------------------

--
-- Table structure for table `artists`
--

CREATE TABLE `artists` (
  `ID_ARTIST` int(11) NOT NULL,
  `NAME` varchar(200) NOT NULL,
  `BIO` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `charts`
--

CREATE TABLE `charts` (
  `ID_CHARTS` int(11) NOT NULL,
  `ID_SONGS` int(11) DEFAULT NULL,
  `NAME` varchar(300) NOT NULL,
  `LINK` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `charts`
--

INSERT INTO `charts` (`ID_CHARTS`, `ID_SONGS`, `NAME`, `LINK`) VALUES
(1, 1, 'Taylor Swift - All Too Well: The Short Film', 'https://www.youtube.com/video/tollGa3S0o8'),
(2, 2, 'Adele - To Be Loved (Official Lyric Video)', 'https://www.youtube.com/video/_dlExeOyFh4'),
(3, 3, 'BTS (방탄소년단) Butter @ 2021 American Music Awards', 'https://www.youtube.com/video/AcXio81i9TE'),
(4, 4, 'Adele - Easy On Me (Live at the NRJ Awards 2021)', 'https://www.youtube.com/video/ffcitRgiNDs'),
(5, 5, 'Adele - Easy On Me (Official Lyric Video)', 'https://www.youtube.com/video/X-yIEMduRXk'),
(6, 6, 'Anuel AA, Myke Towers &amp; Jhay Cortez - Súbelo (Video Oficial)', 'https://www.youtube.com/video/J4_W-R3iPJ8'),
(7, 7, 'Anuel AA - Real Hasta La Muerte (Audio Oficial)', 'https://www.youtube.com/video/OcBk8Lv0XK8'),
(8, 8, 'Anuel AA- McGregor (Audio Oficial)', 'https://www.youtube.com/video/vDa3mlC_oKg'),
(9, 9, 'Coldplay X BTS - My Universe (Live at the AMAs)', 'https://www.youtube.com/video/9L3aw9PfDpw'),
(10, 10, 'Trueno - DANCE CRIP (Video Oficial)', 'https://www.youtube.com/video/JWRlTezTF2k'),
(11, 11, 'The Weeknd - Die For You (Official Music Video)', 'https://www.youtube.com/video/uPD0QOGTmMI'),
(12, 12, 'DUKI - Midtown (Video Oficial)', 'https://www.youtube.com/video/rSEd7vZo6Lk'),
(13, 13, 'Taylor Swift ft. Chris Stapleton - I Bet You Think About Me (Taylors Version) (Officia...', 'https://www.youtube.com/video/5UMCrq-bBCg'),
(14, 14, 'TWICE “SCIENTIST” M/V', 'https://www.youtube.com/video/vPwaXytZcgI'),
(15, 15, '[MV] 화사 (Hwa Sa) - Im a 빛', 'https://www.youtube.com/video/s1kwS3riCQI'),
(16, 16, 'Anuel AA - North Carolina (Audio Oficial)', 'https://www.youtube.com/video/ayJM--593qw'),
(17, 17, 'ROSALÍA - LA FAMA (Official Video) ft. The Weeknd', 'https://www.youtube.com/video/e-CEd6xrRQc'),
(18, 18, 'Reik, Maria Becerra - Los Tragos', 'https://www.youtube.com/video/-15biKXlKiA'),
(19, 19, 'DUKI - YIN YAN ft. Rels B (Lyric Video)', 'https://www.youtube.com/video/V__VGHK5ll4'),
(20, 20, 'DUKI - En Movimiento (Video Oficial)', 'https://www.youtube.com/video/VLrVsKhYY74'),
(21, 21, 'Post Malone, The Weeknd - One Right Now', 'https://www.youtube.com/video/Tc0tLGWIqxA'),
(22, 22, 'TINI, L-Gante - Bar (Official Video)', 'https://www.youtube.com/video/0f3ZHuC-l0c'),
(23, 23, 'DON OMAR x NIO GARCIA - SE MENEA (Official Music Video)', 'https://www.youtube.com/video/kAscra-tuwA'),
(24, 24, 'Kusu Kusu Song Ft Nora Fatehi | Satyameva Jayate 2 | John A, Divya K | Tanishk B, Zahrah Khan, Dev N', 'https://www.youtube.com/video/RgzLnmTaCAU'),
(25, 25, 'Eladio Carrión, Jay Wheeler - Alejarme de Ti (Video Oficial)', 'https://www.youtube.com/video/URJLPchso7o'),
(26, 26, 'Kenia OS - La Noche (Video Oficial)', 'https://www.youtube.com/video/ydsESbGrV5c'),
(27, 27, 'SATISFY - Official Music Video | Sidhu Moose Wala | Shooter Kahlon | New Punjabi Songs 2021', 'https://www.youtube.com/video/QqLyyDgLNdE'),
(28, 28, 'Sech, DJ Khaled - Borracho (Video Oficial)', 'https://www.youtube.com/video/BUGu-zmhVW8'),
(29, 29, 'Tum Tum - Video Song | Enemy (Tamil) | Vishal,Arya | Anand Shankar | Vinod Kumar | Thaman S', 'https://www.youtube.com/video/tYSrY4iPX6w'),
(30, 30, 'Olivia Rodrigo - traitor (Live From The American Music Awards/2021)', 'https://www.youtube.com/video/ngG2XKJ_y1Q'),
(31, 31, 'Tip Tip Song: Sooryavanshi | Akshay Kumar, Katrina Kaif | Udit N, Alka Y, Tanishk | Rohit Shetty', 'https://www.youtube.com/video/l9u8Zb4fY1c'),
(32, 32, 'Soolking feat RimK - Lela [Clip Officiel]', 'https://www.youtube.com/video/1wExNI2HKzE'),
(33, 33, 'Ñejo x Nicky Jam x Silvestre Dangond - Muy Feliz (Remix) [Official Video]', 'https://www.youtube.com/video/Bsr1qL3sv9w'),
(34, 34, 'Costi ❎ Vladuta Lupau ❎ Jador - MARE E LUMEA 🇲🇩 Cream ❎ Minodora ❎ Diana Bucsa - ROMANIA MEA', 'https://www.youtube.com/video/YcPpVlvflUE'),
(35, 35, 'GAYLE - ​abcdefu (Lyrics)', 'https://www.youtube.com/video/P-xLeVHEqEg'),
(36, 36, 'Bruno Mars, Anderson .Paak, Silk Sonic - Smokin Out The Window [Official Music Video]', 'https://www.youtube.com/video/GG7fLOmlhYg'),
(37, 37, 'Pyaar Karte Ho Na (Video) Javed-Mohsin | Stebin B, Shreya G | Mohsin Khan, Jasmin Bhasin | Danish S', 'https://www.youtube.com/video/G_cPeALjy0s'),
(38, 38, 'Bruno Mars &amp; Anderson .Paak as Silk Sonic - Smokin Out The Window (LIVE American Music Awards 2021)', 'https://www.youtube.com/video/nAUwKeO93bY'),
(39, 39, 'MYA, Emilia - BB (Official Video)', 'https://www.youtube.com/video/BzopX5Um8S0'),
(40, 40, 'Adele - My Little Love (Official Lyric Video)', 'https://www.youtube.com/video/zIF70l1zUKU'),
(41, 41, 'Leo Rizzi, Danny Ocean - Amapolas Remix (Videoclip Oficial)', 'https://www.youtube.com/video/INEbRe0MWf4'),
(42, 42, 'Najaa (Full Song) | Sooryavanshi | Akshay Kumar,Katrina Kaif,Rohit Shetty,Tanishk,Pav Dharia,Nikhita', 'https://www.youtube.com/video/cX6g4cbVBwI'),
(43, 43, 'Люся Чеботина - Солнце Монако (ПРЕМЬЕРА КЛИПА)', 'https://www.youtube.com/video/sfd2xj9xtN0'),
(44, 44, 'Janani Video Song (Telugu) - RRR - MM Keeravaani | NTR, Ram Charan, Ajay Devgn, Alia | SS Rajamouli', 'https://www.youtube.com/video/xdpJWh5u-EI'),
(45, 45, 'Janani Video Song (Hindi) - RRR - M M Kreem | NTR, Ram Charan, Ajay Devgn, Alia Bhatt | SS Rajamouli', 'https://www.youtube.com/video/WEBvHPvf0hM'),
(46, 46, '2 Raflaan (Full Video) Mankirt Aulakh Ft Gurlez Akhtar | Shree Brar | Desi Crew | New Punjabi Songs', 'https://www.youtube.com/video/XHvnISYuONc'),
(47, 47, 'Ramy Sabry - Hamoot Men Gherha [Lyrics video] | رامي صبري - هموت من غيرها', 'https://www.youtube.com/video/3iYmadWIgDs'),
(48, 48, 'Ángela Aguilar, Jesse &amp; Joy - Ella Qué Te Dio (Video Oficial)', 'https://www.youtube.com/video/6pIQ-XoouAs'),
(49, 49, 'DUKI - BICI ft. AK4:20, Juhn, Juanka (Video Lyric)', 'https://www.youtube.com/video/wMwbkYGpSYU'),
(50, 50, 'Boom Boom | Yo Yo Honey Singh feat. Hommie Dilliwala | Full Video | 4K', 'https://www.youtube.com/video/yHg7kTnQCiA'),
(51, 51, 'Zé Felipe e MC Danny - Toma Toma Vapo Vapo (Videoclipe Oficial)', 'https://www.youtube.com/video/ey6H_cBy-OQ'),
(52, 52, 'عمر كمال وعبد الباسط حمودة واوكسانا | ضيعنا | Omar Kamal &amp; Abdelbaset Hamouda | Dai3na', 'https://www.youtube.com/video/OgvaE5C-w2c'),
(53, 53, 'Miyagi &amp; Andy Panda feat. Mav-d - Marmalade (Official Audio)', 'https://www.youtube.com/video/6aTYceNFDP4'),
(54, 54, 'ЕГОР ШИП - Твой любимый трек (Премьера клипа, 2021)', 'https://www.youtube.com/video/DHE-lMgFLo8'),
(55, 55, 'WOS ft Ricardo Mollo - CULPA (Video Oficial)', 'https://www.youtube.com/video/XzxN55XwhdU'),
(56, 56, 'HammAli &amp; Navai, Jah Khalib – Боже, как завидую', 'https://www.youtube.com/video/Dxc5BVV7kzs'),
(57, 57, 'Dekhi Jau (Full Video) Gur Sidhu | Gurlez Akhtar | Latest Punjabi Songs 2021 | New Punjabi Song 2021', 'https://www.youtube.com/video/PYfO5NsLmGc'),
(58, 58, 'NJ - PANIPAALI-2 (Official Music Video) | Prod. by Arcado | Spacemarley', 'https://www.youtube.com/video/hth250mmc6k'),
(59, 59, '中島美嘉 - 僕が死のうと思ったのは / THE FIRST TAKE', 'https://www.youtube.com/video/AabAbM_Lmuo'),
(60, 60, 'Ислам Итляшев и Ирина Круг - Москва - Владивосток | Премьера клипа 2021', 'https://www.youtube.com/video/ty4c2ehCYiQ'),
(61, 61, 'Voice of Unity Lyric Video | Maanaadu | Silambarasan TR | Yuvan Shankar Raja | Arivu | Venkat Prabhu', 'https://www.youtube.com/video/P1QvwQBodeI'),
(62, 62, 'Ryan Castro, Feid - Monastery (Vídeo Oficial)', 'https://www.youtube.com/video/EOkCOYrPEsU'),
(63, 63, 'MERO - Double Cup (prod. by Juh-Dee &amp; Young Mesh) [Official Video]', 'https://www.youtube.com/video/58qSiQUq55I'),
(64, 64, 'Janani Video Song (Telugu) - RRR - MM Keeravaani | NTR, Ram Charan, Ajay Devgn, Alia | SS Rajamouli', 'https://www.youtube.com/video/Z1iB2zpFGCk'),
(65, 65, 'Rahiye Hasde (Full Video) | Khan Bhaini | Sycostyle | Latest Punjabi Song 2021 | New punjabi song', 'https://www.youtube.com/video/mcU5t02LmUk'),
(66, 66, 'Eladio Carrión , Beny JR - REDBULL (Video Oficial)', 'https://www.youtube.com/video/lA0INn4zNlk'),
(67, 67, 'Camila — Mientes [Letra]', 'https://www.youtube.com/video/PO3_695dON0'),
(68, 68, 'INSTASAMKA - ХЛОПАЙ (prod. realmoneyken)', 'https://www.youtube.com/video/dr824V3uQ0c'),
(69, 69, 'Lacrim - Kanun', 'https://www.youtube.com/video/ZfdtyHlXebY'),
(70, 70, 'Taylor Swift - Red (Taylors Version) (Lyric Video)', 'https://www.youtube.com/video/R_rUYuFtNO4'),
(71, 71, 'LA MANO DE D10S 420 - RODRIGO X L-GANTE (HOMENAJE AL D1EG0 Y AL POTRO) @DTBILARDO @ERICSANTANA', 'https://www.youtube.com/video/Wd3RYg1yATg'),
(72, 72, 'Eyy Bidda Idhi Naa Adda Lyrical | Pushpa Songs | Allu Arjun, Rashmika | DSP | Nakash Aziz', 'https://www.youtube.com/video/pHHig1XBML0'),
(73, 73, 'Feid, Mora, Eladio Carrion - FUMETEO (Remix)', 'https://www.youtube.com/video/ELXWePppuh4'),
(74, 74, 'Ingratax - Las de Octubre (Official Video)', 'https://www.youtube.com/video/5ksJn29AEgQ'),
(75, 75, 'Foufa Torino X Djalil Palermo - Abra Cadabra (Official Music Video)', 'https://www.youtube.com/video/td2L1H3GTRA'),
(76, 76, 'Хабиб, Galibri &amp; Mavik - Дискотанцы (Премьера клипа, 2021)', 'https://www.youtube.com/video/9tdpHG3M3II'),
(77, 77, 'Jador - Fidel (feat. @Mocanu Bogdan ✘ @Nikolas Sax) | Official Video', 'https://www.youtube.com/video/YQjLoQVOsNw'),
(78, 78, 'Maes - Tmax 560', 'https://www.youtube.com/video/1b6yNApFg_8'),
(79, 79, 'JEON SOMI (전소미) - XOXO M/V', 'https://www.youtube.com/video/H8kqPkEXP_E'),
(80, 80, 'Didine Canon 16 - Doza (Official Music Video)', 'https://www.youtube.com/video/AdMlC5D0nL8'),
(81, 81, 'Polo G - Bad Man (Smooth Criminal) [Official Video]', 'https://www.youtube.com/video/gxXCfSN3YYY'),
(82, 82, 'WOS ft Nicki Nicole - CAMBIANDO LA PIEL (Concept Video)', 'https://www.youtube.com/video/F9iF58hk6xY'),
(83, 83, 'Los Ángeles Azules, NICKI NICOLE - Otra Noche', 'https://www.youtube.com/video/EOBE_uBSGYw'),
(84, 84, 'Tayc - DODO', 'https://www.youtube.com/video/NrdhxCM3aAY'),
(85, 85, 'Dani Mocanu - Vulturul si Porumbeii | special guest @Marius Csampar | Official Video', 'https://www.youtube.com/video/ow4BWOvBVEM'),
(86, 86, 'Magizhini | Anagha | Gouri GK | Govind Vasantha | Madhan Karky | VG Bala | First Tamil LGBTQ Song', 'https://www.youtube.com/video/_9fA29V3MB0'),
(87, 87, 'MONSTA X 몬스타엑스 Rush Hour MV', 'https://www.youtube.com/video/qRdTyoZd3rg'),
(88, 88, 'HammAli x Navai x Jah Khalib – Боже, как завидую', 'https://www.youtube.com/video/JPJ8HjHqIt0'),
(89, 89, 'غريب ال مخلص - فرصة سعيدة (حصرياً) | 2021', 'https://www.youtube.com/video/R2D9IDJkAG4'),
(90, 90, 'FMK, Tiago PZK, Mau y Ricky - Prende La Cámara RMX (Official Video)', 'https://www.youtube.com/video/2x6F-uUa1mI'),
(91, 91, 'ماجد المهندس - من يشبهك ( حصريا ) | 2021', 'https://www.youtube.com/video/XTWK7mQFraM'),
(92, 92, 'Миша Марвин - Полюбил такую (Премьера трека, 2021)', 'https://www.youtube.com/video/7JomdECGfjo'),
(93, 93, 'DESIRES - AP DHILLON | GURINDER GILL', 'https://www.youtube.com/video/3ONzh3tf884'),
(94, 94, 'Лолита &amp; Коста Лакоста — По-другому (Премьера клипа, 2021)', 'https://www.youtube.com/video/VOLqsrDGRcM'),
(95, 95, 'Maymay Entratas jawdropping Amakabogera performance | ASAP Natin To', 'https://www.youtube.com/video/jTRWBSHyVwA'),
(96, 96, '關於那一道墻的故事... 黃明志金門觀光主題曲【牆外】Ft. 小花 @鬼才做音樂 2021 Ghosician', 'https://www.youtube.com/video/WCLlFA9SiDI'),
(97, 97, 'GAYAZOV$ BROTHER$ — МАЛИНОВАЯ ЛАДА (ПРЕМЬЕРА КЛИПА 2021)', 'https://www.youtube.com/video/TvEkldfa3T0'),
(98, 98, 'OrelSan - Lodeur de lessence [CLIP OFFICIEL]', 'https://www.youtube.com/video/zFknl7OAV0c'),
(99, 99, 'Ee Raathale Lyrical Video Song | Radhe Shyam | Prabhas,Pooja Hegde | Justin Prabhakaran | Krishna K', 'https://www.youtube.com/video/vHuBCcm7KC8'),
(100, 100, 'بدر العزي - كلمني ( حصرياً ) 2021', 'https://www.youtube.com/video/BJR3_GHxkEY');

-- --------------------------------------------------------

--
-- Table structure for table `favorites`
--

CREATE TABLE `favorites` (
  `ID_SONGS` int(11) NOT NULL,
  `ID_ARTIST` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `playlists`
--

CREATE TABLE `playlists` (
  `ID_PLAYLIST` int(11) NOT NULL,
  `ID_SONGS` int(11) DEFAULT NULL,
  `ID_ALBUM` int(11) DEFAULT NULL,
  `NAME` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `GENRE` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `SONGS_IDs` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `playlists`
--

INSERT INTO `playlists` (`ID_PLAYLIST`, `ID_SONGS`, `ID_ALBUM`, `NAME`, `GENRE`, `SONGS_IDs`) VALUES
(325, NULL, NULL, 'The best songs', 'Pop', '1, 2, 5, 8, 16'),
(326, NULL, NULL, 'Another playlist', 'Hip Hop', '23, 34, 2, 56, 90'),
(327, NULL, NULL, 'A playlist', 'Trends', '1, 2, 5, 7, 9');

-- --------------------------------------------------------

--
-- Table structure for table `songs`
--

CREATE TABLE `songs` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(200) NOT NULL,
  `NAME_ARTIST` varchar(200) NOT NULL,
  `NAME_ALBUM` varchar(200) NOT NULL,
  `GENRE` varchar(200) DEFAULT NULL,
  `LINK` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `songs`
--

INSERT INTO `songs` (`ID`, `NAME`, `NAME_ARTIST`, `NAME_ALBUM`, `GENRE`, `LINK`) VALUES
(1, 'Taylor Swift - All Too Well: The Short Film', ' ', ' ', ' ', 'https://www.youtube.com/video/tollGa3S0o8'),
(2, 'Adele - To Be Loved (Official Lyric Video)', ' ', ' ', ' ', 'https://www.youtube.com/video/_dlExeOyFh4'),
(3, 'BTS (방탄소년단) Butter @ 2021 American Music Awards', ' ', ' ', ' ', 'https://www.youtube.com/video/AcXio81i9TE'),
(4, 'Adele - Easy On Me (Live at the NRJ Awards 2021)', ' ', ' ', ' ', 'https://www.youtube.com/video/ffcitRgiNDs'),
(5, 'Adele - Easy On Me (Official Lyric Video)', ' ', ' ', ' ', 'https://www.youtube.com/video/X-yIEMduRXk'),
(6, 'Anuel AA, Myke Towers &amp; Jhay Cortez - Súbelo (Video Oficial)', ' ', ' ', ' ', 'https://www.youtube.com/video/J4_W-R3iPJ8'),
(7, 'Anuel AA - Real Hasta La Muerte (Audio Oficial)', ' ', ' ', ' ', 'https://www.youtube.com/video/OcBk8Lv0XK8'),
(8, 'Anuel AA- McGregor (Audio Oficial)', ' ', ' ', ' ', 'https://www.youtube.com/video/vDa3mlC_oKg'),
(9, 'Coldplay X BTS - My Universe (Live at the AMAs)', ' ', ' ', ' ', 'https://www.youtube.com/video/9L3aw9PfDpw'),
(10, 'Trueno - DANCE CRIP (Video Oficial)', ' ', ' ', ' ', 'https://www.youtube.com/video/JWRlTezTF2k'),
(11, 'The Weeknd - Die For You (Official Music Video)', ' ', ' ', ' ', 'https://www.youtube.com/video/uPD0QOGTmMI'),
(12, 'DUKI - Midtown (Video Oficial)', ' ', ' ', ' ', 'https://www.youtube.com/video/rSEd7vZo6Lk'),
(13, 'Taylor Swift ft. Chris Stapleton - I Bet You Think About Me (Taylors Version) (Officia...', ' ', ' ', ' ', 'https://www.youtube.com/video/5UMCrq-bBCg'),
(14, 'TWICE “SCIENTIST” M/V', ' ', ' ', ' ', 'https://www.youtube.com/video/vPwaXytZcgI'),
(15, '[MV] 화사 (Hwa Sa) - Im a 빛', ' ', ' ', ' ', 'https://www.youtube.com/video/s1kwS3riCQI'),
(16, 'Anuel AA - North Carolina (Audio Oficial)', ' ', ' ', ' ', 'https://www.youtube.com/video/ayJM--593qw'),
(17, 'ROSALÍA - LA FAMA (Official Video) ft. The Weeknd', ' ', ' ', ' ', 'https://www.youtube.com/video/e-CEd6xrRQc'),
(18, 'Reik, Maria Becerra - Los Tragos', ' ', ' ', ' ', 'https://www.youtube.com/video/-15biKXlKiA'),
(19, 'DUKI - YIN YAN ft. Rels B (Lyric Video)', ' ', ' ', ' ', 'https://www.youtube.com/video/V__VGHK5ll4'),
(20, 'DUKI - En Movimiento (Video Oficial)', ' ', ' ', ' ', 'https://www.youtube.com/video/VLrVsKhYY74'),
(21, 'Post Malone, The Weeknd - One Right Now', ' ', ' ', ' ', 'https://www.youtube.com/video/Tc0tLGWIqxA'),
(22, 'TINI, L-Gante - Bar (Official Video)', ' ', ' ', ' ', 'https://www.youtube.com/video/0f3ZHuC-l0c'),
(23, 'DON OMAR x NIO GARCIA - SE MENEA (Official Music Video)', ' ', ' ', ' ', 'https://www.youtube.com/video/kAscra-tuwA'),
(24, 'Kusu Kusu Song Ft Nora Fatehi | Satyameva Jayate 2 | John A, Divya K | Tanishk B, Zahrah Khan, Dev N', ' ', ' ', ' ', 'https://www.youtube.com/video/RgzLnmTaCAU'),
(25, 'Eladio Carrión, Jay Wheeler - Alejarme de Ti (Video Oficial)', ' ', ' ', ' ', 'https://www.youtube.com/video/URJLPchso7o'),
(26, 'Kenia OS - La Noche (Video Oficial)', ' ', ' ', ' ', 'https://www.youtube.com/video/ydsESbGrV5c'),
(27, 'SATISFY - Official Music Video | Sidhu Moose Wala | Shooter Kahlon | New Punjabi Songs 2021', ' ', ' ', ' ', 'https://www.youtube.com/video/QqLyyDgLNdE'),
(28, 'Sech, DJ Khaled - Borracho (Video Oficial)', ' ', ' ', ' ', 'https://www.youtube.com/video/BUGu-zmhVW8'),
(29, 'Tum Tum - Video Song | Enemy (Tamil) | Vishal,Arya | Anand Shankar | Vinod Kumar | Thaman S', ' ', ' ', ' ', 'https://www.youtube.com/video/tYSrY4iPX6w'),
(30, 'Olivia Rodrigo - traitor (Live From The American Music Awards/2021)', ' ', ' ', ' ', 'https://www.youtube.com/video/ngG2XKJ_y1Q'),
(31, 'Tip Tip Song: Sooryavanshi | Akshay Kumar, Katrina Kaif | Udit N, Alka Y, Tanishk | Rohit Shetty', ' ', ' ', ' ', 'https://www.youtube.com/video/l9u8Zb4fY1c'),
(32, 'Soolking feat RimK - Lela [Clip Officiel]', ' ', ' ', ' ', 'https://www.youtube.com/video/1wExNI2HKzE'),
(33, 'Ñejo x Nicky Jam x Silvestre Dangond - Muy Feliz (Remix) [Official Video]', ' ', ' ', ' ', 'https://www.youtube.com/video/Bsr1qL3sv9w'),
(34, 'Costi ❎ Vladuta Lupau ❎ Jador - MARE E LUMEA 🇲🇩 Cream ❎ Minodora ❎ Diana Bucsa - ROMANIA MEA', ' ', ' ', ' ', 'https://www.youtube.com/video/YcPpVlvflUE'),
(35, 'GAYLE - ​abcdefu (Lyrics)', ' ', ' ', ' ', 'https://www.youtube.com/video/P-xLeVHEqEg'),
(36, 'Bruno Mars, Anderson .Paak, Silk Sonic - Smokin Out The Window [Official Music Video]', ' ', ' ', ' ', 'https://www.youtube.com/video/GG7fLOmlhYg'),
(37, 'Pyaar Karte Ho Na (Video) Javed-Mohsin | Stebin B, Shreya G | Mohsin Khan, Jasmin Bhasin | Danish S', ' ', ' ', ' ', 'https://www.youtube.com/video/G_cPeALjy0s'),
(38, 'Bruno Mars &amp; Anderson .Paak as Silk Sonic - Smokin Out The Window (LIVE American Music Awards 2021)', ' ', ' ', ' ', 'https://www.youtube.com/video/nAUwKeO93bY'),
(39, 'MYA, Emilia - BB (Official Video)', ' ', ' ', ' ', 'https://www.youtube.com/video/BzopX5Um8S0'),
(40, 'Adele - My Little Love (Official Lyric Video)', ' ', ' ', ' ', 'https://www.youtube.com/video/zIF70l1zUKU'),
(41, 'Leo Rizzi, Danny Ocean - Amapolas Remix (Videoclip Oficial)', ' ', ' ', ' ', 'https://www.youtube.com/video/INEbRe0MWf4'),
(42, 'Najaa (Full Song) | Sooryavanshi | Akshay Kumar,Katrina Kaif,Rohit Shetty,Tanishk,Pav Dharia,Nikhita', ' ', ' ', ' ', 'https://www.youtube.com/video/cX6g4cbVBwI'),
(43, 'Люся Чеботина - Солнце Монако (ПРЕМЬЕРА КЛИПА)', ' ', ' ', ' ', 'https://www.youtube.com/video/sfd2xj9xtN0'),
(44, 'Janani Video Song (Telugu) - RRR - MM Keeravaani | NTR, Ram Charan, Ajay Devgn, Alia | SS Rajamouli', ' ', ' ', ' ', 'https://www.youtube.com/video/xdpJWh5u-EI'),
(45, 'Janani Video Song (Hindi) - RRR - M M Kreem | NTR, Ram Charan, Ajay Devgn, Alia Bhatt | SS Rajamouli', ' ', ' ', ' ', 'https://www.youtube.com/video/WEBvHPvf0hM'),
(46, '2 Raflaan (Full Video) Mankirt Aulakh Ft Gurlez Akhtar | Shree Brar | Desi Crew | New Punjabi Songs', ' ', ' ', ' ', 'https://www.youtube.com/video/XHvnISYuONc'),
(47, 'Ramy Sabry - Hamoot Men Gherha [Lyrics video] | رامي صبري - هموت من غيرها', ' ', ' ', ' ', 'https://www.youtube.com/video/3iYmadWIgDs'),
(48, 'Ángela Aguilar, Jesse &amp; Joy - Ella Qué Te Dio (Video Oficial)', ' ', ' ', ' ', 'https://www.youtube.com/video/6pIQ-XoouAs'),
(49, 'DUKI - BICI ft. AK4:20, Juhn, Juanka (Video Lyric)', ' ', ' ', ' ', 'https://www.youtube.com/video/wMwbkYGpSYU'),
(50, 'Boom Boom | Yo Yo Honey Singh feat. Hommie Dilliwala | Full Video | 4K', ' ', ' ', ' ', 'https://www.youtube.com/video/yHg7kTnQCiA'),
(51, 'Zé Felipe e MC Danny - Toma Toma Vapo Vapo (Videoclipe Oficial)', ' ', ' ', ' ', 'https://www.youtube.com/video/ey6H_cBy-OQ'),
(52, 'عمر كمال وعبد الباسط حمودة واوكسانا | ضيعنا | Omar Kamal &amp; Abdelbaset Hamouda | Dai3na', ' ', ' ', ' ', 'https://www.youtube.com/video/OgvaE5C-w2c'),
(53, 'Miyagi &amp; Andy Panda feat. Mav-d - Marmalade (Official Audio)', ' ', ' ', ' ', 'https://www.youtube.com/video/6aTYceNFDP4'),
(54, 'ЕГОР ШИП - Твой любимый трек (Премьера клипа, 2021)', ' ', ' ', ' ', 'https://www.youtube.com/video/DHE-lMgFLo8'),
(55, 'WOS ft Ricardo Mollo - CULPA (Video Oficial)', ' ', ' ', ' ', 'https://www.youtube.com/video/XzxN55XwhdU'),
(56, 'HammAli &amp; Navai, Jah Khalib – Боже, как завидую', ' ', ' ', ' ', 'https://www.youtube.com/video/Dxc5BVV7kzs'),
(57, 'Dekhi Jau (Full Video) Gur Sidhu | Gurlez Akhtar | Latest Punjabi Songs 2021 | New Punjabi Song 2021', ' ', ' ', ' ', 'https://www.youtube.com/video/PYfO5NsLmGc'),
(58, 'NJ - PANIPAALI-2 (Official Music Video) | Prod. by Arcado | Spacemarley', ' ', ' ', ' ', 'https://www.youtube.com/video/hth250mmc6k'),
(59, '中島美嘉 - 僕が死のうと思ったのは / THE FIRST TAKE', ' ', ' ', ' ', 'https://www.youtube.com/video/AabAbM_Lmuo'),
(60, 'Ислам Итляшев и Ирина Круг - Москва - Владивосток | Премьера клипа 2021', ' ', ' ', ' ', 'https://www.youtube.com/video/ty4c2ehCYiQ'),
(61, 'Voice of Unity Lyric Video | Maanaadu | Silambarasan TR | Yuvan Shankar Raja | Arivu | Venkat Prabhu', ' ', ' ', ' ', 'https://www.youtube.com/video/P1QvwQBodeI'),
(62, 'Ryan Castro, Feid - Monastery (Vídeo Oficial)', ' ', ' ', ' ', 'https://www.youtube.com/video/EOkCOYrPEsU'),
(63, 'MERO - Double Cup (prod. by Juh-Dee &amp; Young Mesh) [Official Video]', ' ', ' ', ' ', 'https://www.youtube.com/video/58qSiQUq55I'),
(65, 'Rahiye Hasde (Full Video) | Khan Bhaini | Sycostyle | Latest Punjabi Song 2021 | New punjabi song', ' ', ' ', ' ', 'https://www.youtube.com/video/mcU5t02LmUk'),
(66, 'Eladio Carrión , Beny JR - REDBULL (Video Oficial)', ' ', ' ', ' ', 'https://www.youtube.com/video/lA0INn4zNlk'),
(67, 'Camila — Mientes [Letra]', ' ', ' ', ' ', 'https://www.youtube.com/video/PO3_695dON0'),
(68, 'INSTASAMKA - ХЛОПАЙ (prod. realmoneyken)', ' ', ' ', ' ', 'https://www.youtube.com/video/dr824V3uQ0c'),
(69, 'Lacrim - Kanun', ' ', ' ', ' ', 'https://www.youtube.com/video/ZfdtyHlXebY'),
(70, 'Taylor Swift - Red (Taylors Version) (Lyric Video)', ' ', ' ', ' ', 'https://www.youtube.com/video/R_rUYuFtNO4'),
(71, 'LA MANO DE D10S 420 - RODRIGO X L-GANTE (HOMENAJE AL D1EG0 Y AL POTRO) @DTBILARDO @ERICSANTANA', ' ', ' ', ' ', 'https://www.youtube.com/video/Wd3RYg1yATg'),
(72, 'Eyy Bidda Idhi Naa Adda Lyrical | Pushpa Songs | Allu Arjun, Rashmika | DSP | Nakash Aziz', ' ', ' ', ' ', 'https://www.youtube.com/video/pHHig1XBML0'),
(73, 'Feid, Mora, Eladio Carrion - FUMETEO (Remix)', ' ', ' ', ' ', 'https://www.youtube.com/video/ELXWePppuh4'),
(74, 'Ingratax - Las de Octubre (Official Video)', ' ', ' ', ' ', 'https://www.youtube.com/video/5ksJn29AEgQ'),
(75, 'Foufa Torino X Djalil Palermo - Abra Cadabra (Official Music Video)', ' ', ' ', ' ', 'https://www.youtube.com/video/td2L1H3GTRA'),
(76, 'Хабиб, Galibri &amp; Mavik - Дискотанцы (Премьера клипа, 2021)', ' ', ' ', ' ', 'https://www.youtube.com/video/9tdpHG3M3II'),
(77, 'Jador - Fidel (feat. @Mocanu Bogdan ✘ @Nikolas Sax) | Official Video', ' ', ' ', ' ', 'https://www.youtube.com/video/YQjLoQVOsNw'),
(78, 'Maes - Tmax 560', ' ', ' ', ' ', 'https://www.youtube.com/video/1b6yNApFg_8'),
(79, 'JEON SOMI (전소미) - XOXO M/V', ' ', ' ', ' ', 'https://www.youtube.com/video/H8kqPkEXP_E'),
(80, 'Didine Canon 16 - Doza (Official Music Video)', ' ', ' ', ' ', 'https://www.youtube.com/video/AdMlC5D0nL8'),
(81, 'Polo G - Bad Man (Smooth Criminal) [Official Video]', ' ', ' ', ' ', 'https://www.youtube.com/video/gxXCfSN3YYY'),
(82, 'WOS ft Nicki Nicole - CAMBIANDO LA PIEL (Concept Video)', ' ', ' ', ' ', 'https://www.youtube.com/video/F9iF58hk6xY'),
(83, 'Los Ángeles Azules, NICKI NICOLE - Otra Noche', ' ', ' ', ' ', 'https://www.youtube.com/video/EOBE_uBSGYw'),
(84, 'Tayc - DODO', ' ', ' ', ' ', 'https://www.youtube.com/video/NrdhxCM3aAY'),
(85, 'Dani Mocanu - Vulturul si Porumbeii | special guest @Marius Csampar | Official Video', ' ', ' ', ' ', 'https://www.youtube.com/video/ow4BWOvBVEM'),
(86, 'Magizhini | Anagha | Gouri GK | Govind Vasantha | Madhan Karky | VG Bala | First Tamil LGBTQ Song', ' ', ' ', ' ', 'https://www.youtube.com/video/_9fA29V3MB0'),
(87, 'MONSTA X 몬스타엑스 Rush Hour MV', ' ', ' ', ' ', 'https://www.youtube.com/video/qRdTyoZd3rg'),
(88, 'HammAli x Navai x Jah Khalib – Боже, как завидую', ' ', ' ', ' ', 'https://www.youtube.com/video/JPJ8HjHqIt0'),
(89, 'غريب ال مخلص - فرصة سعيدة (حصرياً) | 2021', ' ', ' ', ' ', 'https://www.youtube.com/video/R2D9IDJkAG4'),
(90, 'FMK, Tiago PZK, Mau y Ricky - Prende La Cámara RMX (Official Video)', ' ', ' ', ' ', 'https://www.youtube.com/video/2x6F-uUa1mI'),
(91, 'ماجد المهندس - من يشبهك ( حصريا ) | 2021', ' ', ' ', ' ', 'https://www.youtube.com/video/XTWK7mQFraM'),
(92, 'Миша Марвин - Полюбил такую (Премьера трека, 2021)', ' ', ' ', ' ', 'https://www.youtube.com/video/7JomdECGfjo'),
(93, 'DESIRES - AP DHILLON | GURINDER GILL', ' ', ' ', ' ', 'https://www.youtube.com/video/3ONzh3tf884'),
(94, 'Лолита &amp; Коста Лакоста — По-другому (Премьера клипа, 2021)', ' ', ' ', ' ', 'https://www.youtube.com/video/VOLqsrDGRcM'),
(95, 'Maymay Entratas jawdropping Amakabogera performance | ASAP Natin To', ' ', ' ', ' ', 'https://www.youtube.com/video/jTRWBSHyVwA'),
(96, '關於那一道墻的故事... 黃明志金門觀光主題曲【牆外】Ft. 小花 @鬼才做音樂 2021 Ghosician', ' ', ' ', ' ', 'https://www.youtube.com/video/WCLlFA9SiDI'),
(97, 'GAYAZOV$ BROTHER$ — МАЛИНОВАЯ ЛАДА (ПРЕМЬЕРА КЛИПА 2021)', ' ', ' ', ' ', 'https://www.youtube.com/video/TvEkldfa3T0'),
(98, 'OrelSan - Lodeur de lessence [CLIP OFFICIEL]', ' ', ' ', ' ', 'https://www.youtube.com/video/zFknl7OAV0c'),
(99, 'Ee Raathale Lyrical Video Song | Radhe Shyam | Prabhas,Pooja Hegde | Justin Prabhakaran | Krishna K', ' ', ' ', ' ', 'https://www.youtube.com/video/vHuBCcm7KC8'),
(100, 'بدر العزي - كلمني ( حصرياً ) 2021', ' ', ' ', ' ', 'https://www.youtube.com/video/BJR3_GHxkEY');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `albums`
--
ALTER TABLE `albums`
  ADD PRIMARY KEY (`ID_ALBUMS`),
  ADD KEY `ID_ARTIST` (`ID_ARTIST`);

--
-- Indexes for table `artists`
--
ALTER TABLE `artists`
  ADD PRIMARY KEY (`ID_ARTIST`);

--
-- Indexes for table `charts`
--
ALTER TABLE `charts`
  ADD PRIMARY KEY (`ID_CHARTS`),
  ADD KEY `ID_SONGS` (`ID_SONGS`);

--
-- Indexes for table `favorites`
--
ALTER TABLE `favorites`
  ADD KEY `ID_SONGS` (`ID_SONGS`),
  ADD KEY `ID_ARTIST` (`ID_ARTIST`);

--
-- Indexes for table `playlists`
--
ALTER TABLE `playlists`
  ADD PRIMARY KEY (`ID_PLAYLIST`),
  ADD KEY `ID_SONGS` (`ID_SONGS`),
  ADD KEY `ID_ALBUM` (`ID_ALBUM`);

--
-- Indexes for table `songs`
--
ALTER TABLE `songs`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `NAME` (`NAME`),
  ADD UNIQUE KEY `LINK` (`LINK`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `albums`
--
ALTER TABLE `albums`
  MODIFY `ID_ALBUMS` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `artists`
--
ALTER TABLE `artists`
  MODIFY `ID_ARTIST` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `charts`
--
ALTER TABLE `charts`
  MODIFY `ID_CHARTS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `playlists`
--
ALTER TABLE `playlists`
  MODIFY `ID_PLAYLIST` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=328;

--
-- AUTO_INCREMENT for table `songs`
--
ALTER TABLE `songs`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=901;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `albums`
--
ALTER TABLE `albums`
  ADD CONSTRAINT `albums_ibfk_1` FOREIGN KEY (`ID_ARTIST`) REFERENCES `artists` (`ID_ARTIST`);

--
-- Constraints for table `favorites`
--
ALTER TABLE `favorites`
  ADD CONSTRAINT `favorites_ibfk_1` FOREIGN KEY (`ID_SONGS`) REFERENCES `songs` (`ID`);

--
-- Constraints for table `playlists`
--
ALTER TABLE `playlists`
  ADD CONSTRAINT `fk_id_song` FOREIGN KEY (`ID_SONGS`) REFERENCES `songs` (`ID`),
  ADD CONSTRAINT `playlists_ibfk_1` FOREIGN KEY (`ID_ALBUM`) REFERENCES `albums` (`ID_ALBUMS`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
