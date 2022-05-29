-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Май 29 2022 г., 04:56
-- Версия сервера: 8.0.19
-- Версия PHP: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `airtravel`
--

-- --------------------------------------------------------

--
-- Структура таблицы `accounts`
--

CREATE TABLE `accounts` (
  `id` int UNSIGNED NOT NULL,
  `username` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `hashPassword` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `accounts`
--

INSERT INTO `accounts` (`id`, `username`, `hashPassword`) VALUES
(1, 'Hachiko', 'BAA00CB0B34B034A9C42ED83DAB4E623F803FFD130B99B9994DF815BD87D4115D0EB113ADA3CF41BA302A37F22038137548EDDDE9A3E400FDA674F9A0618CC65'),
(2, 'Shibeko', 'C29282E5E06E8D8344208616C1B0DA828B6E86802190470BAEC6426E18AF45A5DA9F527EBC6D94F5E8C50D7D716ACA7C8AC9C846C5E67A347BFD161455327E29'),
(3, 'Romankova', 'F6F92CF1607604E056DA890E183F64BA36D9326085E5C8D3E0D7F85CFD7FD08FF170C4048CC0D6EBC66A7F3815B62560A331A1CBFF15F363A97E9A5184B4ADD7'),
(4, 'Mishchenko', '10D77506D1571D601B29D49CEB1A01AAC42F346D59BF9D51817FD4BF230DFAA631899CF53630815E86A659FDFC17D9088C0EE0135387D7D5C0F7F9D2C8DD684B'),
(5, 'Annasan', 'CB24F1D57B5AD74FE9B4186DEF253B616B7257EAD8B81CC87C9E177E569B5AF4AC0A9FDA3917F1134F025799BA02AA68F497134FCAA96C57B2DB306F74D94F2D'),
(6, 'Dimka', 'FF4A93A6BF468527B366F14A9F65C9176ABAF42DF8479572A3CE9354480CE901A3164AE29898256FCB741C75B8FD5C2498612D3C4424AE168D9E9AF8934EB599'),
(7, 'Ilnur', '9D9D17B6962CEC0DC168439B2DD62D7DE8417A4760068EF4B6AA812ACE2A3F89DB9684D49A87ED5D0A552E9702DA0D767FF400807CCFF36E72FB3EAC28AE6EF3');

-- --------------------------------------------------------

--
-- Структура таблицы `accounts_roles`
--

CREATE TABLE `accounts_roles` (
  `id_account` int UNSIGNED NOT NULL,
  `id_role` int UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `accounts_roles`
--

INSERT INTO `accounts_roles` (`id_account`, `id_role`) VALUES
(1, 1),
(4, 2),
(3, 3),
(5, 4),
(2, 5),
(6, 6);

-- --------------------------------------------------------

--
-- Структура таблицы `airports`
--

CREATE TABLE `airports` (
  `id` int UNSIGNED NOT NULL,
  `id_city` int UNSIGNED NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `airports`
--

INSERT INTO `airports` (`id`, `id_city`, `name`) VALUES
(1, 1, 'Airport Brest'),
(2, 3, 'National Airport Minsk'),
(3, 2, 'Brest Bretagne Airport'),
(4, 4, 'Vnukovo International Airport'),
(5, 4, 'Zhukovsky International Airport'),
(6, 4, 'Moscow Domodedovo Mikhail Lomonosov Airport'),
(7, 4, 'Sheremetyevo - A.S. Pushkin international airport');

-- --------------------------------------------------------

--
-- Дублирующая структура для представления `airports_view`
-- (См. Ниже фактическое представление)
--
CREATE TABLE `airports_view` (
`id` int unsigned
,`country` varchar(255)
,`city` varchar(255)
,`airport` varchar(255)
);

-- --------------------------------------------------------

--
-- Структура таблицы `cities`
--

CREATE TABLE `cities` (
  `id` int UNSIGNED NOT NULL,
  `id_country` int UNSIGNED NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `cities`
--

INSERT INTO `cities` (`id`, `id_country`, `name`) VALUES
(1, 2, 'Brest'),
(2, 4, 'Brest'),
(3, 2, 'Minsk'),
(4, 1, 'Moscow'),
(5, 3, 'Kyiv');

-- --------------------------------------------------------

--
-- Дублирующая структура для представления `cities_view`
-- (См. Ниже фактическое представление)
--
CREATE TABLE `cities_view` (
`id` int unsigned
,`city` varchar(255)
,`country` varchar(255)
);

-- --------------------------------------------------------

--
-- Структура таблицы `classes`
--

CREATE TABLE `classes` (
  `id` int UNSIGNED NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `classes`
--

INSERT INTO `classes` (`id`, `name`) VALUES
(1, 'First'),
(2, 'Business'),
(3, 'Economy');

-- --------------------------------------------------------

--
-- Структура таблицы `countries`
--

CREATE TABLE `countries` (
  `id` int UNSIGNED NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `countries`
--

INSERT INTO `countries` (`id`, `name`) VALUES
(1, 'Russia'),
(2, 'Belarus'),
(3, 'Ukraine'),
(4, 'France');

-- --------------------------------------------------------

--
-- Структура таблицы `flights`
--

CREATE TABLE `flights` (
  `id` int UNSIGNED NOT NULL,
  `id_plane` int UNSIGNED DEFAULT NULL,
  `id_route` int UNSIGNED DEFAULT NULL,
  `boarding_date` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `flights`
--

INSERT INTO `flights` (`id`, `id_plane`, `id_route`, `boarding_date`) VALUES
(1, 1, 3, '2022-06-01 18:33:00.000000'),
(2, 2, 3, '2022-06-01 12:30:00.000000'),
(3, 3, 4, '2022-06-01 16:20:00.000000'),
(4, 5, 1, '2022-06-01 17:40:00.000000'),
(5, 4, 6, '2022-06-01 21:10:00.000000'),
(6, 2, 9, '2022-06-01 22:15:00.000000');

-- --------------------------------------------------------

--
-- Дублирующая структура для представления `flights_view`
-- (См. Ниже фактическое представление)
--
CREATE TABLE `flights_view` (
`id` int unsigned
,`number` varchar(255)
,`fromAirport` varchar(255)
,`fromCity` varchar(255)
,`fromCountry` varchar(255)
,`toAirport` varchar(255)
,`toCity` varchar(255)
,`toCountry` varchar(255)
,`boarding_datetime` datetime(6)
,`id_route` int unsigned
);

-- --------------------------------------------------------

--
-- Дублирующая структура для представления `flight_fullname_class_view`
-- (См. Ниже фактическое представление)
--
CREATE TABLE `flight_fullname_class_view` (
`id_flight` int unsigned
,`fullname` varchar(255)
,`class` varchar(255)
);

-- --------------------------------------------------------

--
-- Дублирующая структура для представления `from_to_view`
-- (См. Ниже фактическое представление)
--
CREATE TABLE `from_to_view` (
`id` int unsigned
,`fromAirport` varchar(255)
,`fromCity` varchar(255)
,`fromCountry` varchar(255)
,`toAirport` varchar(255)
,`toCity` varchar(255)
,`toCountry` varchar(255)
);

-- --------------------------------------------------------

--
-- Дублирующая структура для представления `id_airport_country_and_city`
-- (См. Ниже фактическое представление)
--
CREATE TABLE `id_airport_country_and_city` (
`id_airport` int unsigned
,`country` varchar(255)
,`city` varchar(255)
);

-- --------------------------------------------------------

--
-- Структура таблицы `planes`
--

CREATE TABLE `planes` (
  `id` int UNSIGNED NOT NULL,
  `id_seating_layout` int UNSIGNED NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `planes`
--

INSERT INTO `planes` (`id`, `id_seating_layout`, `name`, `number`) VALUES
(1, 1, 'Ту-144', 'FV9263'),
(2, 2, 'Boeing-737', 'EH5921'),
(3, 2, 'Boeing-737', 'FR5903'),
(4, 3, 'Airbus-A380', 'GL5819'),
(5, 3, 'Airbus-A380', 'GK0194');

-- --------------------------------------------------------

--
-- Структура таблицы `price_list`
--

CREATE TABLE `price_list` (
  `id_flight` int UNSIGNED NOT NULL,
  `id_class` int UNSIGNED NOT NULL,
  `price` decimal(60,2) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `price_list`
--

INSERT INTO `price_list` (`id_flight`, `id_class`, `price`) VALUES
(1, 1, '165.00'),
(1, 2, '100.00'),
(1, 3, '0.00'),
(2, 1, '400.00'),
(2, 2, '320.00'),
(2, 3, '245.00'),
(3, 1, '365.00'),
(3, 2, '200.00'),
(3, 3, '125.00'),
(4, 1, '420.00'),
(4, 2, '340.00'),
(4, 3, '280.00'),
(5, 1, '245.00'),
(5, 2, '200.00'),
(5, 3, '195.00'),
(6, 1, '365.00'),
(6, 2, '200.00'),
(6, 3, '125.00');

-- --------------------------------------------------------

--
-- Дублирующая структура для представления `price_list_view`
-- (См. Ниже фактическое представление)
--
CREATE TABLE `price_list_view` (
`id_flight` int unsigned
,`class` varchar(255)
,`price` decimal(60,2) unsigned
);

-- --------------------------------------------------------

--
-- Структура таблицы `roles`
--

CREATE TABLE `roles` (
  `id` int UNSIGNED NOT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `roles`
--

INSERT INTO `roles` (`id`, `role`) VALUES
(1, 'admin'),
(5, 'director'),
(4, 'economist'),
(2, 'maintenance dispatcher'),
(6, 'pilot'),
(3, 'traffic coordination dispatcher');

-- --------------------------------------------------------

--
-- Структура таблицы `routes`
--

CREATE TABLE `routes` (
  `id` int UNSIGNED NOT NULL,
  `id_airport_departure` int UNSIGNED NOT NULL,
  `id_airport_distanation` int UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `routes`
--

INSERT INTO `routes` (`id`, `id_airport_departure`, `id_airport_distanation`) VALUES
(1, 2, 4),
(2, 4, 2),
(3, 3, 1),
(4, 1, 3),
(5, 3, 6),
(6, 6, 3),
(7, 1, 5),
(8, 7, 1),
(9, 1, 7);

-- --------------------------------------------------------

--
-- Дублирующая структура для представления `routes_popularity_view`
-- (См. Ниже фактическое представление)
--
CREATE TABLE `routes_popularity_view` (
`fromAirport` varchar(255)
,`fromCity` varchar(255)
,`fromCountry` varchar(255)
,`toAirport` varchar(255)
,`toCity` varchar(255)
,`toCountry` varchar(255)
,`count` bigint
);

-- --------------------------------------------------------

--
-- Структура таблицы `seating_layouts`
--

CREATE TABLE `seating_layouts` (
  `id` int UNSIGNED NOT NULL DEFAULT '0',
  `id_class` int UNSIGNED NOT NULL,
  `count_rows` int UNSIGNED NOT NULL,
  `count_cols` int UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `seating_layouts`
--

INSERT INTO `seating_layouts` (`id`, `id_class`, `count_rows`, `count_cols`) VALUES
(1, 1, 6, 3),
(1, 2, 16, 5),
(1, 3, 0, 0),
(2, 1, 1, 6),
(2, 2, 13, 6),
(2, 3, 16, 6),
(3, 1, 3, 3),
(3, 2, 8, 3),
(3, 3, 8, 8);

-- --------------------------------------------------------

--
-- Структура таблицы `tickets`
--

CREATE TABLE `tickets` (
  `id` int UNSIGNED NOT NULL,
  `id_flight` int UNSIGNED DEFAULT '0',
  `id_class` int UNSIGNED DEFAULT NULL,
  `buy_datetime` datetime(6) NOT NULL,
  `fullname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `passport` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `tickets`
--

INSERT INTO `tickets` (`id`, `id_flight`, `id_class`, `buy_datetime`, `fullname`, `passport`) VALUES
(1, 1, 1, '2022-05-28 16:59:04.201783', 'Denis Zhukov', 'HB489321'),
(2, 1, 2, '2022-05-28 16:59:31.773777', 'Anna Glushenok', 'HB924106'),
(3, 2, 1, '2022-05-29 03:52:49.022537', 'Zhukova Svetlana', 'HB571994'),
(4, 4, 2, '2022-05-29 03:54:29.259280', 'Vladimir Zhukov', 'HB018593'),
(5, 4, 3, '2022-05-29 04:03:33.454721', 'Oskar Lee', 'HB513522'),
(6, 4, 3, '2022-05-29 04:03:45.173570', 'Emory Jackson', 'HB940233'),
(7, 4, 2, '2022-05-29 04:03:58.586253', 'Phillip Simmons', 'HB904132'),
(8, 4, 3, '2022-05-29 04:04:14.368409', 'Holden Gonzales', 'HB940019'),
(9, 4, 3, '2022-05-29 04:04:27.344229', 'Everett Hall', 'HB949901'),
(10, 1, 1, '2022-05-29 04:05:04.037618', 'Timur Zhukov', 'HB911113'),
(11, 1, 2, '2022-05-29 04:05:22.322868', 'Ruslan Zhukov', 'HB112341'),
(12, 6, 3, '2022-05-29 04:06:35.303841', 'Xandros Thomas', 'HB940123'),
(13, 6, 3, '2022-05-29 04:06:47.252827', 'Uthman Barnes', 'HB133345'),
(14, 6, 1, '2022-05-29 04:06:58.001838', 'Isaias Howard', 'HB990101'),
(15, 6, 2, '2022-05-29 04:07:15.315963', 'Vito Garcia', 'EH650322'),
(16, 6, 1, '2022-05-29 04:07:26.595981', 'Colby Evans', 'EH949912');

-- --------------------------------------------------------

--
-- Структура для представления `airports_view`
--
DROP TABLE IF EXISTS `airports_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `airports_view`  AS  select `airports`.`id` AS `id`,`countries`.`name` AS `country`,`cities`.`name` AS `city`,`airports`.`name` AS `airport` from ((`airports` join `cities` on((`airports`.`id_city` = `cities`.`id`))) join `countries` on((`cities`.`id_country` = `countries`.`id`))) ;

-- --------------------------------------------------------

--
-- Структура для представления `cities_view`
--
DROP TABLE IF EXISTS `cities_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `cities_view`  AS  select `cities`.`id` AS `id`,`cities`.`name` AS `city`,`countries`.`name` AS `country` from (`cities` join `countries` on((`cities`.`id_country` = `countries`.`id`))) ;

-- --------------------------------------------------------

--
-- Структура для представления `flights_view`
--
DROP TABLE IF EXISTS `flights_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `flights_view`  AS  select `flights`.`id` AS `id`,`planes`.`number` AS `number`,`from_to_view`.`fromAirport` AS `fromAirport`,`from_to_view`.`fromCity` AS `fromCity`,`from_to_view`.`fromCountry` AS `fromCountry`,`from_to_view`.`toAirport` AS `toAirport`,`from_to_view`.`toCity` AS `toCity`,`from_to_view`.`toCountry` AS `toCountry`,`flights`.`boarding_date` AS `boarding_datetime`,`flights`.`id_route` AS `id_route` from ((`flights` join `from_to_view` on((`flights`.`id_route` = `from_to_view`.`id`))) join `planes` on((`flights`.`id_plane` = `planes`.`id`))) order by `flights`.`boarding_date` desc ;

-- --------------------------------------------------------

--
-- Структура для представления `flight_fullname_class_view`
--
DROP TABLE IF EXISTS `flight_fullname_class_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `flight_fullname_class_view`  AS  select `tickets`.`id_flight` AS `id_flight`,`tickets`.`fullname` AS `fullname`,`classes`.`name` AS `class` from ((`tickets` join `flights_view` on((`flights_view`.`id` = `tickets`.`id_flight`))) join `classes` on((`classes`.`id` = `tickets`.`id_class`))) ;

-- --------------------------------------------------------

--
-- Структура для представления `from_to_view`
--
DROP TABLE IF EXISTS `from_to_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `from_to_view`  AS  select `fromtable`.`id` AS `id`,`fromtable`.`fromAirport` AS `fromAirport`,`fromtable`.`fromCity` AS `fromCity`,`fromtable`.`fromCountry` AS `fromCountry`,`totable`.`toAirport` AS `toAirport`,`totable`.`toCity` AS `toCity`,`totable`.`toCountry` AS `toCountry` from ((select `routes`.`id` AS `id`,`airports`.`name` AS `fromAirport`,`cities`.`name` AS `fromCity`,`countries`.`name` AS `fromCountry` from (((`routes` join `airports` on((`routes`.`id_airport_departure` = `airports`.`id`))) join `cities` on((`airports`.`id_city` = `cities`.`id`))) join `countries` on((`cities`.`id_country` = `countries`.`id`)))) `fromtable` join (select `routes`.`id` AS `id`,`airports`.`name` AS `toAirport`,`cities`.`name` AS `toCity`,`countries`.`name` AS `toCountry` from (((`routes` join `airports` on((`routes`.`id_airport_distanation` = `airports`.`id`))) join `cities` on((`airports`.`id_city` = `cities`.`id`))) join `countries` on((`cities`.`id_country` = `countries`.`id`)))) `totable` on((`fromtable`.`id` = `totable`.`id`))) ;

-- --------------------------------------------------------

--
-- Структура для представления `id_airport_country_and_city`
--
DROP TABLE IF EXISTS `id_airport_country_and_city`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `id_airport_country_and_city`  AS  select `airports`.`id` AS `id_airport`,`t`.`country` AS `country`,`t`.`city` AS `city` from (`airports` join (select `cities`.`id` AS `id`,`cities`.`name` AS `city`,`countries`.`name` AS `country` from (`cities` join `countries` on((`cities`.`id_country` = `countries`.`id`)))) `t` on((`airports`.`id_city` = `t`.`id`))) ;

-- --------------------------------------------------------

--
-- Структура для представления `price_list_view`
--
DROP TABLE IF EXISTS `price_list_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `price_list_view`  AS  select `price_list`.`id_flight` AS `id_flight`,`classes`.`name` AS `class`,`price_list`.`price` AS `price` from (`price_list` join `classes` on((`price_list`.`id_class` = `classes`.`id`))) ;

-- --------------------------------------------------------

--
-- Структура для представления `routes_popularity_view`
--
DROP TABLE IF EXISTS `routes_popularity_view`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`127.0.0.1` SQL SECURITY DEFINER VIEW `routes_popularity_view`  AS  select `from_to_view`.`fromAirport` AS `fromAirport`,`from_to_view`.`fromCity` AS `fromCity`,`from_to_view`.`fromCountry` AS `fromCountry`,`from_to_view`.`toAirport` AS `toAirport`,`from_to_view`.`toCity` AS `toCity`,`from_to_view`.`toCountry` AS `toCountry`,count(0) AS `count` from (`from_to_view` join `tickets` on((`from_to_view`.`id` = `tickets`.`id_flight`))) group by `tickets`.`id_flight` ;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Индексы таблицы `accounts_roles`
--
ALTER TABLE `accounts_roles`
  ADD UNIQUE KEY `id_accounts` (`id_account`),
  ADD KEY `id_roles` (`id_role`) USING BTREE;

--
-- Индексы таблицы `airports`
--
ALTER TABLE `airports`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_city` (`id_city`);

--
-- Индексы таблицы `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_country` (`id_country`);

--
-- Индексы таблицы `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `flights`
--
ALTER TABLE `flights`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_plane` (`id_plane`),
  ADD KEY `id_route` (`id_route`);

--
-- Индексы таблицы `planes`
--
ALTER TABLE `planes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `number` (`number`),
  ADD KEY `id_seating_layout` (`id_seating_layout`);

--
-- Индексы таблицы `price_list`
--
ALTER TABLE `price_list`
  ADD UNIQUE KEY `unique_index` (`id_flight`,`id_class`),
  ADD KEY `id_flight` (`id_flight`),
  ADD KEY `id_class` (`id_class`);

--
-- Индексы таблицы `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `role` (`role`);

--
-- Индексы таблицы `routes`
--
ALTER TABLE `routes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_airport_departure` (`id_airport_departure`),
  ADD KEY `id_airport_distanation` (`id_airport_distanation`);

--
-- Индексы таблицы `seating_layouts`
--
ALTER TABLE `seating_layouts`
  ADD UNIQUE KEY `unique_index` (`id`,`id_class`),
  ADD KEY `id` (`id`),
  ADD KEY `seating_layout_ibfk_1` (`id_class`);

--
-- Индексы таблицы `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_flight` (`id_flight`),
  ADD KEY `id_class` (`id_class`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT для таблицы `airports`
--
ALTER TABLE `airports`
  MODIFY `id` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT для таблицы `cities`
--
ALTER TABLE `cities`
  MODIFY `id` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `classes`
--
ALTER TABLE `classes`
  MODIFY `id` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `countries`
--
ALTER TABLE `countries`
  MODIFY `id` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `flights`
--
ALTER TABLE `flights`
  MODIFY `id` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `planes`
--
ALTER TABLE `planes`
  MODIFY `id` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `routes`
--
ALTER TABLE `routes`
  MODIFY `id` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT для таблицы `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `accounts_roles`
--
ALTER TABLE `accounts_roles`
  ADD CONSTRAINT `accounts_roles_ibfk_1` FOREIGN KEY (`id_account`) REFERENCES `accounts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `accounts_roles_ibfk_2` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `airports`
--
ALTER TABLE `airports`
  ADD CONSTRAINT `airports_ibfk_1` FOREIGN KEY (`id_city`) REFERENCES `cities` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Ограничения внешнего ключа таблицы `cities`
--
ALTER TABLE `cities`
  ADD CONSTRAINT `cities_ibfk_1` FOREIGN KEY (`id_country`) REFERENCES `countries` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `flights`
--
ALTER TABLE `flights`
  ADD CONSTRAINT `flights_ibfk_1` FOREIGN KEY (`id_plane`) REFERENCES `planes` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `flights_ibfk_2` FOREIGN KEY (`id_route`) REFERENCES `routes` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `planes`
--
ALTER TABLE `planes`
  ADD CONSTRAINT `planes_ibfk_1` FOREIGN KEY (`id_seating_layout`) REFERENCES `seating_layouts` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `price_list`
--
ALTER TABLE `price_list`
  ADD CONSTRAINT `price_list_ibfk_1` FOREIGN KEY (`id_class`) REFERENCES `classes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `price_list_ibfk_2` FOREIGN KEY (`id_flight`) REFERENCES `flights` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `routes`
--
ALTER TABLE `routes`
  ADD CONSTRAINT `routes_ibfk_1` FOREIGN KEY (`id_airport_departure`) REFERENCES `airports` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `routes_ibfk_2` FOREIGN KEY (`id_airport_distanation`) REFERENCES `airports` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `seating_layouts`
--
ALTER TABLE `seating_layouts`
  ADD CONSTRAINT `seating_layouts_ibfk_1` FOREIGN KEY (`id_class`) REFERENCES `classes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`id_flight`) REFERENCES `flights` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `tickets_ibfk_2` FOREIGN KEY (`id_class`) REFERENCES `classes` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
