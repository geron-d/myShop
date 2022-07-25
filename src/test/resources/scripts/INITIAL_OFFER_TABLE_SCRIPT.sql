alter table product drop foreign key FK1mtsbur82frn64de7balymq9s;
alter table product drop foreign key FKaxeo9fj1sfah36yd9bujs8qft;
alter table product drop foreign key FKq3fvcsydiaotwy3iqn1erqsfd;
alter table product_in_bucket drop foreign key FK2vlb58hfdpn8r35r4o7nlua7t;
alter table product_in_bucket drop foreign key FK6je6g6ng57y3kck5nimj1ptx1;
drop table if exists category;
drop table if exists producer;
drop table if exists product;
drop table if exists product_in_bucket;
drop table if exists type;
drop table if exists user;
create table category
(
    id bigint not null auto_increment,
    name varchar(30),
    primary key (id)
);

create table producer
(
    id bigint not null auto_increment,
    name varchar(100),
    primary key (id)
);

create table product
(
    id bigint not null auto_increment,
    amount integer,
    date_inserting date,
    image_path varchar(255),
    name varchar(255),
    price double precision,
    category_id bigint,
    producer_id bigint not null,
    type_id bigint,
    primary key (id)
);

create table product_in_bucket
(
    id bigint not null auto_increment,
    amount integer,
    product_id bigint,
    user_id bigint,
    primary key (id)
);

create table type
(
    id bigint not null auto_increment,
    name varchar(30),
    primary key (id)
);

create table user
(
    id bigint not null auto_increment,
    access_level enum('ADMIN','USER'),
    login varchar(50),
    password varchar(50),
    primary key (id)
);

alter table category
    add constraint UK_46ccwnsi9409t36lurvtyljak
        unique (name);

alter table producer
    add constraint UK_h4x8qiecc2v7qwixqgwfgm1xl
        unique (name);

alter table type
    add constraint UK_3tg65hx29l2ser69ddfwvhy4h
        unique (name);

alter table user
    add constraint UK_ew1hvam8uwaknuaellwhqchhb
        unique (login);

alter table product
    add constraint FK1mtsbur82frn64de7balymq9s
        foreign key (category_id)
            references category (id);

alter table product
    add constraint FKaxeo9fj1sfah36yd9bujs8qft
        foreign key (producer_id)
            references producer (id);

alter table product
    add constraint FKq3fvcsydiaotwy3iqn1erqsfd
        foreign key (type_id)
            references type (id);

alter table product_in_bucket
    add constraint FK2vlb58hfdpn8r35r4o7nlua7t
        foreign key (product_id)
            references product (id);

alter table product_in_bucket
    add constraint FK6je6g6ng57y3kck5nimj1ptx1
        foreign key (user_id)
            references user (id);

insert into category (name) values ('headphones');
insert into category (name) values ('micros');
insert into category (name) values ('something new');

insert into type (name) values ('wireless');
insert into type (name) values ('wireless overhead');
insert into type (name) values ('wire');

insert into producer (name) values ('F9');
insert into producer (name) values ('Defender');
insert into producer (name) values ('GQBox');

INSERT INTO product (amount, date_inserting, image_path, name, price, category_id, producer_id, type_id)
VALUES (100, '2022-04-16', 'no', 'F9', 34.9, 1, 1, 1);

INSERT INTO product (amount, date_inserting, image_path, name, price, category_id, producer_id, type_id)
VALUES (100, '2022-04-16', 'no', 'Defender FreeMotion', 34.83, 1, 2, 2);

INSERT INTO product (amount, date_inserting, image_path, name, price, category_id, producer_id, type_id)
VALUES (100, '2022-04-16', 'no', 'GQBox', 9.43, 1, 3, 3);

INSERT INTO user (access_level, login, password) VALUES ('USER', 'nik', 'nik');