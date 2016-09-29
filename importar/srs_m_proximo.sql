CREATE DATABASE  IF NOT EXISTS `srs` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `srs`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: srs
-- ------------------------------------------------------
-- Server version	5.6.15-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `proximo`
--

DROP TABLE IF EXISTS `proximo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proximo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `de` varchar(155) DEFAULT NULL,
  `id_verbo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_prox_verbo_idx` (`id_verbo`),
  CONSTRAINT `fk_prox_verbo` FOREIGN KEY (`id_verbo`) REFERENCES `verbo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proximo`
--

LOCK TABLES `proximo` WRITE;
/*!40000 ALTER TABLE `proximo` DISABLE KEYS */;
INSERT INTO `proximo` (`id`,`de`,`id_verbo`) VALUES (1,'os dados',1),(2,'os campos',1),(3,'os dados',3),(4,'a opção',13),(5,'o comando',13),(6,'o botão',13),(7,'o campo',13),(8,'os dados',13),(9,'o comando',4),(10,'o caso de uso',4),(11,'a opção',11),(12,'o comando',11),(13,'o botão',11),(14,'o botão',16),(15,'o comando',16),(16,'a opção',16),(17,'a opção',17);
/*!40000 ALTER TABLE `proximo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-10 15:20:06
