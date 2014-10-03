create table models_MobilePhone (
	id_ bigint not null primary key,
	name nvarchar(75) null,
	brand nvarchar(75) null,
	description nvarchar(255) null,
	releaseDate datetime null,
	price float
);
