use estore;
drop table if exists User;

-- Relational Data requires each row to be uniquely identified
-- Use the PRIMARY KEY phrase on column or columns that should be to uniquely identify a row

-- PRIMARY KEY tells the DBMS to use this column to uniquely a row in the table
-- AUTO_INCREMENT tells the DBMS to automatically generate a sequential value for the column
--                this is the easiest way to generate primary key values
-- These are typically used together in tables to create a way uniquely identify rows

create table User (
    uid     int PRIMARY KEY AUTO_INCREMENT, -- have the DBMS automatically generate a uid value
    name    varchar(256),
    phone   varchar(256),
    email   varchar(256)
);
show tables;
describe User;