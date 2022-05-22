show databases;
use bakery;
show tables ;
select * from product;
select * from category;

create database bakery CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

insert into category(name)values ('urodzinowy');
insert into category(name)values ('weselny');
insert into category(name)values ('przyjęcie');

insert into product(available_quantity, description, image_url, name, price, category_id
) VALUES (10,'smaczny tort','www.tort.pl','masło orzechowe', 100,  1);
insert into product(available_quantity, description, image_url, name, price, category_id
) VALUES (10,'słodki tort owocowy','www.tort.pl','owocowy', 100,  1);
insert into product(available_quantity, description, image_url, name, price, category_id
) VALUES (10,'tort czekoladowy','www.tort.pl','czekolada mleczna', 100,  2);
insert into product(available_quantity, description, image_url, name, price, category_id
) VALUES (20,'tort beza','www.tortbeza.pl','beza', 200,  3);
insert into product(available_quantity, description, image_url, name, price, category_id
) VALUES (5,'tort orzechowy','www.tortorzech.pl','orzech', 150,  3);

INSERT INTO `role` (`id`, `name`) VALUES (NULL, 'ROLE_USER');
INSERT INTO `role` (`id`, `name`) VALUES (NULL, 'ROLE_ADMIN');

INSERT INTO `payment_method`(`name`)values ('gotówka odbiór własny');
INSERT INTO `payment_method`(`name`)values ('przelew internetowy');
INSERT INTO `payment_method`(`name`)values ('płatność za pobraniem');

select id from cart join cart_item ci on cart.id = ci.cart_id
where ci.status = 0;
