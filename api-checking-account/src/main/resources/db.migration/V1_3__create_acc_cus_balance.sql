CREATE TABLE IF NOT EXISTS `acc_cus_balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cus_accounts_id` int(11) NOT NULL,
  `value` decimal(10,2) NOT NULL,
  `updated_at` datetime NOT NULL,
  `created_at` datetime  NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cus_accounts_id` (`cus_accounts_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
