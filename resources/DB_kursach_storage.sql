create table storage
(
    idStorage          int not null
        primary key,
    products_idProduct int null,
    constraint fk_storage_products1
        foreign key (products_idProduct) references products (idProduct)
);

create index fk_storage_products1_idx
    on storage (products_idProduct);

INSERT INTO DB_kursach.storage (idStorage, products_idProduct) VALUES (1, 1);
INSERT INTO DB_kursach.storage (idStorage, products_idProduct) VALUES (2, 1);
INSERT INTO DB_kursach.storage (idStorage, products_idProduct) VALUES (3, 2);