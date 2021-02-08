--drop table IF EXISTS acmeb_customer;
--drop table IF EXISTS acmeb_account;
--drop table IF EXISTS acmeb_user ;
--drop table IF EXISTS acmeb_transcation ;
--drop table IF EXISTS acmeb_fx_rate;

CREATE TABLE IF NOT EXISTS acmeb_user (
     user_id INT NOT NULL AUTO_INCREMENT NOT NULL PRIMARY KEY,
     username VARCHAR(200) NOT NULL,
     password_hash VARCHAR(72) NOT NULL,
     create_date DATE,
     CONSTRAINT acmeb_user_con1 UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS acmeb_customer (
     customer_id INT NOT NULL AUTO_INCREMENT NOT NULL PRIMARY KEY,
     customer_name VARCHAR(200) NOT NULL,
     create_date DATE,
     user_id INT NOT NULL,
     foreign key (user_id) references acmeb_user(user_id)
);

CREATE TABLE IF NOT EXISTS acmeb_account (
     account_id INT NOT NULL AUTO_INCREMENT NOT NULL PRIMARY KEY,
     account_no VARCHAR(200) NOT NULL,
     currency_code VARCHAR(10) NOT NULL,
     balance DECIMAL(20, 2) NOT NULL,
     create_date DATE,
     user_id INT NOT NULL,
     foreign key (user_id) references acmeb_user(user_id)
);

CREATE TABLE IF NOT EXISTS acmeb_transaction (
     transaction_id INT NOT NULL AUTO_INCREMENT NOT NULL PRIMARY KEY,
     from_account_id INT NOT NULL,
     to_account_id INT NOT NULL,
     amount DECIMAL(20, 2) NOT NULL,
     currency_code VARCHAR(10) NOT NULL,
     create_ts DATE NOT NULL,
     update_ts DATE,
     status VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS acmeb_fx_rate (
  currency_code_from VARCHAR(10) NOT NULL,
  currency_code_to VARCHAR(10) NOT NULL,
  fx_rate DECIMAL(10, 5),
  CONSTRAINT acme_fx_rate_con1 UNIQUE (currency_code_from, currency_code_to)
);


--drop table IF EXISTS acme_transaction ;
--drop table IF EXISTS acme_fx_rate;


