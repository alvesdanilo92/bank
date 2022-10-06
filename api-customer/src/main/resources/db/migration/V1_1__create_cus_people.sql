CREATE TABLE IF NOT EXISTS `cus_people` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `document` varchar(11) NOT NULL,
  `birth` date NOT NULL,
  `gender` varchar(45) NOT NULL,
  `updated_at` datetime NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `document` (`document`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
COMMIT;