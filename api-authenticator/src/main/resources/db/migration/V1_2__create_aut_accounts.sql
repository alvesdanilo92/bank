CREATE TABLE IF NOT EXISTS `aut_accounts` (
  `document` varchar(45) NOT NULL,
  `number` int(11) DEFAULT NULL,
  `digit` int(11) DEFAULT NULL,
  `agency` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`document`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `aut_accounts`
  ADD CONSTRAINT `fk_accounts_users` FOREIGN KEY (`document`) REFERENCES `aut_users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;