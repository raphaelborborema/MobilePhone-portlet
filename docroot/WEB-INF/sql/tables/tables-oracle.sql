create table models_MobilePhone (
	id_ number(30,0) not null primary key,
	name VARCHAR2(75 CHAR) null,
	brand VARCHAR2(75 CHAR) null,
	description VARCHAR2(255 CHAR) null,
	releaseDate timestamp null,
	price number(30,20)
);
