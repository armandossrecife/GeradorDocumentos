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
-- Table structure for table `introducao`
--

DROP TABLE IF EXISTS `introducao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `introducao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `objetivo` varchar(1000) NOT NULL,
  `escopo` varchar(1000) NOT NULL,
  `visao_geral` varchar(1000) NOT NULL,
  `id_projeto` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_introducao_projeto_idx` (`id_projeto`),
  CONSTRAINT `fk_introducao_projeto` FOREIGN KEY (`id_projeto`) REFERENCES `projeto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `introducao`
--

LOCK TABLES `introducao` WRITE;
/*!40000 ALTER TABLE `introducao` DISABLE KEYS */;
INSERT INTO `introducao` (`id`,`objetivo`,`escopo`,`visao_geral`,`id_projeto`) VALUES (1,'Este documento tem o objetivo de descrever e especificar os requisitos que dever ser atendidos pelo Sistema de Emissão de Certidões, de forma a satisfazer as necessidades o Tribunal de Contas do Estado do Piauí - TCE-PI e de seus jurisdicionado, bem como definir o produto a ser desenvolvido e servir documento guia para todos participantes do sistema.','O Sistema de Emissão de Certidões, deve oferecer o apoio informatizado ao controle de emissão das certidões emitidasd pelo TCE-PI e deve ser disponibilizado no sítio da entidade.\nCom o uso do sistema o TCE-PI e seus usurário dosporão de mais agilidade na emissão e disponibilização das certidões, bem como um mapeamento das certidões emitidas por tipo, períodom denre outros.','Este sistema permite que os usuários externos do Tribunal de Contas do Estado - TCE, solicitem junto ao órgão certidões negativas relacionadas a dívidas, situações processuais e relacionadas\nà prestações de contas.\nO sistema contempla 8 funcionalidades principais:\n- Solicitar certidão;\n- Anexar certidão;\n- Rejeitar certidão;\n- Disponibilizar certidão;\n- Consultar certidão;\n- Emitir certidão;\n- Validar certidão;\n- Solicitar certidão retificadora;\nAs funcionalidades serão descritas nas seções a seguir.',1);
/*!40000 ALTER TABLE `introducao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-10 15:20:17
