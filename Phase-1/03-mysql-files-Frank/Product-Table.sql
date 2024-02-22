use estore;
drop table if exists Product;
create table Product (
    pid     int,
    name    varchar(256),
    brand   varchar(256),
    price   int,
    color   varchar(256),
    ratings float
    );
 show tables;
 describe Product;   