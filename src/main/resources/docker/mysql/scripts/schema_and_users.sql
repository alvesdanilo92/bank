CREATE SCHEMA db_bank_authenticator;
CREATE SCHEMA db_bank_customer;

CREATE USER 'usr_bank_authenticator'@'%' IDENTIFIED BY 'atErciDetACh';
CREATE USER 'usr_bank_customer'@'%' IDENTIFIED BY 'INgULTUMNalE';

GRANT ALL ON db_bank_authenticator.* TO 'usr_bank_authenticator'@'%';
GRANT ALL ON db_bank_customer.* TO 'usr_bank_customer'@'%';

FLUSH PRIVILEGES;