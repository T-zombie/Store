-- liquibase formatted sql

-- changeset T-zombie:1
CREATE TABLE item
(
    id              BIGSERIAL        PRIMARY KEY,
    name            VARCHAR          NOT NULL,
    price           DOUBLE PRECISION NOT NULL,
    description     VARCHAR,
    category        VARCHAR          NOT NULL
);

