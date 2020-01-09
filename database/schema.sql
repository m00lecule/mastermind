CREATE USER postgres_user WITH PASSWORD 'password';
CREATE DATABASE mastermind OWNER postgres_user;
 
\c mastermind; 

CREATE TABLE IF NOT EXISTS users (
    id serial primary key,
    nickname text NOT NULL UNIQUE,
    email text NOT NULL UNIQUE,
    send_emails boolean NOT NULL default FALSE
);

CREATE TABLE IF NOT EXISTS games (
    id serial primary key,
    score INTEGER NOT NULL,
    userId INTEGER NOT NULL REFERENCES users(id),
);
