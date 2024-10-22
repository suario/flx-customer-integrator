CREATE TABLE IF NOT EXISTS `address` (
  `id` int AUTO_INCREMENT  PRIMARY KEY,
  `street` varchar(30) NOT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(2) NOT NULL,
  `zip_code` varchar(8) NOT NULL
);

CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` int AUTO_INCREMENT  PRIMARY KEY,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(120) NOT NULL,
  `phone_number` varchar(10) NOT NULL,
  `address_id` int NOT NULL,
  FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
);