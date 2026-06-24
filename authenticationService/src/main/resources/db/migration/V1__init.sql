
drop table if exists springschema.roles;
create table springschema.roles(
	id bigserial,
	rolename varchar(255) not null,
	constraint roles_unique_rolename unique(rolename),
	constraint roles_primary_key_id primary key(id)
);

drop table if exists springschema.users;
create table  springschema.users(
	id bigserial,
	email varchar(255) not null,
	password varchar(255) not null,
	constraint users_primary_key_id primary key(id),
	constraint users_unique_email unique(email)
);

drop table if exists springschema.userroles;
create table springschema.userroles(
	userid bigint,
	roleid bigint,
	constraint userroles_primary_key_userid_roleid primary key(userid,roleid),
	constraint userroles_foreign_key_userid foreign key(userid) references springschema.users(id),
	constraint userroles_foreign_key_roleid foreign key(roleid) references springschema.roles(id)	
);



