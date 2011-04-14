-- MySQL dump 10.13  Distrib 5.5.9, for Win32 (x86)
--
-- Host: localhost    Database: proj_col
-- ------------------------------------------------------
-- Server version	5.5.9

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
-- Current Database: `proj_col`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `proj_col` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `proj_col`;

--
-- Table structure for table `canale`
--

DROP TABLE IF EXISTS `canale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `canale` (
  `id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `canale`
--

LOCK TABLES `canale` WRITE;
/*!40000 ALTER TABLE `canale` DISABLE KEYS */;
/*!40000 ALTER TABLE `canale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contracte_studiu`
--

DROP TABLE IF EXISTS `contracte_studiu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contracte_studiu` (
  `nrmat` int(11) NOT NULL AUTO_INCREMENT,
  `id_curs` int(11) NOT NULL,
  `nota` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`nrmat`),
  KEY `fk_contracte_studiu_cursuri1` (`id_curs`),
  CONSTRAINT `fk_contracte_studiu_studenti1` FOREIGN KEY (`nrmat`) REFERENCES `studenti` (`nr_matr`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contracte_studiu_cursuri1` FOREIGN KEY (`id_curs`) REFERENCES `cursuri` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contracte_studiu`
--

LOCK TABLES `contracte_studiu` WRITE;
/*!40000 ALTER TABLE `contracte_studiu` DISABLE KEYS */;
/*!40000 ALTER TABLE `contracte_studiu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conturi_studenti`
--

DROP TABLE IF EXISTS `conturi_studenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conturi_studenti` (
  `login_name` varchar(45) NOT NULL,
  `id_student` int(11) NOT NULL,
  PRIMARY KEY (`login_name`),
  UNIQUE KEY `un_student_id_unique` (`id_student`),
  KEY `fk_conturi_studenti_users1` (`login_name`),
  CONSTRAINT `fk_conturi_studenti_studenti1` FOREIGN KEY (`id_student`) REFERENCES `studenti` (`nr_matr`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_conturi_studenti_users1` FOREIGN KEY (`login_name`) REFERENCES `users` (`login_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conturi_studenti`
--

LOCK TABLES `conturi_studenti` WRITE;
/*!40000 ALTER TABLE `conturi_studenti` DISABLE KEYS */;
/*!40000 ALTER TABLE `conturi_studenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curicule`
--

DROP TABLE IF EXISTS `curicule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curicule` (
  `curs` int(11) NOT NULL,
  `sectie` int(11) NOT NULL,
  `semestru` int(11) NOT NULL,
  `credite` int(11) NOT NULL,
  `tip` varchar(15) NOT NULL,
  PRIMARY KEY (`curs`,`sectie`),
  KEY `fk_curicule_cursuri1` (`curs`),
  KEY `fk_curicule_sectii1` (`sectie`),
  CONSTRAINT `fk_curicule_cursuri1` FOREIGN KEY (`curs`) REFERENCES `cursuri` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_curicule_sectii1` FOREIGN KEY (`sectie`) REFERENCES `sectii` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curicule`
--

LOCK TABLES `curicule` WRITE;
/*!40000 ALTER TABLE `curicule` DISABLE KEYS */;
/*!40000 ALTER TABLE `curicule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursuri`
--

DROP TABLE IF EXISTS `cursuri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursuri` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nume` varchar(255) NOT NULL,
  `abreviere` varchar(12) NOT NULL,
  `version` int(11) NOT NULL,
  `main_page` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cursuri_resurse1` (`main_page`),
  CONSTRAINT `fk_cursuri_resurse1` FOREIGN KEY (`main_page`) REFERENCES `resurse` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursuri`
--

LOCK TABLES `cursuri` WRITE;
/*!40000 ALTER TABLE `cursuri` DISABLE KEYS */;
/*!40000 ALTER TABLE `cursuri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facultati`
--

DROP TABLE IF EXISTS `facultati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facultati` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nume` varchar(45) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facultati`
--

LOCK TABLES `facultati` WRITE;
/*!40000 ALTER TABLE `facultati` DISABLE KEYS */;
INSERT INTO `facultati` VALUES (1,'matematica',1);
/*!40000 ALTER TABLE `facultati` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupe`
--

DROP TABLE IF EXISTS `grupe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupe` (
  `id` varchar(5) NOT NULL,
  `sectie` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grupe_sectii1` (`sectie`),
  CONSTRAINT `fk_grupe_sectii1` FOREIGN KEY (`sectie`) REFERENCES `sectii` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupe`
--

LOCK TABLES `grupe` WRITE;
/*!40000 ALTER TABLE `grupe` DISABLE KEYS */;
INSERT INTO `grupe` VALUES ('mf331',1);
/*!40000 ALTER TABLE `grupe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesaje`
--

DROP TABLE IF EXISTS `mesaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mesaje` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `msg` text NOT NULL,
  `expeditor` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mesaje_users1` (`expeditor`),
  CONSTRAINT `fk_mesaje_users1` FOREIGN KEY (`expeditor`) REFERENCES `users` (`login_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesaje`
--

LOCK TABLES `mesaje` WRITE;
/*!40000 ALTER TABLE `mesaje` DISABLE KEYS */;
/*!40000 ALTER TABLE `mesaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msg_chan`
--

DROP TABLE IF EXISTS `msg_chan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msg_chan` (
  `msgid` int(11) NOT NULL,
  `chanid` varchar(45) NOT NULL,
  PRIMARY KEY (`msgid`,`chanid`),
  KEY `fk_msg_chan_canale1` (`chanid`),
  KEY `fk_msg_chan_mesaje1` (`msgid`),
  CONSTRAINT `fk_msg_chan_canale1` FOREIGN KEY (`chanid`) REFERENCES `canale` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_msg_chan_mesaje1` FOREIGN KEY (`msgid`) REFERENCES `mesaje` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msg_chan`
--

LOCK TABLES `msg_chan` WRITE;
/*!40000 ALTER TABLE `msg_chan` DISABLE KEYS */;
/*!40000 ALTER TABLE `msg_chan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orar`
--

DROP TABLE IF EXISTS `orar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orar` (
  `id` int(11) NOT NULL,
  `grupa` varchar(5) NOT NULL,
  `curs_id` int(11) NOT NULL,
  `tip_activitate` varchar(45) NOT NULL,
  `zi` tinyint(4) NOT NULL,
  `saptamana` tinyint(4) NOT NULL,
  `start_ora` tinyint(4) NOT NULL,
  `end_ora` tinyint(4) NOT NULL,
  `sala` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orar_grupe1` (`grupa`),
  KEY `fk_orar_cursuri1` (`curs_id`),
  CONSTRAINT `fk_orar_grupe1` FOREIGN KEY (`grupa`) REFERENCES `grupe` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orar_cursuri1` FOREIGN KEY (`curs_id`) REFERENCES `cursuri` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orar`
--

LOCK TABLES `orar` WRITE;
/*!40000 ALTER TABLE `orar` DISABLE KEYS */;
/*!40000 ALTER TABLE `orar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persoane`
--

DROP TABLE IF EXISTS `persoane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persoane` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cnp` varchar(13) DEFAULT NULL,
  `pasaport` varchar(45) DEFAULT NULL,
  `prenume` varchar(45) DEFAULT NULL,
  `nume` varchar(45) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cnp_unique` (`cnp`),
  UNIQUE KEY `pass_unique` (`pasaport`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persoane`
--

LOCK TABLES `persoane` WRITE;
/*!40000 ALTER TABLE `persoane` DISABLE KEYS */;
INSERT INTO `persoane` VALUES (1,'1831104060023','251452','mihai daniel','andrei',1),(2,'1900106223','25416','geza','kovacs',1),(3,'19011','123456','diana','troanca',1);
/*!40000 ALTER TABLE `persoane` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resurse`
--

DROP TABLE IF EXISTS `resurse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resurse` (
  `id` int(11) NOT NULL,
  `file_name` varchar(45) DEFAULT NULL,
  `wikitext` text,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resurse`
--

LOCK TABLES `resurse` WRITE;
/*!40000 ALTER TABLE `resurse` DISABLE KEYS */;
/*!40000 ALTER TABLE `resurse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sectii`
--

DROP TABLE IF EXISTS `sectii`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sectii` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `facultate` int(11) NOT NULL,
  `nume` varchar(45) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sectii_facultati1` (`facultate`),
  CONSTRAINT `fk_sectii_facultati1` FOREIGN KEY (`facultate`) REFERENCES `facultati` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sectii`
--

LOCK TABLES `sectii` WRITE;
/*!40000 ALTER TABLE `sectii` DISABLE KEYS */;
INSERT INTO `sectii` VALUES (1,1,'mate-fizica',1);
/*!40000 ALTER TABLE `sectii` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studenti`
--

DROP TABLE IF EXISTS `studenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studenti` (
  `nr_matr` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `grupaId` varchar(5) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`nr_matr`),
  UNIQUE KEY `uniq_login` (`login`),
  KEY `fk_studenti_grupe` (`grupaId`),
  CONSTRAINT `fk_studenti_grupe` FOREIGN KEY (`grupaId`) REFERENCES `grupe` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_studenti_users` FOREIGN KEY (`login`) REFERENCES `users` (`login_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1041 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studenti`
--

LOCK TABLES `studenti` WRITE;
/*!40000 ALTER TABLE `studenti` DISABLE KEYS */;
INSERT INTO `studenti` VALUES (1040,'mihai','mf331',0);
/*!40000 ALTER TABLE `studenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscriptii`
--

DROP TABLE IF EXISTS `subscriptii`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscriptii` (
  `id_canal` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  PRIMARY KEY (`id_canal`,`login`),
  KEY `fk_subscriptii_users1` (`login`),
  KEY `fk_subscriptii_canale1` (`id_canal`),
  CONSTRAINT `fk_subscriptii_users1` FOREIGN KEY (`login`) REFERENCES `users` (`login_name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_subscriptii_canale1` FOREIGN KEY (`id_canal`) REFERENCES `canale` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscriptii`
--

LOCK TABLES `subscriptii` WRITE;
/*!40000 ALTER TABLE `subscriptii` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscriptii` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teme`
--

DROP TABLE IF EXISTS `teme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teme` (
  `id` int(11) NOT NULL,
  `curs` int(11) NOT NULL,
  `cerinte_page` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_teme_cursuri1` (`curs`),
  CONSTRAINT `fk_teme_cursuri1` FOREIGN KEY (`curs`) REFERENCES `cursuri` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teme`
--

LOCK TABLES `teme` WRITE;
/*!40000 ALTER TABLE `teme` DISABLE KEYS */;
/*!40000 ALTER TABLE `teme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tm_student`
--

DROP TABLE IF EXISTS `tm_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tm_student` (
  `tema` int(11) NOT NULL,
  `nrmatr` int(11) NOT NULL,
  `status` varchar(6) NOT NULL,
  `deadline` datetime DEFAULT NULL,
  PRIMARY KEY (`tema`,`nrmatr`),
  KEY `fk_tm_student_teme1` (`tema`),
  KEY `fk_tm_student_studenti1` (`nrmatr`),
  CONSTRAINT `fk_tm_student_teme1` FOREIGN KEY (`tema`) REFERENCES `teme` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tm_student_studenti1` FOREIGN KEY (`nrmatr`) REFERENCES `studenti` (`nr_matr`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tm_student`
--

LOCK TABLES `tm_student` WRITE;
/*!40000 ALTER TABLE `tm_student` DISABLE KEYS */;
/*!40000 ALTER TABLE `tm_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `login_name` varchar(45) NOT NULL,
  `password_hash` varchar(45) NOT NULL,
  `salt` varchar(45) NOT NULL,
  `lastlogin` varchar(45) DEFAULT NULL,
  `lastip` varchar(45) DEFAULT NULL,
  `persoana` int(11) NOT NULL,
  PRIMARY KEY (`login_name`),
  KEY `fk_users_persoane1` (`persoana`),
  CONSTRAINT `fk_users_persoane1` FOREIGN KEY (`persoana`) REFERENCES `persoane` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('didy','didy','todo',NULL,NULL,3),('geza','geza','todo',NULL,NULL,2),('mihai','mihai','todo',NULL,NULL,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-04-14 13:18:59
