-- MySQL dump 10.13  Distrib 5.5.9, for Win32 (x86)
--
-- Host: localhost    Database: proj_rosu
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
-- Current Database: `proj_rosu`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `proj_rosu` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `proj_rosu`;

--
-- Table structure for table `contracte_studiu`
--

DROP TABLE IF EXISTS `contracte_studiu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contracte_studiu` (
  `nrmat` int(11) NOT NULL,
  `id_curs` int(11) NOT NULL,
  `contract_version` int(11) NOT NULL,
  `nota` float DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`nrmat`,`id_curs`,`contract_version`),
  KEY `fk_contracte_studiu_cursuri1` (`id_curs`),
  CONSTRAINT `fk_contracte_studiu_studenti1` FOREIGN KEY (`nrmat`) REFERENCES `studenti` (`nr_matr`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_contracte_studiu_cursuri1` FOREIGN KEY (`id_curs`) REFERENCES `cur_course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contracte_studiu`
--

LOCK TABLES `contracte_studiu` WRITE;
/*!40000 ALTER TABLE `contracte_studiu` DISABLE KEYS */;
INSERT INTO `contracte_studiu` VALUES (1040,1,1,4,'2009-04-01 00:00:00'),(1040,1,2,5,'2009-09-01 00:00:00'),(1040,1,3,NULL,NULL),(1040,1,4,NULL,NULL),(1040,1,5,NULL,NULL),(1040,5,2,NULL,NULL),(1040,5,5,NULL,NULL),(1040,7,3,5,NULL),(1040,7,4,NULL,NULL),(1040,7,5,NULL,NULL),(1040,9,4,NULL,NULL),(1040,9,5,NULL,NULL),(1040,12,5,NULL,NULL),(1040,16,5,NULL,NULL);
/*!40000 ALTER TABLE `contracte_studiu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cur_course`
--

DROP TABLE IF EXISTS `cur_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cur_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `abbrev` varchar(12) NOT NULL,
  `version` int(11) NOT NULL,
  `main_page` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cursuri_resurse1` (`main_page`),
  CONSTRAINT `fk_cursuri_resurse1` FOREIGN KEY (`main_page`) REFERENCES `resource` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cur_course`
--

LOCK TABLES `cur_course` WRITE;
/*!40000 ALTER TABLE `cur_course` DISABLE KEYS */;
INSERT INTO `cur_course` VALUES (1,'Tehnici de optimizare','Optimizare',1,NULL),(5,'Inteligenta Artificiala','AI',1,NULL),(6,'Retele de calculatoare','retele',1,NULL),(7,'Programare orientata obiect','OOP',1,NULL),(8,'Ingineria sistemelor software','ISS',1,NULL),(9,'Algoritmi si structuri de date','Algoritmi',1,NULL),(10,'Metodologia redactarii unei lucrari stiintifice','Paper',1,NULL),(11,'Sisteme de operare','SO',1,NULL),(12,'Proiect colectiv','proj col',1,NULL),(13,'Branzeturi, metode industriale','BRZ',1,NULL),(14,'Metode moderne de abordare a cartofului','CRTF',1,NULL),(15,'Razboaiele luminii','lumi',1,NULL),(16,'despre gavitzapa','gravitzapa',1,NULL),(17,'kin dza dza','dza dza',1,NULL);
/*!40000 ALTER TABLE `cur_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cur_curicul`
--

DROP TABLE IF EXISTS `cur_curicul`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cur_curicul` (
  `groupid` int(11) NOT NULL,
  `section` int(11) NOT NULL,
  PRIMARY KEY (`groupid`,`section`),
  KEY `fk_curicule_sectii1` (`section`),
  KEY `fk_cur_curicule_cur_grup1` (`groupid`),
  CONSTRAINT `fk_curicule_sectii1` FOREIGN KEY (`section`) REFERENCES `org_section` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cur_curicule_cur_grup1` FOREIGN KEY (`groupid`) REFERENCES `cur_grup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cur_curicul`
--

LOCK TABLES `cur_curicul` WRITE;
/*!40000 ALTER TABLE `cur_curicul` DISABLE KEYS */;
INSERT INTO `cur_curicul` VALUES (1,1),(2,1),(3,1),(6,1),(7,1);
/*!40000 ALTER TABLE `cur_curicul` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cur_grup`
--

DROP TABLE IF EXISTS `cur_grup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cur_grup` (
  `id` int(11) NOT NULL,
  `exclusiv` tinyint(1) NOT NULL,
  `semester` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cur_grup`
--

LOCK TABLES `cur_grup` WRITE;
/*!40000 ALTER TABLE `cur_grup` DISABLE KEYS */;
INSERT INTO `cur_grup` VALUES (1,0,2),(2,0,4),(3,0,6),(6,1,4),(7,1,6);
/*!40000 ALTER TABLE `cur_grup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cur_grup_cours`
--

DROP TABLE IF EXISTS `cur_grup_cours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cur_grup_cours` (
  `groupid` int(11) NOT NULL,
  `courseid` int(11) NOT NULL,
  `ncredits` int(11) NOT NULL,
  PRIMARY KEY (`groupid`,`courseid`),
  KEY `fk_cur_grup_cours_cur_grup1` (`groupid`),
  KEY `fk_cur_grup_cours_cur_course1` (`courseid`),
  CONSTRAINT `fk_cur_grup_cours_cur_grup1` FOREIGN KEY (`groupid`) REFERENCES `cur_grup` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cur_grup_cours_cur_course1` FOREIGN KEY (`courseid`) REFERENCES `cur_course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cur_grup_cours`
--

LOCK TABLES `cur_grup_cours` WRITE;
/*!40000 ALTER TABLE `cur_grup_cours` DISABLE KEYS */;
INSERT INTO `cur_grup_cours` VALUES (1,7,6),(1,9,4),(2,11,5),(3,1,6),(3,5,6),(3,6,6),(3,8,6),(3,12,3),(6,13,4),(6,14,4),(6,15,4),(7,16,4),(7,17,4);
/*!40000 ALTER TABLE `cur_grup_cours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msg_channel`
--

DROP TABLE IF EXISTS `msg_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msg_channel` (
  `id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msg_channel`
--

LOCK TABLES `msg_channel` WRITE;
/*!40000 ALTER TABLE `msg_channel` DISABLE KEYS */;
INSERT INTO `msg_channel` VALUES ('MF331'),('mihai');
/*!40000 ALTER TABLE `msg_channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msg_message`
--

DROP TABLE IF EXISTS `msg_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msg_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `msg` text NOT NULL,
  `expeditor` varchar(45) NOT NULL,
  `level` varchar(10) NOT NULL DEFAULT 'INFO',
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mesaje_users1` (`expeditor`),
  CONSTRAINT `fk_mesaje_users1` FOREIGN KEY (`expeditor`) REFERENCES `users` (`login_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msg_message`
--

LOCK TABLES `msg_message` WRITE;
/*!40000 ALTER TABLE `msg_message` DISABLE KEYS */;
INSERT INTO `msg_message` VALUES (1,'<p>Yesterday,<br>All those backups seemed a waste of pay.<br>Now my database has gone away.<br>Oh I believe in yesterday.</p>','mihai','INFO','2011-04-17 00:00:00'),(2,'You can never find general mechanical means <br>for predicting the acts of computing machines <br>Itâ€™s something that cannot be done. So we users <br>must find our own bugs. Our computers are losers! <br><a target=\"_blank\" href=\"http://www.lel.ed.ac.uk/~gpullum/loopsnoop.pdf\"> source </a>','mihai','INFO','2011-04-11 11:20:00'),(3,'I have no money, no resources, no hopes. I am the happiest man alive.','system','INFO','2011-04-09 11:20:00'),(4,'Debugging is twice as hard as writing the code in the first place.  Therefore, if you write the code as cleverly as possible, you are&ndash;by definition&ndash;not smart enough to debug it.','system','INFO','2011-03-11 11:20:00'),(5,'Atunci cÃ¢nd sunt supÄƒrat, mÄƒ retrag Ã®ntre oile mele ÅŸi mÄƒ liniÅŸtesc','system','INFO','2010-04-11 11:20:00');
/*!40000 ALTER TABLE `msg_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msg_message_channel`
--

DROP TABLE IF EXISTS `msg_message_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msg_message_channel` (
  `msgid` int(11) NOT NULL,
  `chanid` varchar(45) NOT NULL,
  PRIMARY KEY (`msgid`,`chanid`),
  KEY `fk_msg_chan_canale1` (`chanid`),
  KEY `fk_msg_chan_mesaje1` (`msgid`),
  CONSTRAINT `fk_msg_chan_canale1` FOREIGN KEY (`chanid`) REFERENCES `msg_channel` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_msg_chan_mesaje1` FOREIGN KEY (`msgid`) REFERENCES `msg_message` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msg_message_channel`
--

LOCK TABLES `msg_message_channel` WRITE;
/*!40000 ALTER TABLE `msg_message_channel` DISABLE KEYS */;
INSERT INTO `msg_message_channel` VALUES (1,'MF331'),(2,'MF331'),(3,'mihai'),(4,'MF331'),(5,'MF331'),(5,'mihai');
/*!40000 ALTER TABLE `msg_message_channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msg_subscription`
--

DROP TABLE IF EXISTS `msg_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `msg_subscription` (
  `login` varchar(45) NOT NULL,
  `chanid` varchar(45) NOT NULL,
  PRIMARY KEY (`login`,`chanid`),
  KEY `fk_subscriptii_users1` (`login`),
  KEY `fk_subscriptii_canale1` (`chanid`),
  CONSTRAINT `fk_subscriptii_canale1` FOREIGN KEY (`chanid`) REFERENCES `msg_channel` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_subscriptii_users1` FOREIGN KEY (`login`) REFERENCES `users` (`login_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msg_subscription`
--

LOCK TABLES `msg_subscription` WRITE;
/*!40000 ALTER TABLE `msg_subscription` DISABLE KEYS */;
INSERT INTO `msg_subscription` VALUES ('mihai','MF331'),('mihai','mihai');
/*!40000 ALTER TABLE `msg_subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orar`
--

DROP TABLE IF EXISTS `orar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
  CONSTRAINT `fk_orar_cursuri1` FOREIGN KEY (`curs_id`) REFERENCES `cur_course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orar_grupe1` FOREIGN KEY (`grupa`) REFERENCES `org_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orar`
--

LOCK TABLES `orar` WRITE;
/*!40000 ALTER TABLE `orar` DISABLE KEYS */;
INSERT INTO `orar` VALUES (1,'mf331',1,'curs',1,0,14,16,'5/I'),(2,'mf331',1,'sem',1,2,16,18,'5/I'),(3,'mf331',6,'curs',2,0,8,10,'2/I'),(4,'mf311',7,'lab',2,1,8,10,'L336'),(5,'mf331',8,'curs',2,0,10,12,'2/I'),(6,'mf331',5,'curs',2,0,14,16,'2/I'),(7,'mf311',9,'curs',2,0,14,16,'7/I'),(8,'mf331',6,'lab',3,0,8,10,'L302'),(9,'mf331',8,'lab',3,1,10,12,'L339'),(10,'mf331',5,'lab',3,2,10,12,'L302'),(11,'mf331',12,'lab',3,0,12,14,'L307'),(12,'mf331',10,'curs',4,2,8,10,'5/I'),(13,'mf331',8,'sem',4,2,10,12,'7/I'),(14,'mf331',5,'sem',4,2,12,14,'5/I'),(15,'mf321',11,'curs',4,0,14,16,'C310'),(16,'mf321',11,'lab',4,0,16,18,'L339'),(17,'mf311',7,'curs',5,0,8,10,'C335'),(18,'mf311',7,'sem',5,1,14,16,'C509'),(19,'mf311',9,'sem',5,2,14,16,'C509'),(20,'mf311',7,'lab',5,2,16,18,'L307');
/*!40000 ALTER TABLE `orar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org_faculty`
--

DROP TABLE IF EXISTS `org_faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `org_faculty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_faculty`
--

LOCK TABLES `org_faculty` WRITE;
/*!40000 ALTER TABLE `org_faculty` DISABLE KEYS */;
INSERT INTO `org_faculty` VALUES (1,'matematica');
/*!40000 ALTER TABLE `org_faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org_group`
--

DROP TABLE IF EXISTS `org_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `org_group` (
  `id` varchar(5) NOT NULL,
  `section` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grupe_sectii1` (`section`),
  CONSTRAINT `fk_grupe_sectii1` FOREIGN KEY (`section`) REFERENCES `org_section` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_group`
--

LOCK TABLES `org_group` WRITE;
/*!40000 ALTER TABLE `org_group` DISABLE KEYS */;
INSERT INTO `org_group` VALUES ('mf311',1),('mf321',1),('mf331',1);
/*!40000 ALTER TABLE `org_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `org_section`
--

DROP TABLE IF EXISTS `org_section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `org_section` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `faculty` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sectii_facultati1` (`faculty`),
  CONSTRAINT `fk_sectii_facultati1` FOREIGN KEY (`faculty`) REFERENCES `org_faculty` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `org_section`
--

LOCK TABLES `org_section` WRITE;
/*!40000 ALTER TABLE `org_section` DISABLE KEYS */;
INSERT INTO `org_section` VALUES (1,1,'mate-fizica');
/*!40000 ALTER TABLE `org_section` ENABLE KEYS */;
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
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL,
  `file_name` varchar(45) DEFAULT NULL,
  `wikitext` text,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
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
  CONSTRAINT `fk_studenti_grupe` FOREIGN KEY (`grupaId`) REFERENCES `org_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_studenti_users` FOREIGN KEY (`login`) REFERENCES `users` (`login_name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1041 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studenti`
--

LOCK TABLES `studenti` WRITE;
/*!40000 ALTER TABLE `studenti` DISABLE KEYS */;
INSERT INTO `studenti` VALUES (1040,'mihai','mf331',2);
/*!40000 ALTER TABLE `studenti` ENABLE KEYS */;
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
  CONSTRAINT `fk_teme_cursuri1` FOREIGN KEY (`curs`) REFERENCES `cur_course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  CONSTRAINT `fk_tm_student_studenti1` FOREIGN KEY (`nrmatr`) REFERENCES `studenti` (`nr_matr`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tm_student_teme1` FOREIGN KEY (`tema`) REFERENCES `teme` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `persoana` int(11) DEFAULT NULL,
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
INSERT INTO `users` VALUES ('didy','didy','todo',NULL,NULL,3),('geza','geza','todo',NULL,NULL,2),('mihai','mihai','todo',NULL,NULL,1),('system','golomoz never login golomoz','golomoz',NULL,NULL,NULL);
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

-- Dump completed on 2011-04-18 13:01:37
