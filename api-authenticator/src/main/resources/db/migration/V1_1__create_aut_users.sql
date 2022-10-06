CREATE TABLE IF NOT EXISTS `aut_users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(200) NOT NULL,
  `active` tinyint(4) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;