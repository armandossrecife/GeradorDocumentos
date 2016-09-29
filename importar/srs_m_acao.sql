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
-- Table structure for table `acao`
--



LOCK TABLES `acao` WRITE;
/*!40000 ALTER TABLE `acao` DISABLE KEYS */;
SET names 'utf8';
INSERT INTO `acao` (`id`, `objeto`,`verbo`,`id_projeto`) VALUES (11,'campos de número','inserir',1),(1,'certidão','anexar',1),(99,'certidão','conferir',1),(5,'certidão','consultar',1),(6,'certidão','disponibilizar',1),(3,'certidão','imprimir',1),(88,'certidão','receber',1),(25,'certidão','rejeitar',1),(4,'certidão','solicitar',1),(7,'certidão','validar',1),(87,'certidão em formato','anexar',1),(102,'certidão informando','solicitar',1),(83,'certidão possibilitando','exibir',1),(2,'certidão retificadora','solicitar',1),(104,'dado','gravar',1),(9,'data da disponibilização','gravar',1),(100,'diretor da unidade','enviar',1),(22,'email','receber',1),(91,'email informando','receber',1),(96,'ente','dirigir',1),(16,'erro','detectar',1),(77,'fluxo normal','seguir',1),(21,'funcionalidades principais','contemplar',1),(8,'justificativa','exibir',1),(17,'link','acessar',1),(81,'link informado','acessar',1),(26,'link para download','exibir',1),(85,'numeração sequencial','gerar',1),(89,'secretaria de sessões','encaminhar',1),(86,'setor','encaminhar',1),(106,'setor interno','definir',1),(14,'sistema','autenticar',1),(101,'solicitação','reencaminha',1),(13,'solicitação','rejeitar',1),(103,'solicitação informando','rejeitar',1),(84,'solicitação vinculada','crer',1),(109,'solicitações','filtrar',1),(18,'solicitações de certidão','filtrar',1),(24,'status','consultar',1),(12,'status da solicitação','alterar',1),(79,'status da solicitação','validar',1),(10,'status da solicitação','verificar',1),(20,'tela de visualização','abrir',1),(92,'usuários externos','permitir',1);
/*!40000 ALTER TABLE `acao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-10 15:19:57
