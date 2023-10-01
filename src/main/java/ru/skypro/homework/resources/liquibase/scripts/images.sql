-- liquibase formatted sql
-- changeset irina:1
create table images
(
    image_id   bigserial primary key,
    size       bigint,
    media_type varchar(50)
);

alter table users
    add column image_id bigint references images (image_id);

alter table ads
    add column image_id bigint references images (image_id);

-- changeset anna:2
alter table images
    drop column size,
    drop column media_type;