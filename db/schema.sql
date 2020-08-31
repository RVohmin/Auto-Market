DROP TABLE IF EXISTS users;
create table if not exists users
(
    id       serial primary key,
    name     varchar(100) not null,
    email    varchar(100) not null unique,
    password varchar(100) not null
);
DROP TABLE IF EXISTS photo;
create table if not exists photo
(
    id   serial primary key,
    name varchar(100) not null
);
DROP TABLE IF EXISTS posts;
create table if not exists posts
(
    id               serial primary key,
    mark             varchar(40)  not null,
    model            varchar(40)  not null,
    color            varchar(200) not null,
    type             varchar(20)  not null,
    manufacturedYear varchar(200) not null,
    mileage          varchar(100)   not null,
    bodyType         varchar(20)  not null,
    status           bool         not null,
    price            varchar(20)  not null,
    fuel             varchar(20)  not null,
    data             Timestamp    not null,
    photo_id         int,
    user_id          int          not null
)
