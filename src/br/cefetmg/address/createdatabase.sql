-- CREATE DATABASE address;

DROP TABLE IF EXISTS person CASCADE;

CREATE TABLE person (
    id bigserial CONSTRAINT person_pkey PRIMARY KEY,
    firstName text,
    lastName text,
    street text,
    postalCode integer,
    city text,
    birthday date
);