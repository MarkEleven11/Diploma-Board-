-- liquibase formatted sql

create table users
(
    user_id    bigserial constraint users_pk primary key,
    password   varchar,
    username   varchar,
    first_name varchar,
    last_name  varchar,
    phone      varchar,
    role       varchar,
    image      varchar,
    reg_date                timestamp,
    non_expired             boolean default true,
    non_locked              boolean default true,
    non_credentials_expired boolean default true,
    is_enabled              boolean default true
);

create table ads
(
    ad_id          bigserial primary key,
    user_id        bigint references users(user_id),
    title          text,
    price          int,
    description    text,
    image          varchar
);
create table comments
(
    comment_id     bigserial primary key,
    user_id        bigint references users(user_id),
    created_at     timestamp,
    comments_text  text,
    ad_id bigint references ads (ad_id)
);

/*
*insert into users(password, email, first_name, last_name, phone, role, image_id)
*values ('r3IwatD25TuCab.D2A1AT1be',
*        'irma.semenova@gmail.com',
*        'Irma',
*        'Semenova',
*        '+7(908)3456778',
*        'ADMIN',
*        null)
 */