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
-- Table structure for table `isr`
--

DROP TABLE IF EXISTS `isr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `isr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dados_armazenados` longtext,
  `de` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `nome_lemma` varchar(255) DEFAULT NULL,
  `proposito` longtext,
  `id_projeto` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IUNIQUE` (`id_projeto`,`nome_lemma`),
  CONSTRAINT `FK_isr_id_projeto` FOREIGN KEY (`id_projeto`) REFERENCES `projeto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `isr`
--

LOCK TABLES `isr` WRITE;
/*!40000 ALTER TABLE `isr` DISABLE KEYS */;
INSERT INTO `isr` (`id`,`dados_armazenados`,`de`,`nome`,`nome_lemma`,`proposito`,`id_projeto`) VALUES (1,'- Data da solicitação, Tipo de certidão, Retificadora: Indica se a certidão é retificadora, Informações complementares, Tipo de certidão.\n- Status da solicitação: indica a situação da solicitação.\n- Setor responsável: informa o setor responsável pela confecção da certidão.\n- Detalhamento da rejeição: campo para o solicitante informar detalhes do pedido de uma certidão retificadora.\n- Arquivo de detalhamento da rejeição: algum arquivo que detalhe o porque da rejeição da solicitação. \n- Tipo de solicitante, Identificação do solicitante, nome do solicitante e email do solicitante.\n- Certidão gerada: informa a certidão gerada a partir da solicitação.\n- Solicitação original: indica qual a solicitação que originou o pedido de retificação.\n- Motivo da retificação: Indica porque o usuário externo necessita de uma certidão retificadora.\n','Armazena as informações referentes à solicitação de uma certidão.','Solicitação','solicitação',NULL,1),(2,'- Solicitação que gerou a certidão, Número da certidão, Ano da certidão, Arquivo da certidão, chave de validação.\n- Data da disponibilização e observação.\n- Código da UG.','Deve conter as infiormações referentes a uma certidão que foi solicitada pelo usuário externo.','Certidão','certidão',NULL,1),(3,'- Descrição\n- Setor responsável\n','Contém os tipo s de certidões que podem ser emitidas pelo TCE.','Tipo de certidão','tipo de certidão',NULL,1),(20,'- Código da UG\n- Nome da UG',' Unidade orçamentária ou administrativa investida do poder de gerir recursos orçamentários e financeiros, próprios ou sob descentralização.','Unidade Gestora','unidade gestor',NULL,1);
/*!40000 ALTER TABLE `isr` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-10 15:20:22
