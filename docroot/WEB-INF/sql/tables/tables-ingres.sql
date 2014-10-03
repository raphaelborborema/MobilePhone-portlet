create table models_MobilePhone (
	id_ bigint not null primary key,
	name varchar(75) null,
	brand varchar(75) null,
	description varchar(255) null,
	releaseDate timestamp null,
	price float
);
