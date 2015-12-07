CREATE TABLE IF NOT EXISTS `category` (
  `id`          INT(11)                 NOT NULL AUTO_INCREMENT,
  `name`        VARCHAR(50)
                COLLATE utf8_unicode_ci NOT NULL,
  `description` VARCHAR(30)
                COLLATE utf8_unicode_ci          DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `publisher` (
  `id`      INT(11)                 NOT NULL AUTO_INCREMENT,
  `name`    VARCHAR(50)
            COLLATE utf8_unicode_ci NOT NULL,
  `address` VARCHAR(100)
            COLLATE utf8_unicode_ci          DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `book` (
  `id`           INT(11) NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(50)      DEFAULT NULL,
  `page_count`   INT(11)          DEFAULT NULL,
  `authors`      VARCHAR(100)     DEFAULT NULL,
  `annotation`   VARCHAR(255)     DEFAULT NULL,
  `publisher_id` INT(11)          DEFAULT NULL,
  `year`         INT(11)          DEFAULT NULL,
  `weight`       INT(11)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `publisher_fk` (`publisher_id`),
  CONSTRAINT `publisher_fk` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `book_categories` (
  `book_id`     INT(11) DEFAULT NULL,
  `category_id` INT(11) DEFAULT NULL,
  KEY `fk_category` (`category_id`),
  KEY `fk_book` (`book_id`),
  CONSTRAINT `fk_book` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `fk_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `user` (
  `id`        INT(11)            NOT NULL AUTO_INCREMENT,
  `login`     VARCHAR(50) UNIQUE NOT NULL,
  `password`  VARCHAR(65)        NOT NULL,
  `enabled`   TINYINT(1)         NOT NULL,
  `confirmed` TINYINT(1)         NOT NULL,
  `role`      VARCHAR(20)        NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;