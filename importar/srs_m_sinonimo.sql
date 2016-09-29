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
-- Table structure for table `sinonimo`
--

DROP TABLE IF EXISTS `sinonimo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sinonimo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `de` varchar(45) DEFAULT NULL,
  `id_verbo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sinonimo_verbo_idx` (`id_verbo`),
  CONSTRAINT `fk_s_v` FOREIGN KEY (`id_verbo`) REFERENCES `verbo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sinonimo`
--

LOCK TABLES `sinonimo` WRITE;
/*!40000 ALTER TABLE `sinonimo` DISABLE KEYS */;
INSERT INTO `sinonimo` (`id`, `de`,`id_verbo`) VALUES (1,'exibir',1),(2,'mostrar',1),(3,'apresentar',1),(5,'consultar',2),(6,'pesquisar',2),(7,'inserir',3),(8,'preencher',3),(9,'digitar',3),(10,'informar',3),(11,'fornecer',3),(12,'entrar',3),(13,'executar',4),(14,'selecionar',13),(15,'pressionar',11),(16,'acionar',4),(17,'imprimir',8),(18,'gravar',9),(19,'armazenar',9),(21,'acrescentar',3),(22,'introduzir',3),(24,'deletar',5),(25,'excluir',5),(26,'apagar',5),(27,'remover',5),(28,'alterar',6),(29,'modificar',6),(30,'editar',6),(31,'mudar',6),(32,'corrigir',6),(33,'salvar',9),(36,'emitir',8),(37,'atualizar',6),(38,'chamar',4),(39,'invocar',4),(40,'registrar',9),(41,'cadastrar',3),(42,'incluir',3),(44,'localizar',2),(45,'clicar',11),(46,'acionar',11),(47,'abrir',12),(49,'sair',16),(50,'validar',15),(51,'verificar',15),(52,'escolher',16),(53,'marcar',17);
/*!40000 ALTER TABLE `sinonimo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-10 15:20:01
