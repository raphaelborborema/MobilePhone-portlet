drop database lportal;
create database lportal encoding = 'UNICODE';
\c lportal;

create table models_MobilePhone (
	id_ bigint not null primary key,
	name varchar(75) null,
	brand varchar(75) null,
	description varchar(255) null,
	releaseDate timestamp null,
	price double precision
);


create index IX_6176737C on models_MobilePhone (name);


