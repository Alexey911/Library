/*USERS password value = 'pass' */
INSERT INTO user (login, password, enabled, confirmed, role)
VALUES ("admin", "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", 1, 1, 2);
INSERT INTO user (login, password, enabled, confirmed, role)
VALUES ("user", "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", 1, 1, 0);
INSERT INTO user (login, password, enabled, confirmed, role)
VALUES ("librarian", "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", 1, 1, 1);

/*PUBLISHERS*/
INSERT INTO publisher (id, name, address)
VALUES (1, "O'Reilly Media", "America");
INSERT INTO publisher (id, name, address)
VALUES (2, "Apress", "New York City");
INSERT INTO publisher (id, name, address)
VALUES (3, "PRENTICE HALL", "");
INSERT INTO publisher (id, name, address)
VALUES (4, "Manning", "");

/*CATEGORIES*/
INSERT INTO category (id, name, description) VALUES (1, "Java", "programming language");
INSERT INTO category (id, name, description) VALUES (2, "Spring", "application framework");
INSERT INTO category (id, name, description) VALUES (3, "Programming", "");
INSERT INTO category (id, name, description) VALUES (4, "Git", "version controll");
INSERT INTO category (id, name, description) VALUES (5, "Java EE", "Java technology");
INSERT INTO category (id, name, description) VALUES (6, "JVM", "details of JVM");

/*BOOKS*/
INSERT INTO book (id, name, page_count, authors, annotation, publisher_id, year, weight)
VALUES
  (1, "Pro SPRING 3", 880, "Clarence Ho, Rob Harrop Apress", "Pro Spring 3 updates the bestselling Pro Spring", 2, 2013,
   400);
INSERT INTO book (id, name, page_count, authors, annotation, publisher_id, year, weight)
VALUES (2, "Core JavaServer Faces", 544, "David Geary, Cay Horstmann", "", 3, 2011, 280);

INSERT INTO book (id, name, page_count, authors, annotation, publisher_id, year, weight)
VALUES (3, "Beginning Java EE 7", 640, "Antonio Goncalves", "", 2, 2014, 350);

INSERT INTO book (id, name, page_count, authors, annotation, publisher_id, year, weight)
VALUES (4, "Pro Git", 608, "Gcott Chacon, Ben Straub", "Everytnig you need to know about git", 2, 2014, 330);

INSERT INTO book (id, name, page_count, authors, annotation, publisher_id, year, weight)
VALUES (5, "Vital Techniques of Java 7", 560,
        "Benjamin J. Evans, Matrijn Verburg", "The Well-Grounded Java Develooper", 4, 2014, 290);

/*BOOK_CATEGORIES*/
INSERT INTO book_categories (book_id, category_id) VALUES (1, 1);
INSERT INTO book_categories (book_id, category_id) VALUES (1, 2);
INSERT INTO book_categories (book_id, category_id) VALUES (1, 3);
INSERT INTO book_categories (book_id, category_id) VALUES (2, 1);
INSERT INTO book_categories (book_id, category_id) VALUES (3, 3);
INSERT INTO book_categories (book_id, category_id) VALUES (3, 5);
INSERT INTO book_categories (book_id, category_id) VALUES (4, 4);
INSERT INTO book_categories (book_id, category_id) VALUES (5, 1);
INSERT INTO book_categories (book_id, category_id) VALUES (5, 6);

