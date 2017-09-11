CREATE TABLE IF NOT EXISTS `products` (
  `id`          INT NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(455) DEFAULT NULL,
  `cost`        DECIMAL DEFAULT NULL,
  `finalStorageDate` TIMESTAMP,
  `description` VARCHAR(4000) DEFAULT NULL ,
  `manufacturer_name` VARCHAR(455) DEFAULT NULL,
  `manufacturer_adress` VARCHAR(455) DEFAULT NULL,
  `manufacturer_phoneNumber` VARCHAR(455) DEFAULT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS `users` (
  `username`    VARCHAR(50) NOT NULL,
  `password`    VARCHAR(455) NOT NULL,
  `email`       VARCHAR(455) DEFAULT NULL,
  `administrator` BOOLEAN,
  PRIMARY KEY (`username`)
);
