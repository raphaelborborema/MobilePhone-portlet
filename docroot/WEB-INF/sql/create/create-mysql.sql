drop database if exists lportal;
create database lportal character set utf8;
use lportal;

create table models_MobilePhone (
	id_ bigint not null primary key,
	name varchar(75) null,
	brand varchar(75) null,
	description varchar(255) null,
	releaseDate datetime null,
	price double
) engine InnoDB;


create index IX_6176737C on models_MobilePhone (name);


