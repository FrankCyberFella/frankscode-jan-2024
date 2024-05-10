-- ---------------------------------------------------------------------------
-- This SQL will create all tables and supporting objects required for 
--
-- Frank's JDBC training session
--
-- To run it: 
--
--    1. Open a terminal window (preferrably, bash or gitbash).
--       (You may use the Treminal window in Eclipse too)
--
--    2. Change to the directory containing this file.
--
--    3. Enter the mySql CLI environment as the root user:
-- 
--          sudo mysql -u root
--
--       Enter the password you use to login to your computer when asked
--
--    4. Run the SQL in this file:
--
--         source jdbc-sample-data.sql
--
--    5. Review the output from the running of this SQL to see what it did
-- ---------------------------------------------------------------------------

-- ---------------------------------------------------------------------------
-- remove any existing version of the database we are defining for class work
-- ---------------------------------------------------------------------------
drop database if exists vegasdb;

-- ---------------------------------------------------------------------------
-- remove any existing user we are defining for class work
-- ---------------------------------------------------------------------------
DROP USER 'student'@'localhost';

-- ---------------------------------------------------------------------------
-- Create a new database for use in class work
-- ---------------------------------------------------------------------------
create database vegasdb;

-- ---------------------------------------------------------------------------
-- Tell mySql we want all processing done in the class database
-- --------------------------------------------------------------------------
use vegasdb;

-- ---------------------------------------------------------------------------
-- Create a user with a password for class work 
-- ---------------------------------------------------------------------------
CREATE USER 'student'@'localhost' IDENTIFIED BY 'Java#1Rules';

-- ---------------------------------------------------------------------------
-- Allow user to do anything on the class database
-- ---------------------------------------------------------------------------
GRANT ALL PRIVILEGES ON vegasdb.* TO 'student'@'localhost' ;

-- ---------------------------------------------------------------------------
-- Create casino table representing gamblers 
-- ---------------------------------------------------------------------------
create table casino
    (casino_name          char(20)            not null,
     location             char(15)            not null,
     owner                char(20)            not null,
     size                 integer             not null,
     primary key(casino_name));

-- ---------------------------------------------------------------------------
-- Create gambler table representing gamblers 
-- ---------------------------------------------------------------------------
create table gambler
    (id                   smallint            not null  auto_increment,
     gambler_name         char(20)            not null,
     address              char(20),
     birth_date           date                not null,
     monthly_salary       decimal(9,2)        not null,
     primary key(id));

-- ---------------------------------------------------------------------------
-- Create host table representing gamblers hosted at a specific casino
--
-- Note: parent dependent relationships defined in foreign clauses
--       and cascase option on delete which will delete dependent rows in
--       this table if the matching parent row is deleted from the parent
--
-- This table must be created after all parent tables
-- ---------------------------------------------------------------------------
create table host
    (casino_name          char(20)            not null,
     id                   smallint            not null,
     room_type            char(15)            not null,
     credit_line          decimal(9,2)        not null,
     avg_bet              smallint            not null,
     primary key(casino_name, id),
     foreign key(casino_name) references casino(casino_name)
                              on delete cascade,
     foreign key(id) references gambler(id)
                              on delete cascade);
-- ---------------------------------------------------------------------------
-- Create dealer table representing dealer working at a specific casino
--
-- Note: parent dependent relationship defined in foreign clause
--      and cascase option on delete which will delete dependent rows in
--      this table if the matching parent row is deleted from the parent
--
-- This table must be created after all parent tables
-- ---------------------------------------------------------------------------

create table dealer
    (casino_name          char(20)            not null,
     dealer_name          char(20)            not null,
     game                 char(10)            not null,
     years_exp            smallint            not null,
     primary key(casino_name, dealer_name),
     foreign key(casino_name) references casino(casino_name)
                              on delete cascade);

-- ---------------------------------------------------------------------------
-- Add data to casino table
-- ---------------------------------------------------------------------------
insert into casino
  values('Caesers Palace', 'Las Vegas', 'Julius Caeser', 40000);
insert into casino
  values('Harveys Casino', 'Lake Tahoe', 'Harvey Wallbanger', 28000);
insert into casino
  values('Ballys', 'Reno', 'Bill Bally', 31500);
insert into casino
  values('Trump Plaza', 'atlantic city', 'Donut Trump', 22800);
insert into casino
  values('Binions Horseshoe', 'Las vegas', 'Fred Binyawn', 16900);

-- ---------------------------------------------------------------------------
-- Add data to gambler table
-- ---------------------------------------------------------------------------
insert into gambler
  values(054, 'Neil Bransfield', 'Seattle, WA',
              '1959-03-11', 5000.00);
insert into gambler
  values(137, 'Phil Donahuge', null,
              '1946-12-29', 3250.25);
insert into gambler
  values(382, 'Stickman Nelson', 'Cumberland, MD',
              '1955-10-21', 12983.75);
insert into gambler
  values(292, 'Bettina Chavez',  'Fresno, CA',
              '1964-09-09', 2950.00);
insert into gambler
  values(012, 'T Judson Smith', 'Los Angeles, CA',
              '1972-05-01', 1398.65);
insert into gambler
  values(049, 'Dana Imori', null,
              '1938-08-08', 7580.50);
insert into gambler
  values(191, 'Frank Mint', 'El Paso, TX',
              '1977-06-23', 8200.00);
insert into gambler
  values(572, 'Al Mostbroke', 'Clayton, MO',
              '1975-01-18', 4505.65);
insert into gambler
  values(201, 'S.Q. Elle', 'Relational, DA',
              '1995-05-23', 1000000.01);              
insert into gambler
  values(319, 'Red Squiggledoc', 'Java, Indonesia',
              '1979-10-16', 2000.00);
-- --------------------------------------------------------------------------------------------------
-- Add data to dealer table - must be done after data is added to it's parent table, casino
-- --------------------------------------------------------------------------------------------------
insert into dealer
  values('Harveys Casino', 'Patriccio Riodini', 'Craps', 4);
insert into dealer
  values('Binions Horseshoe', 'Fred Stipinski', 'Craps', 8);
insert into dealer
  values('Binions Horseshoe', 'Stickman Nelson', 'Blackjack', 10);
insert into dealer
  values('Trump Plaza', 'Lance Lu', 'Roulette', 5);
insert into dealer
  values('Caesers Palace', 'Arthur Andersen', 'Blackjack', 2);
insert into dealer
  values('Ballys', 'Frances Zielinski', 'Craps', 1);

-- --------------------------------------------------------------------------------------------------------
-- Add data to host table - must be done after data is added to both it's parent tables, casino, gambler
-- --------------------------------------------------------------------------------------------------------
insert into host
  values('Ballys', 012, 'Deluxe', 5000.00, 75);
insert into host
  values('Ballys', 049, 'Rack',   5000.00, 65);
insert into host
  values('Binions Horseshoe', 054, 'Presidential', 1000.00, 10);
insert into host
  values('Ballys', 054, 'dump',   2500.00, 15);
insert into host
  values('Binions Horseshoe', 572, 'Penthouse', 2000.00, 25);
insert into host
  values('Harveys Casino', 191, 'Penthouse', 25000.00, 345);
insert into host
  values('Ballys', 382, 'Rack',   16.50, 2);
insert into host
  values('Binions Horseshoe', 382, 'Rack', 5000.00, 75);
insert into host
  values('Trump Plaza', 292, 'Suite', 1000.00, 25);
insert into host
  values('Binions Horseshoe', 012, 'Presidential', 7500.00, 45);
insert into host
  values('Ballys', 572, 'Rack', 1000.00, 20);
insert into host
  values('Trump Plaza', 049, 'Non-Smoking', 100000.00, 600);
insert into host
  values('Binions Horseshoe', 292, 'Deluxe', 5000.00, 55);
insert into host
  values('Ballys', 292, 'Suite', 2250.00, 20);
insert into host
  values('Trump Plaza', 572, 'Non-Smoking', 1000.00, 18);
insert into host
  values('Binions Horseshoe', 191, 'Penthouse',  2000.00, 22);
insert into host
  values('Binions Horseshoe', 049, 'Rack', 10000.00, 80);
insert into host
  values('Ballys', 191, 'Rack', 30000.00, 130);
-- ---------------------------------------------------------------------------
-- Display all the databases mySql knows about
show databases;

-- Display current database
select DATABASE() as Current_Database;

-- Display all the users and the host mySql server thay are defined on
select user, host from mysql.user;

-- Display the privileges help by the user defined for class work
show grants for 'student'@'localhost';

-- Disply the port the mySQL server is monitoring
SHOW GLOBAL VARIABLES LIKE 'PORT';

-- Display all the tables in the current database
show tables;

-- Display the contents of the tables created for class work
select * from casino;
select * from dealer;
select * from gambler;
select * from host;

