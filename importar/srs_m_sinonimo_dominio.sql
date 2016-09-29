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
-- Table structure for table `sinonimo_dominio`
--

DROP TABLE IF EXISTS `sinonimo_dominio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sinonimo_dominio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chave` varchar(255) DEFAULT NULL,
  `sinonimo` varchar(255) DEFAULT NULL,
  `id_projeto` int(11) DEFAULT NULL,
  `conceito_chave` int(11) DEFAULT NULL,
  `conceito_sinonimo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IUNIQUE` (`id_projeto`,`chave`,`sinonimo`),
  KEY `FK_conceito_chave_idx` (`conceito_chave`),
  KEY `FK_conceito_sinonimo_idx` (`conceito_sinonimo`),
  CONSTRAINT `FK_conceito_chave` FOREIGN KEY (`conceito_chave`) REFERENCES `conceito` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_conceito_sinonimo` FOREIGN KEY (`conceito_sinonimo`) REFERENCES `conceito` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_sinonimo_dominio_id_projeto` FOREIGN KEY (`id_projeto`) REFERENCES `projeto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sinonimo_dominio`
--

LOCK TABLES `sinonimo_dominio` WRITE;
/*!40000 ALTER TABLE `sinonimo_dominio` DISABLE KEYS */;
INSERT INTO `sinonimo_dominio` (`id`,`chave`,`sinonimo`,`id_projeto`,`conceito_chave`,`conceito_sinonimo`) VALUES (11,'usuário chefe','usuário chefe',1,29,29),(12,'chefe','usuário chefe',1,16,29),(13,'gestor','usuário chefe',1,11,29),(14,'solicitação','solicitação',1,2,2),(15,'solicitação de certidão','solicitação',1,90,2),(16,'chave de validação','chave de validação',1,28,28),(17,'chave','chave de validação',1,7,28),(24,'status da solicitação','status da solicitação',1,41,41),(25,'status','status da solicitação',1,4,41),(26,'unidade gestor','unidade gestor',1,45,45),(27,'unidade','unidade gestor',1,21,45);
/*!40000 ALTER TABLE `sinonimo_dominio` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-10 15:19:55
