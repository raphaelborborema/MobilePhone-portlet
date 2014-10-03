drop user &1 cascade;
create user &1 identified by &2;
grant connect,resource to &1;
connect &1/&2;
set define off;

create table models_MobilePhone (
	id_ number(30,0) not null primary key,
	name VARCHAR2(75 CHAR) null,
	brand VARCHAR2(75 CHAR) null,
	description VARCHAR2(255 CHAR) null,
	releaseDate timestamp null,
	price number(30,20)
);


create index IX_6176737C on models_MobilePhone (name);



quit