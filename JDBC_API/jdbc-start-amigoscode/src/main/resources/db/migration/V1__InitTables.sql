create table movie (
    id BIGSERIAL PRIMARY KEY, -- bigserial is auto incremented
    name TEXT NOT NULL UNIQUE,
    release_date DATE NOT NULL
);
-- for flyway, each migration file should have a version number and a description
-- V1__InitTables.sql means version 1, description is InitTables
-- YOU SHOULD NEVER EDIT A MIGRATION FILE THAT HAS ALREADY BEEN APPLIED TO A DATABASE
-- because it can lead to inconsistencies between different environments.
-- Instead, create a new migration file for any changes needed.