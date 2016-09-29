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
-- Table structure for table `caso_de_uso`
--

DROP TABLE IF EXISTS `caso_de_uso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caso_de_uso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ator` varchar(255) DEFAULT NULL,
  `descricao` longtext,
  `fluxo_alternativo` longtext,
  `fluxo_excecao` longtext,
  `fluxo_principal` longtext,
  `nome` varchar(255) DEFAULT NULL,
  `nome_lemma` varchar(255) DEFAULT NULL,
  `pos_condicao` longtext,
  `pre_condicao` varchar(255) DEFAULT NULL,
  `proposito` varchar(255) DEFAULT NULL,
  `id_projeto` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IUNIQUE` (`id_projeto`,`nome_lemma`),
  CONSTRAINT `FK_caso_de_uso_id_projeto` FOREIGN KEY (`id_projeto`) REFERENCES `projeto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caso_de_uso`
--

LOCK TABLES `caso_de_uso` WRITE;
/*!40000 ALTER TABLE `caso_de_uso` DISABLE KEYS */;
INSERT INTO `caso_de_uso` (`id`,`ator`,`descricao`,`fluxo_alternativo`,`fluxo_excecao`,`fluxo_principal`,`nome`,`nome_lemma`,`pos_condicao`,`pre_condicao`,`proposito`,`id_projeto`) VALUES (1,'Usuário externo','O sistema deve se comportar como descrito na seguintes sequencia de informações, quando o usuário externo desejar solicitar uma certidão.','','7.1 Se o  tipo de certidão for vazio então \n7.2    O sistema exibe a mensagem \"O tipo de certidão é obrigatório\"\n7.3 Se a identificação do solicitante for vazio então \n7.4    O sistema exibe a mensagem \"O campo Identificação do solicitante é obrigatório\"\n\n','1. O Usuário externo exibe a tela Solicitar certidão.\n2. O Usuário externo escolhe o tipo de certidão\n3. O Usuário externo escolhe o tipo de solicitante.\n4. O Usuário externo digita a Identificação do solicitante, o email do solicitante e as Informações complementares.\n5. O Usuário externo clica na opção Gravar.\n7. O sistema valida que todos campos obrigatórios estão preenchidos.\n8. o sistema atualiza a Data da solicitação para a data atual.\n10. o sistema grava a solicitação.\n11. o Usuário externo fecha a tela Solicitar certidão.','Solicitar certidão','solicitar certidão','Que uma solitação tenha sido gravada.','O usuário ter acessado o sistema de certidão.','Registrar as solicitações de certidão efetuadas pelos usuários externos.',1),(3,'usuário','O sistema deve comportar-se como descrito na seguinte seqüência de interações quando o usuário desejar validar uma certidão.','6.1 o usuário clica no botão Baixar certidão.\n6.2 o sistema faz o download da certidão.','5.1 Se a certidão existir e a chave de validação estiver errado então\n5.2    O sistema exibe a mensagem \"Código de validação incorreto\"\n\n5.3 Se a certidão não existir então\n5.4    O sistema exibe a mensagem \"Certidão não encontrada\"','1. O usuário abre a tela Validar certidão.\n2. o sistema exibe os campos Número da certidão e chave.\n3. o usuário preenche campos Número da certidão, ano da certidão e a chave.\n4. O usuário clica na opção Validar.\n5. o usuário digita os dados da Solicitação.\n5. O sistema valida que existe uma certidão com o mesmo número.\n6. O sistema exibe os campos Data da solicitação, Data da disponibilização, retificadora e o tipo de certidão.\n7. O sistema exibe a mensagem \"Certidão validada!\"\n\n\n','Validar certidão','validar certidão','Uma certidão ser validada.','','Disponibilizar a validação das certidões emitidas pelo TCE-PI.',1),(4,'Usuário externo','O sistema deve comportar-se como descrito na seguinte seqüência de interações quando o (a) usuário externo desejar consultar uma certidão.','','6.1 Se nenhuma certidão não existir então\n6.2    O sistema exibe a mensagem \"Nenhuma certidão encontrada\".','1. O Usuário externo abre a tela Consultar certidão.\n3. O Usuário externo escolhe o Tipo de solicitante e o Tipo de certidão.\n4. O Usuário externo digita a Identificação do solicitante.\n5. O Usuário externo clica no botão Consultar.\n6. O sistema filtra as certições com os paremetros digitados.\n8. Para cada certidão filtrada faça\n9.   O sistema exibe os campos número da certidão, ano da certidão, Data da solicitação, Data da disponibilização, retificadora, Tipo de certidão e status.\n10. O usuário externo seleciona uma certidão.\n11. O usuário externo clica no botão Baixar certidão.\n12. O sistema faz o download da certidão.\n13. O usuário externo fecha a tela Consultar certidão.','Consultar certidão','consultar certidão','O sistema ter consultado uma certidão cadastrada anteriormante.','O usuário externo está acessando a página  inicial do sistema de certidão.','Disponibilizar a consulta das certidões emitidas pelo TCE-PI.',1),(5,'Usuário externo','O sistema deve comportar-se como descrito na seguinte seqüência de interações quando o (a) usuário externo desejar solicitar retificação.','3.1 O Usuário externo clica no botão Cancelar.','3.1 Se a certidão não existir então \n3.2  O sistema exibe a mensagem \"A certidão não existe\".\n','1. O Usuário externo abre a tela Solicitar certidão retificadora.\n2. o Usuário externo digita o número da certidão, o ano da certidão, o chave de validação e o motivo da retificação.\n3. O Usuário externo clica no botão Gravar.\n4. o sistema recupera a soliticação com os campos número da certidão, ano da certidão e chave de validação digitados.\n5. O sistema cria uma nova solicitação com os mesmos dados da solicitação recuperada.\n6. O sistema atualiza o campo solicitação original para a solicitação recuperada.\n7. O sistema grava a nova solicitação.','Solicitar certidão retificadora','solicitar certidão retificador','O cadastro de uma nova solicitação retificação de certidão.','O usuário externo está acessando a página  inicial do sistema de certidão.','Manter o controle das retificações solicitadas pelos usuários.',1),(6,'Usuário chefe','O sistema deve comportar-se como descrito na seguinte seqüência de interações quando o (a) usuário chefe desejar rejet rejeitar solicitação.','5.1 O Usuário chefe clica no botão Cancelar.',NULL,'1. O sistema abre a tela Rejeitar solicitação.\n2. O sistema exibe os campos identificação do solicitante, data da solicitação, setor responsável, tipo de certidão, email do solicitante e a informação complementar.\n3. O Usuário chefe digita o detalhamento da rejeição.\n4. O Usuário chefe escolhe o arquivo de detalhamento.\n5. O Usuário chefe clica no botão Rejeitar.\n6. O sistema atualiza o status da solicitação para \"Rejeitada\"','Rejeitar solicitação','rejeitar solicitação',NULL,NULL,'',1),(7,'Usuário chefe','O sistema deve comportar-se como descrito na seguinte seqüência de interações quando o (a) usuário chefe desejar anexar certidão.','5.1 o Usuário chefe clica no botão Cancelar.',NULL,'1. O sistema abre a tela Anexar certidão.\n2. O sistema exibe os campos número da certidão, código de validação, identificação do solicitante, data da disponibilização, setor responsável, tipo de certidão, email do solicitante e os arquivos da certidão.\n3. o Usuário chefe clica no botão Selecionar arquivo.\n4. o Usuário chefe digita a observação.\n5. o Usuário chefe clica no botão Anexar arquivo.\n6. o Sistema anexa o arquivo da certidão.\n','Anexar certidão','anexar certidão','Um arquivo de certidão ser anexado a uma certidão.','O usuário chefe está logado n osistema e acessando a tela de Gerenciar certidão.','Disponibilizar a funcionalidade de anexar certidão que deve ser executada pelo usuário chefe.',1),(8,'Usuário chefe','O sistema deve comportar-se como descrito na seguinte seqüência de interações quando o (a) usuário chefe desejar disponibilizar certidão.','3.1 o Usuário chefe clica no botão Cancelar.',NULL,'1. O sistema abre a tela Disponibilizar certidão.\n2. O sistema exibe os campos número da certidão, ano da certidão, identificação do solicitante, data da solicitação, setor responsável, tipo de certidão, email do solicitante, arquivo da certidão e observação.\n3. O Usuário chefe clica na opção Disponibilizar.\n','Disponibilizar certidão','disponibilizar certidão','Uma certidão disponibilizada.','O usuário cheve está acessando a tela gerenciar certidões.','Disponibilizar a funcionalidade a disponibilizar certidões para o usuário chefe.',1),(9,'Usuário chefe','O sistema deve comportar-se como descrito na seguinte seqüência de interações quando o (a) usuário chefe desejar gerenciar certidão.','2.1  O Usuário chefe escolhe o status da solicitação e o setor responsável.\n2.1.2  O usuário chefe marca a opção Apenas retificadoras.\n\n6.1 Se o status da solicitação for igual a \"Em processamento\" então \n6.1.1 O sistema exibe o botão Anexar certidão\n6.1.2 O sistema exibe o botão Rejeitar certidão.\n\n6.2 Se o status da solicitação for igual a \"Aguardando disponibilização\" então \n6.2.1 O sistema exibe o botão Anexar certidão\n6.2.2 O sistema exibe o botão disponibilizar certidão\n\n6.3 Se o status da solicitação for igual a \"rejeitada\" ou o status da solicitação for  igual a \"disponibilizada\" então \n6.3.1 O sistema exibe o botão Visualizar certidão.\n\n8.1 O Usuário chefe clica na opção Rejeitar solicitação\n8.1.1 O sistema executa o caso de uso Rejeitar solicitação.\n\n8.2 O Usuário chefe clica na opção Rejeitar certidão\n8.2.1 O sistema executa o caso de uso Rejeitar solicitação.\n\n8.3 O Usuário chefe clica na opção Disponibilizar certidão\n8.3.1 O sistema executa o caso de uso Disponibilizar certidão.',NULL,'1.  O sistema abre a tela Gerenciar Certidões.\n2.  O Usuário chefe escolhe o status da solicitação e o setor responsável.\n3.  O Usuário chefe clica na opção Pesquisar.\n4.  O Sistema filtra as solicitações com os campos de busca.\n5.  Para cada solicitação filtrada\n6.    O sistema exibe os campos identificação do solicitante, data da solicitação, retificadora, setor responsável, tipo de certidão, status da solicitação.\n7. O Usuário chefe seleciona uma solicitação\n8. O Usuário chefe clica no botão Anexar certidão.\n9. O sistema executa o caso de uso Anexar certidão.','Gerenciar certidão','gerenciar certidão','Um das operações ter sido executada.','O usuário cheve está acessando a tela gerenciar certidões.','Disponibilizar a funcionalidade gerenciar certidões para que o usuário chefe possa disponibilizar certidões, rejeitar solicitações, anexar certidões e disponibilizar certidão.',1);
/*!40000 ALTER TABLE `caso_de_uso` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-10 15:20:13
