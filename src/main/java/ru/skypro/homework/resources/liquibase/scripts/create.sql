-- liquibase formatted sql
-- changeset irina:1
create table users
(
    user_id    serial primary key,
    password   varchar(50) not null,
    email      varchar(70) not null unique ,
    first_name varchar(30)  not null,
    last_name  varchar(30)  not null,
    phone      varchar(12)  not null,
    role       varchar(25)  not null
);
create table ads
(
    ad_id          serial primary key,
    user_id        int not null references users(user_id) on delete cascade,
    title          text not null ,
    price          int not null ,
    description    text
);
create table comments
(
    comment_id     serial primary key,
    user_id        int not null references users(user_id) on delete cascade,
    created_at     timestamp not null ,
    comments_text  text,
    ad_id int not null references ads (ad_id) on delete cascade
);