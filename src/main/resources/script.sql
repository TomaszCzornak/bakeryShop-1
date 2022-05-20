show databases;
use bakery;
show tables ;
select * from product;
select * from category;

# insert into product (available_quantity, description, image_url, name, price, category_id) values(10,'Tort z masłem orzechowym', 'www.tort.pl','Tort Urodzinowy',100, 1 );

insert into category(name,description)values ('urodzinowy','masło orzechowe + czekolada');
insert into category(name,description)values ('weselny','zamówienie miesięczne');
insert into category(name,description)values ('przyjęcie','szybka dostawa');

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


select product.name from product join category pc on product.id = pc.id
where pc.name = 'urodzinowy';