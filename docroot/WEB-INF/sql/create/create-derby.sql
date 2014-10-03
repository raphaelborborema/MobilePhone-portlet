drop database lportal;
create database lportal;
connect to lportal;
create table models_MobilePhone (
	id_ bigint not null primary key,
	name varchar(75),
	brand varchar(75),
	description varchar(255),
	releaseDate timestamp,
	price double
);


create index IX_6176737C on models_MobilePhone (name);


