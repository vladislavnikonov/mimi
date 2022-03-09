create table cats (
    id serial primary key,
    name varchar(255),
    upvote int default 0,
    image varchar
);