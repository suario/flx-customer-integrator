CREATE TABLE IF NOT EXISTS `address` (
  `id` bigint AUTO_INCREMENT  PRIMARY KEY,
  `street` varchar(30) NOT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(2) NOT NULL,
  `zip_code` varchar(8) NOT NULL
);

CREATE TABLE IF NOT EXISTS `customer` (
  `id` bigint AUTO_INCREMENT  PRIMARY KEY,
  `customer_id` bigint NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(120) NOT NULL,
  `phone_number` varchar(10) NOT NULL,
  `address_id` bigint NOT NULL,
  FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
);