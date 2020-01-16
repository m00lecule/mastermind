CREATE DATABASE mastermind;
 
\c mastermind;

CREATE TABLE IF NOT EXISTS users (
    user_id serial primary key,
    nickname text UNIQUE,
    email text UNIQUE,
    send_emails boolean NOT NULL default FALSE
);

ALTER TABLE users
    ADD CONSTRAINT CK_only_one_might_be_null
        CHECK (
                (nickname IS NOT NULL OR email IS NOT NULL)
            );

CREATE TABLE IF NOT EXISTS gamescores (
    game_id serial primary key,
    score INTEGER NOT NULL,
    user_id INTEGER REFERENCES users(user_id)
);
