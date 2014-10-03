create database 'lportal.gdb' page_size 8192 user 'sysdba' password 'masterkey';
connect 'lportal.gdb' user 'sysdba' password 'masterkey';
create table models_MobilePhone (id_ int64 not null primary key,name varchar(75),brand varchar(75),description varchar(255),releaseDate timestamp,price double precision);
