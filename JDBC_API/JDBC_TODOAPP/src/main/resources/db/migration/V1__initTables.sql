create table if not exists task (
    id INT auto_increment primary key,
    name varchar(255) not null,
    status varchar(20) not null
);
-- first migration file, creating table task

