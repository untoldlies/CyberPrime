
delimiter $$

CREATE DATABASE `cyberprime` /*!40100 DEFAULT CHARACTER SET latin1 */$$

CREATE TABLE `client` (
  `imageHash` varchar(255) NOT NULL,
  `imageSize` int(11) NOT NULL,
  `imageExtension` varchar(45) NOT NULL,
  `userId` varchar(255) NOT NULL,
  `email` varchar(45) NOT NULL,
  `pattern` varchar(45) NOT NULL,
  PRIMARY KEY (`imageHash`),
  UNIQUE KEY `userId_UNIQUE` (`userId`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$


CREATE TABLE `notifications` (
  `sender` varchar(255) NOT NULL,
  `receiver` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1$$




