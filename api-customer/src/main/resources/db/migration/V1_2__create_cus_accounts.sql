CREATE TABLE IF NOT EXISTS `cus_accounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cus_person_id` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `digit` int(11) NOT NULL,
  `agency` varchar(5) NOT NULL,
  `status` varchar(10) NOT NULL,
  `opened_at` datetime NOT NULL,
  `closed_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cus_acc_per1` (`cus_person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `cus_accounts`
  ADD CONSTRAINT `fk_cus_acc_per1` FOREIGN KEY (`cus_person_id`) REFERENCES `cus_people` (`id`);
COMMIT;