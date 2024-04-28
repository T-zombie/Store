-- liquibase formatted sql

-- changeset T-zombie:1
insert into account (id, username, password)
values ('63edaf7c-a879-4bb5-9f64-95904e0c3ad7', 'Admin', '{bcrypt}$2a$10$nJlVWJ1lrn5OBm7KfPYgHuFJCBoTk1dgXA7z3ZRFOEZsEolSVB6LS');