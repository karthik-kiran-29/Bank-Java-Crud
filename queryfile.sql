-- create a database
CREATE database Bank;

-- use the current database
USE Bank;

-- Creating a table
CREATE table account_tbl(
	
	Account_Number Varchar(10) PRIMARY KEY,
	Customer_Name Varchar(15),
	Balance int
    
);

-- inserting values in the table
INSERT into account_tbl 
values 
	("1234567890" , "Reddy" , 80000),
	("1234567891" , "Mahesh" , 0),
	("1234567892" , "Dhanu" , 100),
	("1234567893" , "Sam" , 500);


-- view the table
SELECT * from account_tbl;

----------------------------------------------------------------- 

-- Creating a  2nd table
CREATE TABLE transfer_tbl (
    Transaction_id INT PRIMARY KEY,
    Account_Number VARCHAR(10),
    Beneficiary_acc_number VARCHAR(10),
    Transaction_Date DATE,
    Transaction_Amount INT,

    FOREIGN KEY (Account_Number)
        REFERENCES account_tbl(Account_Number),

    FOREIGN KEY (Beneficiary_acc_number)
        REFERENCES account_tbl(Account_Number)
);

-- to view 
SELECT * from transfer_tbl;
