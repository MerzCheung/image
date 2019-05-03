CREATE TABLE `rgb` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `img_name` varchar(320) DEFAULT NULL,
  `r` int(5) DEFAULT NULL,
  `g` int(5) DEFAULT NULL,
  `b` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;