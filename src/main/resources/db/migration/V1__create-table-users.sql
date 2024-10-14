CREATE TABLE  users(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    balance INTEGER NOT NULL,
    email varchar(255),
    password varchar(255),
);