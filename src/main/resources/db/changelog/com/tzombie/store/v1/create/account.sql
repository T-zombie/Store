-- liquibase formatted sql

-- changeset T-zombie:1
CREATE TABLE account
(
    id         uuid      not null
        constraint users_pk primary key,
    username      varchar(65),
    password      varchar(8192)
);

