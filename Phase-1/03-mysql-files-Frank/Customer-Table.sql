-- is comment in SQL - everything after the -- is ignored

-- Switch to the database we want table to be in
use estore;  

-- Remove any existing copy of table so we can recreate it
drop table if exists Customer;

create table Customer (  -- create a new table Customer
    cid   int,
    name  varchar(256),
    phone varchar(16),
    email varchar(256)
);
show tables;        -- display all the tables in the database
describe Customer;  -- display the attributes of the Customer table