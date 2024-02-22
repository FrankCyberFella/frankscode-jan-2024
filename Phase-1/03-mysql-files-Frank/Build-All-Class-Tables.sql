-- Build and populate Class Tables

-- is comment in SQL - everything after the -- is ignored

-- Relational Data requires each row to be uniquely identified
-- Use the PRIMARY KEY phrase on column or columns that should be to uniquely identify a row

-- PRIMARY KEY tells the DBMS to use this column to uniquely a row in the table
-- AUTO_INCREMENT tells the DBMS to automatically generate a sequential value for the column
--                this is the easiest way to generate primary key values
-- These are typically used together in tables to create a way uniquely identify rows

-- NOT NULL on a column tells the DBMS a value must be provided for the column 
--          when INSERTing or UPDATEing
--
-- UNIQUE on a column tells the DBMS the value in the column must be unique within the table
--
-- PRIMARY KEY implies both UNIQUE and NOT NULL
--
-- DEFAULT value tells the DBMS to use teh value specified as a default value if no value
--          is specified when the row is INSERTed into the table

-- the SQL INSERT statement is how you add rows to a table
--
-- Insert into table (columns-in-the-table-you-have-data-for)
--     Values(list-of-values-for-the-columns)
--
-- Note: order of the values must match the order of the columns specified

-- 
-- Set up execution environment for this process
-- 

-- Switch to the database we want table to be in
use estore;  
select DATABASE(); -- Display current database

-- 
-- Define table to User Information
-- 
-- Note: Dependent tables must be dropped before parent tables
--       Customer table (FOREIGN KEY) is dependent on User (PRIMARY KEY)
--       ie. An entry in Customer must have a match with an entry in User
--           i.e. A Customer must be a User

drop table if exists Customer;  -- remove any existing copy of the Customer table
drop table if exists User;      -- remove any existing copy of the User table
drop table if exists Product;   -- remove any existing copy of the Product table

create table User (
    uid     int PRIMARY KEY AUTO_INCREMENT, -- have the DBMS automatically generate a uid value
    name    varchar(256) NOT NULL,          -- NOT NULL makes this a required value
    phone   varchar(256),                   -- Value is optional since we don't have NOT NULL  
    email   varchar(256) NOT NULL UNIQUE    -- Value is required and must be unique within the table
);

show tables;
describe User;

-- Add some rows to the User table

-- ALTER will change the structure os a table without dropping or recreating
-- You can pretty ALTER anything, add cols, remove cols, add/remove primary key or foreign leys. et al


ALTER TABLE User AUTO_INCREMENT = 10293;  -- Set starting uid value instead of start at 1 and going up by 1

insert into User
  (name, phone, email)  -- uid column is not included since it is automatically generated
  value('Frank'         , '123-456-7890', 'frank@frank.com'),
       ('Tinkerbell'    , '321-456-7623', 'tink@tink.com'),
       ('Tigger'        , '321-456-7623', 'tiggs@tigger.com'),
       ('Luke Skywalker', '111-111-7623', 'luke@skywalker.net'),
       ('Princess Leia' , '222-222-2222', 'leia@skywalker.net'),
       ('Darth Vader'   , '999-999-9999', 'dad@skywalker.net'),
       ('Obi-Wan Kenobi', '888-888-9999', 'jedimaster1@jedi.com'),
       ('Yoda'          , '777-777-7777', 'Jedi1@jedi.com')
;   -- ; could be at the end of last line - Frank like it here

select * from User; -- display all rows and columns in User table 

-- 
-- Define Customer table
-- 

create table Customer (  -- create a new table Customer
    cid   int PRIMARY KEY AUTO_INCREMENT,  -- Customer id
    uid   int NOT NULL UNIQUE,             -- Associated User id - must match a row in User table
    address varchar(256),
    --     (col-in-this-table)  parent-table(col-in-parent)  - should be the primary key of the parent
    FOREIGN KEY (uid) REFERENCES User(uid) -- A customer must have an entry in the User table

);
show tables;        -- display all the tables in the database
describe Customer;  -- display the attributes of the Customer table

-- 
-- Define table to Product Information
-- 

drop table if exists Product; -- Remove any existing copy of the Product table

create table Product (
    pid     int PRIMARY KEY AUTO_INCREMENT,
    name    varchar(256) NOT NULL,
    brand   varchar(256),
    price   float default 0.00, -- default tells DBMS to provide a value if not given when row added
    color   varchar(256),
    ratings float
    );
 show tables;
 describe Product;   

-- Add some rows to the Customer table
-- Customer is a dependent of User (established by FOREIGN KEY clause on create table Custome)
--     the uid col in Customer table must match an existing uid in the User
--
-- The uid in the User table is automatically generated 
--       we don't know what it is for a specfic user
--       but the DBMS knows the uid for each User
--       so we will ask the DBMS for uid in User fo a specific User with a SELECT on the INSERT
--             when add a asosciated customer table
-- instead of a values() clause to specify the values for the columns, we use a select
-- SELECT - retrieve values from a table

insert into Customer
  (uid, address)
-- retrieve the uid, 'use this literal' from the User table where name is Frank  
  select uid, '1 Java Ln, Phoenix AZ 85339' from User where name ='Frank'
;

-- LIKE does a wildcard search of table:
--
--  % is the wildcard character and means 0 to any number of any characters
--
-- start-with: 'word%'
-- end-with:   '%word'
-- contains:   '%word%'

insert into Customer
  (uid, address)
  select uid, '1 Java Ln, Phoenix AZ 85339' from User where name like 'tink%' -- name starts-with 'tink'
;

insert into Customer
  (uid, address)
  select uid, 'Death Star' from User where name like '%Vader%' -- name contains 'Vader'
;

insert into Customer
  (uid, address)
  select uid, 'Dagobah' from User where name like '%Yo%' -- name contains 'Yo'
;
 select * from Customer; 