CREATE DATABASE mastermind;
 
\c mastermind;

CREATE TABLE IF NOT EXISTS users (
    user_id serial primary key,
    nickname text NOT NULL UNIQUE,
    email text NOT NULL UNIQUE,
    send_emails boolean NOT NULL default FALSE
);

CREATE TABLE IF NOT EXISTS gamescores (
    game_id serial primary key,
    score INTEGER NOT NULL,
    user_id INTEGER NOT NULL REFERENCES users(user_id)
);
