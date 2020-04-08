-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: dbprogetto
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `riunione`
--

DROP TABLE IF EXISTS `riunione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `riunione` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titolo` varchar(45) NOT NULL,
  `anno` int(11) DEFAULT NULL,
  `mese` int(11) NOT NULL,
  `giorno` int(11) NOT NULL,
  `ora` int(11) NOT NULL,
  `durata` int(11) NOT NULL,
  `maxPart` int(11) NOT NULL,
  `creatore` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `creatore` (`creatore`),
  CONSTRAINT `riunione_ibfk_1` FOREIGN KEY (`creatore`) REFERENCES `utente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `riunione`
--

LOCK TABLES `riunione` WRITE;
/*!40000 ALTER TABLE `riunione` DISABLE KEYS */;
INSERT INTO `riunione` VALUES (1,'Riunione1',2020,4,10,21,2,4,1),(2,'Riunione2',2020,4,12,18,4,4,1),(3,'Riunione3',2020,4,15,16,1,4,2),(4,'Riunione4',2020,4,17,10,3,4,2),(5,'aaa',2020,2,1,3,4,4,2),(6,'Prova',2020,7,2,20,2,4,2),(7,'aaaaaaa',2020,22,2,2,2,4,1),(8,'Prova1',2020,3,2,4,5,4,2),(9,'Prova3',2020,5,4,13,2,4,2),(10,'R1',2020,5,5,5,5,4,4),(11,'r2',2020,5,5,5,5,4,4),(12,'gg',2020,3,3,4,6,4,5),(13,'ppp',2020,3,2,4,5,4,1),(14,'a',2020,3,17,3,3,4,2),(15,'ppp',2020,3,4,5,5,4,6),(16,'ggg',2020,4,3,5,6,4,6),(17,'rrr',2020,2,2,2,2,4,2);
/*!40000 ALTER TABLE `riunione` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-08 15:31:13
