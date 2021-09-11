-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: vietnhpc01231_db
-- ------------------------------------------------------
-- Server version	8.0.26
DROP database IF EXISTS `vietnhpc01231_db`;
create database `vietnhpc01231_db`;
use `vietnhpc01231_db`;

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` char(36) NOT NULL,
  `id_parent` char(36) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enable` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_parent` (`id_parent`),
  CONSTRAINT `category_ibfk_1` FOREIGN KEY (`id_parent`) REFERENCES `parent_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('2229df60-9f89-406d-b013-406605739ed8','dba1eb24-8852-4837-8683-b65aa5b8534a','Samsum',_binary ''),('330b34eb-ef44-457d-b584-7a6e8bf79a16','c0b72385-6d4e-45ba-984d-e1ae7cce92b4','Dell',_binary ''),('c143f2a5-3958-426c-8a24-baa748c6f024','c0b72385-6d4e-45ba-984d-e1ae7cce92b4','Lenovo',_binary ''),('e926b893-c33e-4cf3-9404-8d2763ffc04b','dba1eb24-8852-4837-8683-b65aa5b8534a','Nokia',_binary '');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parent_category`
--

DROP TABLE IF EXISTS `parent_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parent_category` (
  `id` char(36) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enable` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parent_category`
--

LOCK TABLES `parent_category` WRITE;
/*!40000 ALTER TABLE `parent_category` DISABLE KEYS */;
INSERT INTO `parent_category` VALUES ('c0b72385-6d4e-45ba-984d-e1ae7cce92b4','Laptop',_binary ''),('d2d0f573-b059-4874-b3cb-de8bb6d10eae','Siêu xe',_binary ''),('dba1eb24-8852-4837-8683-b65aa5b8534a','Điện thoại',_binary '');
/*!40000 ALTER TABLE `parent_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` char(36) NOT NULL,
  `id_category` char(36) NOT NULL DEFAULT '',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enable` bit(1) NOT NULL,
  `price` double(16,2) DEFAULT '0.00',
  `quantity` int NOT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_category` (`id_category`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('25934603-fd93-41f6-8c28-7a17716dd46f','c143f2a5-3958-426c-8a24-baa748c6f024','Laptop lenovo',_binary '',0.00,0,'1d3ec3e4.jpg'),('461321c9-4728-46f5-94be-745750e988f6','c143f2a5-3958-426c-8a24-baa748c6f024','Laptop xịn',_binary '',3213.00,3213,'a88328f5.jpg'),('47de2b5f-318a-4161-9a44-352a4c4e57c0','330b34eb-ef44-457d-b584-7a6e8bf79a16','Laptop mới thêm',_binary '',7000.00,100,'dbb7e69a.jpg'),('8fe0257c-4813-47d0-a5b9-5445a320a604','e926b893-c33e-4cf3-9404-8d2763ffc04b','Điện Thoại Nokia',_binary '',3213.00,123,'78a41a01.jpg'),('921f4ebf-a847-4ca6-85c5-10087ed55d6a','e926b893-c33e-4cf3-9404-8d2763ffc04b','Điện Thoại mới',_binary '',3213.00,2313,'e7d6df4.jpg'),('92782fe4-c9a3-4c78-a0c7-a4950d74b223','e926b893-c33e-4cf3-9404-8d2763ffc04b','Điện Thoại cũ',_binary '',3213.00,3213,'b2184fdf.jpg'),('9faffda8-7ba3-45c7-a587-ccd96b40037c','330b34eb-ef44-457d-b584-7a6e8bf79a16','Laptop Dell',_binary '',1.00,1,'3d471dd1.jpg'),('c597574d-cbf0-4864-baef-2bd552a3d7a8','2229df60-9f89-406d-b013-406605739ed8','Điện Thoại loại 1',_binary '\0',3123.00,213,'f0783383.jpg'),('c86a4bc1-097d-4cf1-896b-9a038ee6353f','e926b893-c33e-4cf3-9404-8d2763ffc04b','Điện Thoại vip',_binary '',1.00,1,'324365eb.jpg');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_user`
--

DROP TABLE IF EXISTS `role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `id_user` varchar(30) NOT NULL,
  `id_role` varchar(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `keyu` (`id_user`,`id_role`),
  UNIQUE KEY `UKr4oxad88wtqy9tdkcrg1f4hvw` (`id_user`,`id_role`),
  KEY `id_role` (`id_role`),
  CONSTRAINT `role_user_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`username`),
  CONSTRAINT `role_user_ibfk_2` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_user`
--

LOCK TABLES `role_user` WRITE;
/*!40000 ALTER TABLE `role_user` DISABLE KEYS */;
INSERT INTO `role_user` VALUES (55,'2','US'),(61,'22','US'),(56,'3','US'),(50,'admin','AD'),(62,'admin2','US'),(63,'admin3','US'),(64,'admin4','US'),(65,'admin5','US'),(66,'admin6','US'),(67,'admin7','US'),(51,'pm2','PM'),(72,'user','US');
/*!40000 ALTER TABLE `role_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` varchar(5) NOT NULL,
  `name` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES ('AD','ROLE_ADMIN'),('PM','ROLE_PM'),('US','ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `fullname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('2','2','2','vitsilo03@gmail.com','01234567890',_binary ''),('22','2','22','2@g','01234567890',_binary ''),('3','3','33','111@111.com','01234567890',_binary ''),('admin','admin','2313','3123@fdasd.com','01234567897',_binary '\0'),('admin2','admin','2313','3123@fdasd.com','01234567897',_binary '\0'),('admin3','admin','2313','3123@fdasd.com','01234567897',_binary '\0'),('admin4','admin','2313','3123@fdasd.com','01234567897',_binary '\0'),('admin5','admin','2313','3123@fdasd.com','01234567897',_binary '\0'),('admin6','admin','2313','3123@fdasd.com','01234567897',_binary '\0'),('admin7','admin','2313','3123@fdasd.com','01234567897',_binary '\0'),('pm2','admin','123','123@gmail.com','01234567891',_binary '\0'),('user','user','2313','111@111.com','01234567897',_binary '\0');
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

-- Dump completed on 2021-09-11  1:43:51
