-- MySQL dump 10.13  Distrib 5.5.41, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: muzickagroznicadb
-- ------------------------------------------------------
-- Server version	5.5.41-0ubuntu0.14.04.1

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
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artist` (
  `name` varchar(90) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES ('neki'),('Scott and Dave'),('Јођа'),('Рибља чорба'),('Сарс'),('Ујка чен');
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_text` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `commenting_time` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `music_content_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_1_idx` (`user_id`),
  KEY `fk_comment_2_idx` (`music_content_id`),
  CONSTRAINT `fk_comment_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_2` FOREIGN KEY (`music_content_id`) REFERENCES `music_content` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (5,'Тест2','2015-03-31 14:52:24',6,16),(12,'Веома добра пјесма','2015-03-31 18:00:37',6,16),(14,'Стара школа','2015-03-31 18:03:10',6,15),(16,'Лош квалитет','2015-03-31 18:04:21',6,15),(17,'Сатро си са овом','2015-03-31 18:05:12',12,16);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `location` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `publish_time` datetime NOT NULL,
  `event_time` datetime NOT NULL,
  `approval_status` int(11) NOT NULL DEFAULT '0',
  `creator_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_event_1_idx` (`creator_id`),
  CONSTRAINT `fk_event_1` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'Пијанка','Нови Град','зар је стварно потребан опис','2015-03-26 21:50:45','2015-03-27 21:50:25',-1,6),(2,'Идемо кући','Станица','Идемо кућа, кажем','2015-03-27 17:47:22','2015-03-27 18:30:58',-1,6),(3,'Неки догађај','СЦ Никола Тесла Бања Лука','Радћемо нешто','2015-04-01 15:41:03','2015-04-02 15:39:59',1,12),(4,'Neki','sodijfsd','sdfnj','2015-04-01 16:24:18','2015-04-01 16:23:36',1,6),(5,'sdfdsa','asdfasdf','asdfasdf','2015-04-01 16:27:50','2015-04-02 16:27:42',1,6),(6,'adohvaodifj','odifvodijfvdfioj','dofhvodsijf','2015-04-01 16:30:39','2015-04-01 16:30:28',1,6),(7,'adohvaodifj','odifvodijfvdfioj','dofhvodsijf','2015-04-01 16:34:07','2015-04-01 16:34:05',1,6),(8,'адфоиј','диоајдфгоијдф','дофигј','2015-04-01 16:59:34','2015-04-02 16:58:44',1,6),(9,'adfasdf','adfasdf','adfadsf','2015-04-01 17:03:37','2015-04-01 17:03:33',1,6);
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `favoriting_time` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `music_content_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `favorite_u_mc` (`user_id`,`music_content_id`),
  KEY `fk_favorite_2_idx` (`music_content_id`),
  CONSTRAINT `fk_favorite_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_favorite_2` FOREIGN KEY (`music_content_id`) REFERENCES `music_content` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite`
--

LOCK TABLES `favorite` WRITE;
/*!40000 ALTER TABLE `favorite` DISABLE KEYS */;
INSERT INTO `favorite` VALUES (4,'2015-03-29 20:08:32',6,16),(5,'2015-04-04 13:35:16',6,17);
/*!40000 ALTER TABLE `favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `name` varchar(90) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES ('Електро'),('Етно'),('Забавна'),('Метал'),('Народна'),('Рок'),('Хаус');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listening`
--

DROP TABLE IF EXISTS `listening`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `listening` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `listening_time` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `music_content_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_listening_2_idx` (`music_content_id`),
  KEY `index4` (`user_id`),
  CONSTRAINT `fk_listening_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_listening_2` FOREIGN KEY (`music_content_id`) REFERENCES `music_content` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listening`
--

LOCK TABLES `listening` WRITE;
/*!40000 ALTER TABLE `listening` DISABLE KEYS */;
INSERT INTO `listening` VALUES (1,'2015-04-01 17:25:59',6,16),(3,'2015-04-01 17:52:48',6,16),(4,'2015-04-01 17:53:35',6,17),(5,'2015-04-01 17:56:10',6,17),(6,'2015-04-02 20:26:14',6,16),(7,'2015-04-02 20:26:55',6,16),(8,'2015-04-02 20:30:52',6,16),(9,'2015-04-02 20:31:06',6,16),(10,'2015-04-02 20:32:35',6,16),(11,'2015-04-02 20:37:55',6,16),(12,'2015-04-02 20:38:08',6,16),(13,'2015-04-02 20:38:51',6,16),(14,'2015-04-02 20:39:11',6,16),(15,'2015-04-02 20:40:37',6,16),(16,'2015-04-02 20:40:50',6,16),(17,'2015-04-02 20:41:35',6,16),(18,'2015-04-02 20:48:19',6,16),(19,'2015-04-02 21:01:01',6,16),(20,'2015-04-02 21:01:20',6,16),(21,'2015-04-02 21:04:09',6,16),(22,'2015-04-02 21:08:31',6,16),(23,'2015-04-02 21:08:36',6,16),(24,'2015-04-02 21:09:52',6,16),(25,'2015-04-02 21:11:33',6,16),(26,'2015-04-02 21:17:54',6,16),(27,'2015-04-02 21:19:07',6,16),(28,'2015-04-02 21:19:20',6,16),(29,'2015-04-02 21:23:17',6,17),(30,'2015-04-02 23:21:37',6,17),(31,'2015-04-02 23:38:18',6,16),(32,'2015-04-02 23:41:30',6,16),(33,'2015-04-02 23:41:56',6,15),(34,'2015-04-04 12:15:02',6,16),(35,'2015-04-04 13:17:42',6,16),(36,'2015-04-04 13:18:16',12,16),(37,'2015-04-04 13:18:39',12,16),(38,'2015-04-04 13:31:57',6,15),(39,'2015-04-04 13:35:14',6,17),(43,'2015-04-05 16:32:02',6,16),(44,'2015-04-05 16:33:50',6,17);
/*!40000 ALTER TABLE `listening` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music_content`
--

DROP TABLE IF EXISTS `music_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `music_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `publish_time` datetime NOT NULL,
  `lyrics` varchar(2048) COLLATE utf8_unicode_ci NOT NULL,
  `duration` time NOT NULL,
  `content_path` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `content_type` int(11) NOT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `artist_name` varchar(90) COLLATE utf8_unicode_ci NOT NULL,
  `genre_name` varchar(90) COLLATE utf8_unicode_ci NOT NULL,
  `publisher_id` int(11) NOT NULL,
  `extra_info` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_music_content_1_idx` (`artist_name`),
  KEY `fk_music_content_2_idx` (`genre_name`),
  KEY `fk_music_content_3_idx` (`publisher_id`),
  CONSTRAINT `fk_music_content_1` FOREIGN KEY (`artist_name`) REFERENCES `artist` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_music_content_2` FOREIGN KEY (`genre_name`) REFERENCES `genre` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_music_content_3` FOREIGN KEY (`publisher_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music_content`
--

LOCK TABLES `music_content` WRITE;
/*!40000 ALTER TABLE `music_content` DISABLE KEYS */;
INSERT INTO `music_content` VALUES (15,'Рибља чорба - спотови','2015-03-28 23:08:20','спотови..............','02:50:39','https://www.youtube.com/watch?v=Km89WVJlhFU',1,'','Рибља чорба','Рок',6,'Km89WVJlhFU'),(16,'Чивилук','2015-03-28 23:09:54','асодхцаипдсуцгапуидс','01:03:40','00406440-4b70-4568-99e0-e97c3f84e8b8',0,'','Рибља чорба','Рок',6,NULL),(17,'Saxo Club','2015-04-01 17:29:53','цуп цуп цуп','02:02:27','https://soundcloud.com/elyassb/the-saxo-club-presented-by-dj-elyassb',2,'','neki','Електро',6,'3446724');
/*!40000 ALTER TABLE `music_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `creation_time` datetime NOT NULL,
  `creator_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_playlist_1_idx` (`creator_id`),
  CONSTRAINT `fk_playlist_1` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (3,'Прва плејлиста','2015-04-02 21:19:36',6);
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_music_content`
--

DROP TABLE IF EXISTS `playlist_music_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist_music_content` (
  `playlist_id` int(11) NOT NULL,
  `music_content_id` int(11) NOT NULL,
  PRIMARY KEY (`playlist_id`,`music_content_id`),
  KEY `fk_playlist_music_content_2_idx` (`music_content_id`),
  CONSTRAINT `fk_playlist_music_content_1` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_playlist_music_content_2` FOREIGN KEY (`music_content_id`) REFERENCES `music_content` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_music_content`
--

LOCK TABLES `playlist_music_content` WRITE;
/*!40000 ALTER TABLE `playlist_music_content` DISABLE KEYS */;
INSERT INTO `playlist_music_content` VALUES (3,16),(3,17);
/*!40000 ALTER TABLE `playlist_music_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rate`
--

DROP TABLE IF EXISTS `rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rate` int(11) NOT NULL,
  `rating_time` datetime NOT NULL,
  `user_id` int(11) NOT NULL,
  `music_content_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `rate_u_mc` (`user_id`,`music_content_id`),
  KEY `fk_rate_2_idx` (`music_content_id`),
  CONSTRAINT `fk_rate_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rate_2` FOREIGN KEY (`music_content_id`) REFERENCES `music_content` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rate`
--

LOCK TABLES `rate` WRITE;
/*!40000 ALTER TABLE `rate` DISABLE KEYS */;
INSERT INTO `rate` VALUES (1,4,'2015-03-31 14:28:43',6,16),(2,2,'2015-04-04 13:18:23',12,16);
/*!40000 ALTER TABLE `rate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `user_id` int(11) NOT NULL,
  `role_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`,`role_name`),
  CONSTRAINT `fk_table1_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (6,'ROLE_ADMIN'),(6,'ROLE_REGISTERED'),(6,'ROLE_SLATKOCA'),(6,'ROLE_SUPER'),(11,'ROLE_REGISTERED'),(12,'ROLE_REGISTERED'),(12,'ROLE_SUPER'),(13,'ROLE_REGISTERED'),(14,'ROLE_REGISTERED');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(64) COLLATE utf8_unicode_ci NOT NULL,
  `jmb` char(13) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(90) COLLATE utf8_unicode_ci NOT NULL,
  `avatar_path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `active` bit(1) NOT NULL DEFAULT b'1',
  `registration_time` datetime NOT NULL,
  `activation_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (6,'admin','$2a$10$AouhwkhRL16tcUOqk4braeTXavm9ydqcwTXWVn7In..5OBJdL0bRa','Александра','Адамовић','1710990167533','acamojmail@gmail.com',NULL,'','2015-03-14 18:17:58','2015-03-14 18:17:58'),(11,'registrovani1','$2a$10$WkOlZMHwfv4tepJsK7FYVuLhJizB/IHHG1RP0udcXUELK4fMYqLWm','Некоиме','Некопрезиме','1234567890123','neko@mail.com',NULL,'','2015-03-17 19:08:26','2015-03-21 20:55:45'),(12,'milan.predojevic','$2a$10$W/EOTARuTLHTRr964f1I6.yyECjR.i9PfYGGJpqGy8cpaxfZvq4r2','Милан','Предојевић','1334567890123','milan.predojevic@mail.com','58d1cd90-41da-4fb1-a903-d3ca8a099dfd','','2015-03-17 19:49:29',NULL),(13,'registrovani2','$2a$10$BkFYYjBb9z6LbFbbTewwyOBnVUtRvsfDqGevn39Dh4vWkjaMh7fVq','dsfon','nofvdofinv','1234567890123','dsc@dff.com',NULL,'','2015-03-17 23:19:56','2015-04-04 23:04:45'),(14,'attacker','$2a$10$0P3138iknUqTFh4LL6Evxu/XGyiVtzhVgo0.0c.vw4JUwaGIDAAey','<script type=\"text/javascript\">alert(\"Gotcha\")</script>','dsf','1234567890123','attacker@mail.com',NULL,'','2015-03-31 18:07:51','2015-03-31 18:09:02');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-04-06  0:05:23
