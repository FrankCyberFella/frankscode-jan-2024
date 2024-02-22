-- be sure we are in the database containing the tables 
use estore;
-- select * - retrieve all columns in the order they are defined in table
select *
  from User
;
select name, email   -- specify the columns and the order of the columns in the result
  from User
;  
select email, name   -- specify the columns and the order of the columns in the result
  from User
;  
-- show the names of User that have skywalker emails
select name, email   -- column(s) to show in the result
  from User   -- table with the columns
where email like '%skywalker%'  -- only show rows where email contains skywalker
;

-- A join means retrieving data from multiple tables
-- There needs to something that matches between the tables
-- Typically this is Primary Key and Foreign Keys

-- an INNER JOIN - show rows that match between tables (Most common)
-- a LEFT JOIN   - show rows that are in the first table, but not the second (not so common)
-- a RIGHT JOIN  - show rows that are in the second table, but not the first (not so common)
-- a FULL OUTER JOIN - show all rows from both tables (rare)

-- Show the names and addresses of all Customers
-- name is in the User table
-- address is in the Customer table
-- uid matches between the tables - INNER JOIN

-- if names of the matching columns are same, 
--       you must include the name of the table for the column
--
-- Classic syntax of an INNER JOIN
--
-- If you forget the WHERE clause - it stills runs
--   but you get a Carterisian Product - all rows from table matched to all rows in the other
--
select name, address            -- columns to see in the result
  from User, Customer           -- table(s) with the columns
 where Customer.uid = User.uid  -- the rows where uid match between the tables
   and name like '%t%'
 ;
--
-- Modern syntax of an INNER JOIN
--
select name, address            -- columns to see in the result
  from User
       INNER JOIN
       Customer           
    on Customer.uid = User.uid  -- Join Condition - the rows where uid match between the tables
  where name like '%t%'
 ;