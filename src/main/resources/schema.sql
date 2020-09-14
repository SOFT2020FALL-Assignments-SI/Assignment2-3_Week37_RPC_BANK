-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

-- This file has fixed name and location
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Transactions_accounts;

CREATE TABLE Customer (
  accnum INTEGER PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  amount DOUBLE
);

CREATE TABLE Transactions_accounts (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  accnum INTEGER,
  type VARCHAR(250) NOT NULL,
  amount DOUBLE NOT NULL
);