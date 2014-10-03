create table models_MobilePhone (
	id_ int8 not null primary key,
	name varchar(75),
	brand varchar(75),
	description varchar(255),
	releaseDate datetime YEAR TO FRACTION,
	price float
)
extent size 16 next size 16
lock mode row;
