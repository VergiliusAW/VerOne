create table shops
(
    idShop       int      not null
        primary key,
    manager_name char(10) null
);

INSERT INTO DB_kursach.shops (idShop, manager_name) VALUES (1, 'Misha');
INSERT INTO DB_kursach.shops (idShop, manager_name) VALUES (2, 'Timofey');