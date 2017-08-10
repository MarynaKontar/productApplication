CREATE TABLE IF NOT EXISTS `products` (
  `id`          INT NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(455) DEFAULT NULL,
  `cost`        DECIMAL DEFAULT NULL,
  `finalStorageDate` TIMESTAMP,
  `manufacturer_name` VARCHAR(455) DEFAULT NULL,
  `manufacturer_adress` VARCHAR(455) DEFAULT NULL,
  `manufacturer_phoneNumber` VARCHAR(455) DEFAULT NULL,
  PRIMARY KEY (id)
);
