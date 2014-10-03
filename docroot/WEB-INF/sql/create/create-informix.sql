database sysmaster;
drop database lportal;
create database lportal WITH LOG;

create procedure 'lportal'.isnull(test_string varchar)
returning boolean;
IF test_string IS NULL THEN
	RETURN 't';
ELSE
	RETURN 'f';
END IF
end procedure;


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


create index IX_6176737C on models_MobilePhone (name);


