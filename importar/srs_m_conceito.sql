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
-- Table structure for table `conceito`
--

DROP TABLE IF EXISTS `conceito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conceito` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `de` varchar(255) DEFAULT NULL,
  `f` double DEFAULT NULL,
  `id_projeto` int(11) DEFAULT NULL,
  `id_tipo_conceito` int(11) DEFAULT NULL,
  `nome_lemma` varchar(255) DEFAULT NULL,
  `utilizado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IUNIQUE` (`id_projeto`,`nome_lemma`,`id_tipo_conceito`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conceito`
--

LOCK TABLES `conceito` WRITE;
/*!40000 ALTER TABLE `conceito` DISABLE KEYS */;
INSERT INTO `conceito` (`id`,`de`,`f`,`id_projeto`,`id_tipo_conceito`,`nome_lemma`,`utilizado`) VALUES (1,'certidão',42,1,101,'certidão',1),(2,'solicitação',13,1,101,'solicitação',1),(3,'usuário',13,1,101,'usuário',0),(4,'status',6,1,101,'status',-1),(5,'disponibilização',5,1,101,'disponibilização',0),(6,'validação',4,1,101,'validação',0),(7,'chave',3,1,101,'chave',-1),(8,'emissão',3,1,101,'emissão',0),(9,'dados',3,1,101,'dado',0),(10,'informações',2,1,101,'informação',0),(11,'gestor',2,1,101,'gestor',-1),(12,'rejeição',2,1,101,'rejeição',0),(13,'justificativa',2,1,101,'justificativa',0),(14,'confecção',2,1,101,'confecção',0),(15,'sessões',2,1,101,'sessões',0),(16,'chefe',2,1,101,'chefe',-1),(17,'consulta',2,1,101,'consulta',0),(18,'contas',2,1,101,'conta',0),(19,'secretaria',2,1,101,'secretaria',0),(20,'download',2,1,101,'download',0),(21,'unidade',2,1,101,'unidade',-1),(22,'campos',1,1,101,'campo',0),(23,'numeração',1,1,101,'numeração',0),(24,'usuário externo',5,1,102,'usuário externo',0),(25,'certidão retificadora',3,1,102,'certidão retificador',0),(26,'usuário interno',2,1,102,'usuário interno',1),(27,'gestor do sistema',2,1,102,'gestor do sistema',0),(28,'chave de validação',2,1,102,'chave de validação',0),(29,'usuário chefe',1,1,102,'usuário chefe',1),(30,'tela de certidão',1,1,102,'tela de certidão',0),(31,'disponibilização da certidão',1,1,102,'disponibilização da certidão',0),(32,'final da certidão',1,1,102,'final da certidão',0),(33,'tribunal de contas',1,1,102,'tribunal de conta',0),(34,'secretaria das sessões',1,1,102,'secretaria das sessões',0),(35,'perfil de chefe',1,1,102,'perfil de chefe',0),(36,'prestações de contas',1,1,102,'prestação de conta',0),(37,'emissão da certidão',1,1,102,'emissão da certidão',0),(38,'campos de número',1,1,102,'campo de número',0),(39,'número da certidão',1,1,102,'número da certidão',0),(40,'sistema de emissão',1,1,102,'sistema de emissão',0),(41,'status da solicitação',3,1,102,'status da solicitação',0),(42,'certidão em mãos',1,1,102,'certidão em mãos',0),(43,'procedimentos de confecção',1,1,102,'procedimento de confecção',0),(44,'informações da certidão',1,1,102,'informação da certidão',0),(45,'unidade gestora',1,1,102,'unidade gestor',0),(46,'consulta a certidão',1,1,102,'consulta a certidão',0),(47,'solicitar',23,1,103,'solicitar',0),(48,'disponibilizar',11,1,103,'disponibilizar',0),(49,'validar',6,1,103,'validar',0),(50,'consultar',5,1,103,'consultar',-1),(51,'anexar',4,1,103,'anexar',0),(52,'rejeitar',4,1,103,'rejeitar',0),(53,'exibir',3,1,103,'exibir',-1),(54,'autenticar',2,1,103,'autenticar',0),(55,'filtrar',2,1,103,'filtrar',0),(56,'receber',2,1,103,'receber',0),(57,'verificar',1,1,103,'verificar',0),(58,'alterar',1,1,103,'alterar',-1),(59,'detectar',1,1,103,'detectar',0),(60,'acessar',1,1,103,'acessar',0),(61,'contemplar',1,1,103,'contemplar',0),(62,'imprimir',0,1,103,'imprimir',-1),(63,'gravar',0,1,103,'gravar',-1),(64,'inserir',0,1,103,'inserir',-1),(65,'abrir',0,1,103,'abrir',0),(66,'anexar certidão',1,1,105,'anexar certidão',2),(67,'solicitar certidão retificadora',1,1,105,'solicitar certidão retificador',0),(68,'imprimir certidão',2,1,105,'imprimir certidão',1),(69,'solicitar certidão',2,1,105,'solicitar certidão',2),(70,'consultar certidão',2,1,105,'consultar certidão',2),(71,'disponibilizar certidão',1,1,105,'disponibilizar certidão',0),(72,'validar certidão',2,1,105,'validar certidão',0),(73,'exibir justificativa',1,1,105,'exibir justificativa',0),(74,'gravar data da disponibilização',1,1,105,'gravar data da disponibilização',0),(75,'verificar status da solicitação',1,1,105,'verificar status da solicitação',0),(76,'inserir campos de número',1,1,105,'inserir campo de número',0),(77,'alterar status da solicitação',1,1,105,'alterar status da solicitação',0),(78,'rejeitar solicitação',1,1,105,'rejeitar solicitação',1),(79,'autenticar sistema',2,1,105,'autenticar sistema',0),(80,'detectar erro',1,1,105,'detectar erro',0),(81,'acessar link',1,1,105,'acessar link',0),(82,'filtrar solicitações de certidão',1,1,105,'filtrar solicitação de certidão',0),(83,'abrir tela de visualização',1,1,105,'abrir tela de visualização',0),(84,'contemplar funcionalidades principais',1,1,105,'contemplar funcionalidade principal',0),(85,'receber email',1,1,105,'receber email',0),(86,'consultar status',1,1,105,'consultar status',0),(87,'rejeitar certidão',2,1,105,'rejeitar certidão',0),(88,'exibir link para download',1,1,105,'exibir link para download',0),(89,'TCE#Tribunal de Contas do Estado',0,1,104,'TCE#Tribunal de contas do Estado',0),(90,'solicitação de certidão',13,1,102,'solicitação de certidão',-1),(91,'salva',1,1,101,'salva',0),(92,'sequencial',1,1,101,'sequencial',0),(94,'órgão',1,1,101,'órgão',0),(95,'procedimentos',1,1,101,'procedimento',0),(96,'certidão informando',1,1,102,'certidão informando',0),(97,'data da disponibilização',1,1,102,'data da disponibilização',0),(98,'solicitação fornecendo',1,1,102,'solicitação fornecendo',0),(99,'status de aguardando',1,1,102,'status de aguardando',0),(100,'confecção da certidão',1,1,102,'confecção da certidão',0),(101,'órgão certidões',1,1,102,'órgão certidão',0),(102,'certidão anexada',1,1,102,'certidão anexada',0),(103,'tipo de certidão',1,1,102,'tipo de certidão',0),(104,'usuário interessado',1,1,102,'usuário interessado',0),(105,'encaminhar',2,1,103,'encaminhar',0),(106,'seguir',1,1,103,'seguir',0),(107,'permitir',1,1,103,'permitir',0),(108,'dirigir',1,1,103,'dirigir',0),(109,'conferir',1,1,103,'conferir',0),(110,'enviar',1,1,103,'enviar',0),(111,'reencaminha',1,1,103,'reencaminha',0),(112,'definir',1,1,103,'definir',0),(113,'crer',0,1,103,'crer',0),(114,'gerar',0,1,103,'gerar',0),(115,'seguir fluxo normal',1,1,105,'seguir fluxo normal',0),(116,'validar status da solicitação',1,1,105,'validar status da solicitação',0),(117,'acessar link informado',1,1,105,'acessar link informado',0),(118,'exibir certidão possibilitando',1,1,105,'exibir certidão possibilitando',0),(119,'crer solicitação vinculada',1,1,105,'crer solicitação vinculada',0),(120,'gerar numeração sequencial',1,1,105,'gerar numeração sequencial',0),(121,'encaminhar setor',1,1,105,'encaminhar setor',0),(122,'anexar certidão em formato',1,1,105,'anexar certidão em formato',0),(123,'receber certidão',1,1,105,'receber certidão',0),(124,'encaminhar secretaria de sessões',1,1,105,'encaminhar secretaria de sessões',0),(125,'receber email informando',1,1,105,'receber email informando',0),(126,'permitir usuários externos',1,1,105,'permitir usuário externo',0),(127,'dirigir ente',1,1,105,'dirigir ente',0),(128,'conferir certidão',1,1,105,'conferir certidão',0),(129,'enviar diretor da unidade',1,1,105,'enviar diretor da unidade',0),(130,'reencaminha solicitação',1,1,105,'reencaminha solicitação',0),(131,'solicitar certidão informando',1,1,105,'solicitar certidão informando',0),(132,'rejeitar solicitação informando',1,1,105,'rejeitar solicitação informando',0),(133,'gravar dado',1,1,105,'gravar dado',0),(134,'definir setor interno',1,1,105,'definir setor interno',0),(135,'filtrar solicitações',1,1,105,'filtrar solicitação',0);
/*!40000 ALTER TABLE `conceito` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-10 15:20:24
