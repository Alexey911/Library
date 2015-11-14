CREATE TABLE IF NOT EXISTS categories (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(50) DEFAULT NULL,
  description varchar(150) DEFAULT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS publishers (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(40) DEFAULT NULL,
  address varchar(100) DEFAULT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS books (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name varchar(50) DEFAULT NULL,
  page_count int(11) DEFAULT NULL,
  authors varchar(100) DEFAULT NULL,
  annotation varchar(255) DEFAULT NULL,
  publisher_id int(11) DEFAULT NULL,
  publishing_year int(11) DEFAULT NULL,
  weight int(11) DEFAULT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS book_categories (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  id_book int(11) DEFAULT NULL,
  id_category int(11) DEFAULT NULL,
  KEY id_book (id_book),
  KEY id_category (id_category)
) ENGINE=InnoDB;