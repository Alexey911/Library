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