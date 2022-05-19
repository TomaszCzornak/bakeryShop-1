show databases;
use bakery;
show tables ;
select * from product;
select * from category;

# insert into product (available_quantity, description, image_url, name, price, category_id) values(10,'Tort z masłem orzechowym', 'www.tort.pl','Tort Urodzinowy',100, 1 );

insert into category(name,description)values ('urodzinowy','masło orzechowe + czekolada');
insert into category(name,description)values ('weselny','zamówienie miesięczne');
insert into category(name,description)values ('przyjęcie','szybka dostawa');

select product.name from product join category pc on product.id = pc.id
where pc.name = 'urodzinowy';