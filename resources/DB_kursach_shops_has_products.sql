create table shops_has_products
(
    shops_idShop       int not null,
    products_idProduct int not null,
    count              int null,
    primary key (shops_idShop, products_idProduct),
    constraint fk_shops_has_products_products1
        foreign key (products_idProduct) references products (idProduct),
    constraint fk_shops_has_products_shops
        foreign key (shops_idShop) references shops (idShop)
);

create index fk_shops_has_products_products1_idx
    on shops_has_products (products_idProduct);

create index fk_shops_has_products_shops_idx
    on shops_has_products (shops_idShop);

INSERT INTO DB_kursach.shops_has_products (shops_idShop, products_idProduct, count) VALUES (1, 1, 1);
INSERT INTO DB_kursach.shops_has_products (shops_idShop, products_idProduct, count) VALUES (1, 2, 2);
INSERT INTO DB_kursach.shops_has_products (shops_idShop, products_idProduct, count) VALUES (2, 1, 3);