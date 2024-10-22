CREATE TABLE IF NOT EXISTS `customer_crm` (
  `id` int AUTO_INCREMENT  PRIMARY KEY,
  `full_name` varchar(90) NOT NULL,
  `contact_email` varchar(120) NOT NULL,
  `primary_phone` varchar(10) NOT NULL,
  `location` varchar(78) NOT NULL
);