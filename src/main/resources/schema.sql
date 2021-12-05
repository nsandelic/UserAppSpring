drop table if exists user;
create table if not exists user (
    id identity,
    first_name varchar(250) not null,
    last_name varchar(250) not null,
    username varchar(100) not null,
    password varchar(250) not null,
    dob date not null,
    email varchar(250) not null,
    gender varchar(250) not null
    );

drop table if exists authority;
create table if not exists authority (
    id identity,
    name varchar(100) not null
    );

create table if not exists user_authority (
    user_id bigint not null,
    authority_id bigint not null,
    constraint fk_user foreign key (user_id) references user(id),
    constraint fk_authority foreign key (authority_id) references authority(id)
    );