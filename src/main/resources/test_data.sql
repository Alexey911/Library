/*USERS password value = 'pass' */
INSERT INTO user (login, password, enabled, confirmed, role)
VALUES ("admin", "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", 1, 1, "ADMIN");
INSERT INTO user (login, password, enabled, confirmed, role)
VALUES ("user", "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", 1, 1, "USER");
INSERT INTO user (login, password, enabled, confirmed, role)
VALUES ("librarian", "d74ff0ee8da3b9806b18c877dbf29bbde50b5bd8e4dad7a3a725000feb82e8f1", 1, 1, "LIBRARIAN");

/*PUBLISHERS*/
INSERT INTO publisher (id, name, address)
VALUES (1, "O'Reilly Media", "American media company established by Tim O'Reilly");
INSERT INTO publisher (id, name, address)
VALUES (2, "Apress", "Is a publisher of information technology books, based in New York City");

/*CATEGORIES*/
INSERT INTO category (id, name, description) VALUES (1, "Java", "programming language");
INSERT INTO category (id, name, description) VALUES (2, "Spring", "is an application framework");
INSERT INTO category (id, name, description) VALUES (3, "Programming", "");

/*BOOKS*/
INSERT INTO book (id, name, page_count, authors, annotation, publisher_id, year, weight)
VALUES
  (1, "Pro SPRING 3", 880, "Clarence Ho, Rob Harrop Apress", "Pro Spring 3 updates the bestselling Pro Spring", 2, 2013,
   330);

/*BOOK_CATEGORIES*/
INSERT INTO book_categories (book_id, category_id) VALUES (1, 1);
INSERT INTO book_categories (book_id, category_id) VALUES (1, 2);
INSERT INTO book_categories (book_id, category_id) VALUES (1, 3);