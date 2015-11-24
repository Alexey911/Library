CREATE TABLE `publisher` (
  `id`      INT(11)                 NOT NULL AUTO_INCREMENT,
  `name`    VARCHAR(50)
            COLLATE utf8_unicode_ci NOT NULL,
  `address` VARCHAR(100)
            COLLATE utf8_unicode_ci          DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `category` (
  `id`          INT(11)                 NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(50)
                COLLATE utf8_unicode_ci NOT NULL,
  `description` VARCHAR(30)
                COLLATE utf8_unicode_ci          DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `page_count` int(11) DEFAULT NULL,
  `authors` varchar(100) DEFAULT NULL,
  `annotation` varchar(255) DEFAULT NULL,
  `publisher_id` int(11) DEFAULT NULL,
  `publishing_year` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `publisher_id` (`publisher_id`),
  CONSTRAINT `to_publisher` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `book_categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_book` int(11) DEFAULT NULL,
  `id_category` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_book` (`id_book`),
  KEY `id_category` (`id_category`),
  CONSTRAINT `to_book` FOREIGN KEY (`id_book`) REFERENCES `books` (`id`) ON DELETE CASCADE,
  CONSTRAINT `to_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`)
    ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;