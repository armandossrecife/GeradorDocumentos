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
-- Table structure for table `tabela`
--

DROP TABLE IF EXISTS `tabela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tabela` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `de` longtext,
  `id_tipo_tabela` int(11) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `nome_lemma` varchar(255) DEFAULT NULL,
  `id_projeto` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IUNIQUE` (`id_projeto`,`nome_lemma`,`id_tipo_tabela`),
  CONSTRAINT `FK_tabela_id_projeto` FOREIGN KEY (`id_projeto`) REFERENCES `projeto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabela`
--

LOCK TABLES `tabela` WRITE;
/*!40000 ALTER TABLE `tabela` DISABLE KEYS */;
INSERT INTO `tabela` (`id`, `de`, `id_tipo_tabela`, `nome`, `nome_lemma`, `id_projeto`) VALUES (1,'Interface on-line para solicitação de certidões.',3,'Solicitar certidão','solicitar certidão',1),(2,'Qualquer cidadão ou órgão que necessite obter certidão junto ao TCE-PI.',2,'Usuário externo','usuário externo',1),(3,'Funcionário do TCE, devidamente autorizado a efetuar atividades no escopo do sistema.',2,'Usuário interno','usuário interno',1),(4,'Usuário responsável por disponibilizar as certidões.',2,'Usuário chefe','usuário chefe',1),(5,'O sistema deve permitir ao Usuário chefe anexar uma ou mais certidões a uma solicitação de certidão, além de rejeitar uma certidão.',5,'Anexar certidão','anexar certidão',1),(6,'O sistema deve permitir que o usuário valide uma certidão através da digitação da chave.',5,'Validar certidão','validar certidão',1),(7,'O sistema deve permitir que o usuário externo consulte uma certidão.',5,'Consultar certidão','consultar certidão',1),(9,'O usuário chefe pode rejeitar uma solicitação que contenha algum defeito.',5,'Rejeitar solicitação','rejeitar solicitação',1),(10,'O sistema deve permitir que o usuário externo solicite uma certidão.',5,'Solicitar certidão','solicitar certidão',1),(11,'Interface para consuilta de certidões',3,'Consultar certidão','consultar certidão',1),(12,'Interface que permite ao usuário chefe gerenciar todas as solicitações.',3,'Gerenciar certidão','gerenciar certidão',1),(13,'Interface disponível para validação e diaponibilização da certidão.',3,'Validar certidão','validar certidão',1),(14,'Inteface para anexação de uma certidão a outra certidão.',3,'Anexar certidão','anexar certidão',1),(17,'O sistema deve permitir ao usuário chefe anexar e disponibilizar certidões e rejeitar solicitações.',5,'Gerenciar certidão','gerenciar certidão',1),(18,'O usuário externo poderá imprimir uma certidão já finalizada.',5,'Imprimir certidão','imprimir certidão',1),(19,'Qualquer pessoa ou entidade que acesse o Sistema de Solicitação de Certidões.',2,'Usuário','usuário',1),(20,'O Usuário chefe poderá disponibilizar Certidão já finalizada.',5,'Disponibilizar certidão','disponibilizar certidão',1),(21,'Interface que permitue que o usuário externo faça uma solictação de uma certidão retiifcadora.',3,'Solicitar certidão retificadora','solicitar certidão retificador',1),(23,NULL,1,'teste','teste',1),(24,'Interface que permite que o usuário chefe rejeite a uma solicitação.',3,'Rejeitar solicitação','rejeitar solicitação',1),(25,'Interface que permirte que o usuário chefe disponibilize uma certidão para o solicitante.',3,'Disponibilizar certidão','disponibilizar certidão',1),(26,'O sistema deve permitir que o usuário externo solicite uma certidão retificadora com o fim de corrigir algo errado em uma certidão pedida anteriormente.',5,'Solicitar certidão retificadora','solicitar certidão retificador',1);
/*!40000 ALTER TABLE `tabela` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-10 15:19:53
