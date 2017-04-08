create user 'dbuser'@'localhost' identified by 'dbpass';
create database db_test;
grant all privileges on db_test.* to 'dbuser'@'localhost';