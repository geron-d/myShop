create table category
(
    id bigint not null auto_increment,
    name varchar(30),
    primary key (id)
);

insert into category (name) values ('headphones');
insert into category (name) values ('micros');
insert into category (name) values ('something new');
