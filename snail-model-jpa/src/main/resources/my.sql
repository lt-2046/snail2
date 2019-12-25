/*
SQLyog Ultimate v13.1.1 (64 bit)
MySQL - 5.7.24-0ubuntu0.18.04.1 : Database - my
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`my` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `my`;

/*Table structure for table `m_permission` */

DROP TABLE IF EXISTS `m_permission`;

CREATE TABLE `m_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(100) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `permission_num` int(11) DEFAULT NULL,
  `start` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

/*Data for the table `m_permission` */

/*Table structure for table `m_role` */

DROP TABLE IF EXISTS `m_role`;

CREATE TABLE `m_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL,
  `start` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `m_role` */

insert  into `m_role`(`id`,`role_name`,`start`) values 
(6,'管理员角色',0),
(7,'管理员角色',0),
(8,'经办员角色',0),
(9,'复核员角色',0),
(10,'管理员角色',0),
(11,'经办员角色',0),
(12,'复核员角色',0);

/*Table structure for table `m_role_permission` */

DROP TABLE IF EXISTS `m_role_permission`;

CREATE TABLE `m_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `r_id` int(11) DEFAULT NULL COMMENT '角色id',
  `p_id` int(11) DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `m_role_permission` */

/*Table structure for table `m_user` */

DROP TABLE IF EXISTS `m_user`;

CREATE TABLE `m_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_Name` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `alias_Name` varchar(100) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `create_Time` datetime DEFAULT NULL,
  `start` int(11) DEFAULT NULL COMMENT '用户状态0正常，1是锁定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `m_user` */

insert  into `m_user`(`id`,`user_Name`,`alias_Name`,`password`,`email`,`phone`,`create_Time`,`start`) values 
(5,'liutao','snail','123456','index.jspl@gmail.com','13902101490','2018-12-28 16:27:19',0),
(6,'liutao1','ss','123456','index.jspl@gmail.com','1213','2018-12-30 17:50:17',0),
(7,'ret','er','rer','index.jspl@gmail.com','rer','2018-12-30 18:08:04',0),
(8,'ewr','e','eer','index.jspl@gmail.com','78','2018-12-30 18:06:10',0),
(9,'dxx','dg','ter','index.jspl@gmail.com','78','2018-12-30 18:06:12',0),
(10,'dd','r','gfdg','index.jspl@gmail.com','7898','2018-12-30 18:06:13',0),
(11,'wer','r','ert','index.jspl@gmail.com','78','2018-12-30 18:06:14',0),
(12,'ss','dg','t','index.jspl@gmail.com','78','2018-12-30 18:06:16',0),
(13,'dd','dg','er','index.jspl@gmail.com','78','2018-12-30 18:06:18',0),
(14,'ddd','dg','t','index.jspl@gmail.com','78','2018-12-30 18:06:19',0),
(15,'ss','dg','e','index.jspl@gmail.com','87','2018-12-30 18:06:19',0);

/*Table structure for table `m_user_role` */

DROP TABLE IF EXISTS `m_user_role`;

CREATE TABLE `m_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `r_id` int(11) DEFAULT NULL,
  `start` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色关系表';

/*Data for the table `m_user_role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
