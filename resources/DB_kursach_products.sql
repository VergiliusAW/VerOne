create table products
(
    idProduct int         not null
        primary key,
    name      varchar(45) null,
    city      varchar(45) null,
    price     int         null
);

INSERT INTO DB_kursach.products (idProduct, name, city, price) VALUES (1, 'apple', 'Moscow', 129);
INSERT INTO DB_kursach.products (idProduct, name, city, price) VALUES (2, 'orange', 'Yaroslavl', 89);
INSERT INTO DB_kursach.products (idProduct, name, city, price) VALUES (3, 'banana', 'Rybinsk', 99);