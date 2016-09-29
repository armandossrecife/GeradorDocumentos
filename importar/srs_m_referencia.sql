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
-- Table structure for table `referencia`
--

DROP TABLE IF EXISTS `referencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `referencia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `de` varchar(255) NOT NULL,
  `qtd` int(11) unsigned DEFAULT NULL,
  `qtd_relevante` int(11) DEFAULT NULL,
  `perc_relevante` float DEFAULT NULL,
  `qtd_radical` int(11) DEFAULT NULL,
  `id_projeto` int(11) DEFAULT NULL,
  `nome_arquivo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_referencia_projeto_idx` (`id_projeto`),
  CONSTRAINT `fk_referencia_projeto` FOREIGN KEY (`id_projeto`) REFERENCES `projeto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `referencia`
--

LOCK TABLES `referencia` WRITE;
/*!40000 ALTER TABLE `referencia` DISABLE KEYS */;
INSERT INTO `referencia` (`id`, `de`, `qtd`, `qtd_relevante`, `perc_relevante`, `qtd_radical`, `id_projeto`, `nome_arquivo`) VALUES (1,'Descrição do sistema de certidões do TCE',0,258,38.6807,NULL,1,'certidoes.pdf');
/*!40000 ALTER TABLE `referencia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-10 15:20:08
