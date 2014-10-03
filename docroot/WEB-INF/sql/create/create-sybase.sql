use master
exec sp_dboption 'lportal', 'allow nulls by default' , true
go

exec sp_dboption 'lportal', 'select into/bulkcopy/pllsort' , true
go

use lportal

create table models_MobilePhone (
	id_ decimal(20,0) not null primary key,
	name varchar(75) null,
	brand varchar(75) null,
	description varchar(255) null,
	releaseDate datetime null,
	price float
)
go




