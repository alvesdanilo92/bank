CREATE TABLE IF NOT EXISTS `acc_cus_historic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `acc_reg_historic_id` int(11) NOT NULL,
  `cus_accounts_id` int(11) NOT NULL,
  `value` decimal(10,2) NOT NULL,
  `description` varchar(50) NOT NULL,
  `updated_at` datetime NOT NULL,
  `created_at` datetime  NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_acc_reg_historic1` (`acc_reg_historic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `acc_cus_historic`
  ADD CONSTRAINT `fk_acc_reg_historic1` FOREIGN KEY (`acc_reg_historic_id`) REFERENCES `acc_reg_historic` (`id`);
COMMIT;