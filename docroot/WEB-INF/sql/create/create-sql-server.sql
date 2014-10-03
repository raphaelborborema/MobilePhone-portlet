drop database lportal;
create database lportal;

go

use lportal;

create table models_MobilePhone (
	id_ bigint not null primary key,
	name nvarchar(75) null,
	brand nvarchar(75) null,
	description nvarchar(255) null,
	releaseDate datetime null,
	price float
);


create index IX_6176737C on models_MobilePhone (name);


