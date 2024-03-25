-- Add some rows to the User table

-- the SQL INSERT statement is how you add rows to a table
--
-- Insert into table (columns-in-the-table-you-have-data-for)
--     Values(list-of-values-for-the-columns)
--
-- Note: order of the values must match the order of the columns specified

use estore; -- switch to the database with the User table

insert into User
  (name, phone, email)
  value('Frank', '123-456-7890', 'frank@frank.com');

  insert into User
  (name, phone, email)
  value('Tinkerbell', '321-456-7623', 'tink@tink.com');

  insert into User
  (name, phone, email)
  value('Tigger', '321-456-7623', 'tiggs@tigger.com')
;   -- ; could be at the end of last line - Frank like it here

select * from User; -- display all rows and columns in User table 