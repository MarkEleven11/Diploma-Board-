-- liquibase formatted sql
-- changeset irina:1

create table images
(
    image_id   bigserial primary key
);

create table users
(
    user_id    serial primary key,
    password   varchar(50) not null,
    email      varchar(70) not null unique ,
    first_name varchar(30)  not null,
    last_name  varchar(30)  not null,
    phone      varchar(12)  not null,
    role       varchar(25)  not null,
    image_id bigint references images (image_id)
);

create table ads
(
    ad_id          serial primary key,
    user_id        int not null references users(user_id) on delete cascade,
    title          text not null ,
    price          int not null ,
    description    text,
    image_id bigint references images (image_id)
);
create table comments
(
    comment_id     serial primary key,
    user_id        int not null references users(user_id) on delete cascade,
    created_at     timestamp not null ,
    comments_text  text,
    ad_id int not null references ads (ad_id) on delete cascade
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