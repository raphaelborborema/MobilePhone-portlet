create table models_MobilePhone (
	id_ bigint not null primary key,
	name varchar(75),
	brand varchar(75),
	description varchar(255),
	releaseDate timestamp,
	price double
);
