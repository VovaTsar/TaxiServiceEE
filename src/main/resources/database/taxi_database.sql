create database `taxi_database`;

use `taxi_database`;



 SET NAMES utf8 ;


DROP TABLE IF EXISTS `adress`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `adress` (
  `id_adress` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `street` varchar(128) NOT NULL,
  `house_number` varchar(12) NOT NULL,
  PRIMARY KEY (`id_adress`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

LOCK TABLES `adress` WRITE;
INSERT INTO `adress` VALUES (1,'Янгеля','20'),(2,'Янгеля','19'),(3,'Янгеля','15'),(4,'Янгеля','5'),(5,'Борщагівська','30'),(6,'Борщагівська','3'),(7,'Перемоги','7'),(8,'Перемоги','8'),(9,'Васильківська','12'),(10,'Васильківська','55');
UNLOCK TABLES;

 SET NAMES utf8 ;



DROP TABLE IF EXISTS `car`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `car` (
  `id_car` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(8) NOT NULL,
  `brand` varchar(45) NOT NULL,
  `color` varchar(45) NOT NULL,
  `car_type` varchar(45) NOT NULL,
  PRIMARY KEY (`id_car`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




LOCK TABLES `car` WRITE;

INSERT INTO `car` VALUES (1,'AA330AA','Audi','red','wagon'),(2,'AA4533AA','BMW','black','wagon'),(3,'AA4544AA','Mercedes','white','minivan'),(4,'AA2095AA','BMV','white','light'),(5,'AA8789AA','BMV','red','light'),(6,'AA2323AA','Audi','black','light'),(7,'AA2538AA','Audi','white','wagon');

UNLOCK TABLES;

 SET NAMES utf8 ;



DROP TABLE IF EXISTS `client`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `client` (
  `id_client` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `surname` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `phone_number` varchar(13) NOT NULL,
  `e_mail` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`id_client`),
  UNIQUE KEY `phone_number_UNIQUE` (`phone_number`),
  UNIQUE KEY `e-mail_UNIQUE` (`e_mail`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;



LOCK TABLES `client` WRITE;

INSERT INTO `client` VALUES (1,'Іван','Іван','+380975240365','ivan@gmail.com','cerber'),(2,'Василь','Василь','+380973223320','vacyl@gmail.com','cerber'),(3,'Сергій','Сергій','+380978363722','sergiy@gmail.com','cerber'),(4,'Олександр','Олександр','+380967825532','olexandr@gmail.com','cerber'),(5,'Петро','Петро','+380452377623','petro@gmail.com','cerber'),(6,'Радік','Радік','+380923422237','radic@gmail.com','cerber'),(7,'Петро','Андрій','+380477423452','andriyya@gmail.com','сукиук'),(8,'Микола','Назар','+380961248850','joy0@spaces.ru','qwerty123'),(9,'Вова','Вова','+380968174583','vova@gmail.com','cerber');

UNLOCK TABLES;


 SET NAMES utf8 ;




DROP TABLE IF EXISTS `coupon`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `coupon` (
  `id_coupon` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `coupon_name` varchar(45) NOT NULL,
  `discount` int(10) NOT NULL,
  PRIMARY KEY (`id_coupon`),
  UNIQUE KEY `coupon_UNIQUE` (`coupon_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



LOCK TABLES `coupon` WRITE;

INSERT INTO `coupon` VALUES (1,'AAA',20),(2,'BBB',10),(3,'CCC',40),(4,'DDD',20);

UNLOCK TABLES;


 SET NAMES utf8 ;



DROP TABLE IF EXISTS `driver`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `driver` (
  `id_driver` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `surname` varchar(128) NOT NULL,
  `name` varchar(128) NOT NULL,
  `middle_name` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `phone_number` varchar(14) NOT NULL,
  `driver_status` varchar(45) NOT NULL,
  `id_car` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_driver`),
  UNIQUE KEY `phone_number_UNIQUE` (`phone_number`),
  KEY `fk_driver_car_idx` (`id_car`),
  CONSTRAINT `fk_driver_car` FOREIGN KEY (`id_car`) REFERENCES `car` (`id_car`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;




LOCK TABLES `driver` WRITE;

INSERT INTO `driver` VALUES (1,'Іванов','Андрій','Сергійович','cerber','+380979111874','free',1),(2,'Петров','Іван','Сергійович','cerber','+380979111875','free',2),(3,'Давід','Орест','Андрійович','cerber','+380979111876','free',4),(4,'Саверченко','Андрій','Олегович','cerber','+380979111877','free',3),(5,'Кириченко','Анатолій','Сергійович','cerber','+380979111878','free',5),(6,'Валигін','Максим','Васильович','cerber','+380979111879','free',7),(7,'Полонський','Олександр','Сергійович','cerber','+38097911180','free',6);

UNLOCK TABLES;


 SET NAMES utf8 ;




DROP TABLE IF EXISTS `order`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `order` (
  `id_order` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_status` varchar(45) NOT NULL,
  `id_client` int(10) unsigned NOT NULL,
  `id_driver` int(10) unsigned DEFAULT NULL,
  `id_adress_departure` int(10) unsigned NOT NULL,
  `id_adress_arrive` int(10) unsigned NOT NULL,
  `id_coupon` int(10) unsigned DEFAULT NULL,
  `cost` double unsigned NOT NULL,
  `cost_with_discount` double unsigned NOT NULL,
  PRIMARY KEY (`id_order`),
  KEY `fk_order_client1_idx` (`id_client`),
  KEY `fk_order_coupon1_idx` (`id_coupon`),
  KEY `fk_order_adress2_idx` (`id_adress_departure`),
  KEY `fk_order_driver1_idx1` (`id_driver`),
  KEY `fk_order_adress3_idx` (`id_adress_arrive`),
  CONSTRAINT `fk_order_adress2` FOREIGN KEY (`id_adress_departure`) REFERENCES `adress` (`id_adress`),
  CONSTRAINT `fk_order_adress3` FOREIGN KEY (`id_adress_arrive`) REFERENCES `adress` (`id_adress`),
  CONSTRAINT `fk_order_client1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`),
  CONSTRAINT `fk_order_coupon1` FOREIGN KEY (`id_coupon`) REFERENCES `coupon` (`id_coupon`),
  CONSTRAINT `fk_order_driver1` FOREIGN KEY (`id_driver`) REFERENCES `driver` (`id_driver`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;




LOCK TABLES `order` WRITE;

INSERT INTO `order` VALUES (1,'COMPLETE',3,1,1,4,1,100,90),(2,'COMPLETE',2,1,3,2,NULL,100,100),(3,'COMPLETE',4,2,4,5,2,120,100),(4,'COMPLETE',2,3,3,4,NULL,120,120),(5,'COMPLETE',2,1,3,6,1,120,100),(6,'COMPLETE',4,3,2,4,2,360,230),(7,'COMPLETE',5,2,1,5,NULL,260,260),(8,'COMPLETE',2,4,4,3,NULL,120,100),(9,'COMPLETE',1,3,3,1,NULL,265,238),(10,'COMPLETE',1,5,5,1,NULL,314,282),(11,'COMPLETE',1,4,1,7,NULL,278,250),(12,'COMPLETE',1,7,1,7,NULL,266,239),(13,'COMPLETE',1,1,4,1,NULL,332,298),(14,'COMPLETE',1,3,4,1,NULL,309,278),(15,'COMPLETE',1,5,1,10,NULL,289,260),(16,'COMPLETE',1,7,3,1,NULL,285,256),(17,'COMPLETE',1,1,6,1,NULL,321,288),(18,'COMPLETE',1,3,2,1,NULL,253,227),(19,'COMPLETE',1,5,2,1,NULL,274,246),(20,'COMPLETE',1,7,1,8,NULL,285,256),(21,'COMPLETE',1,4,1,3,1,243,194),(22,'COMPLETE',1,1,4,1,NULL,295,265),(52,'COMPLETE',1,1,1,10,1,356,255),(23,'COMPLETE',1,1,4,1,NULL,251,225),(24,'COMPLETE',1,1,7,1,NULL,328,295),(25,'COMPLETE',1,1,7,1,NULL,326,293),(26,'COMPLETE',1,1,6,1,NULL,289,260),(27,'COMPLETE',1,1,1,8,NULL,279,251),(28,'COMPLETE',1,3,7,1,NULL,249,224),(29,'COMPLETE',1,5,1,3,NULL,267,240),(30,'COMPLETE',1,1,5,1,NULL,230,207),(31,'COMPLETE',1,2,4,1,NULL,261,234),(32,'COMPLETE',1,4,7,1,NULL,289,260),(33,'COMPLETE',1,3,1,8,NULL,303,272),(34,'COMPLETE',1,5,4,1,NULL,315,283),(35,'COMPLETE',1,7,10,1,NULL,300,270),(36,'COMPLETE',1,4,4,1,NULL,298,268),(37,'COMPLETE',1,1,6,1,NULL,235,211),(38,'COMPLETE',1,2,9,1,NULL,261,234),(39,'COMPLETE',1,3,8,2,1,245,196),(40,'COMPLETE',1,5,5,1,NULL,256,230),(41,'COMPLETE',2,1,2,1,NULL,321,288),(42,'COMPLETE',1,7,6,3,1,297,213),(43,'COMPLETE',1,3,9,2,3,330,198),(44,'COMPLETE',1,4,10,7,1,284,204),(45,'COMPLETE',1,1,9,6,1,323,232),(46,'COMPLETE',1,3,10,2,1,286,205),(47,'COMPLETE',1,4,5,1,NULL,326,293),(48,'COMPLETE',31,5,9,4,NULL,274,219);

UNLOCK TABLES;



