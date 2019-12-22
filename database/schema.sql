CREATE USER postgres_user WITH PASSWORD 'password';
CREATE DATABASE mastermind OWNER postgres_user;
 
\c mastermind; 

CREATE TABLE IF NOT EXISTS users (
    id serial primary key,
    nickname text NOT NULL UNIQUE,
    email text NOT NULL UNIQUE 
);

CREATE TABLE IF NOT EXISTS difficulties (
    id serial primary key,
    description text
);

CREATE TABLE IF NOT EXISTS games (
    id serial primary key,
    score INTEGER NOT NULL,
    userId INTEGER NOT NULL REFERENCES users(id),
    diffId INTEGER NOT NULL REFERENCES difficulties(id)
);

CREATE TABLE IF NOT EXISTS records_history (
    id serial primary key,
    gameId INTEGER NOT NULL REFERENCES games(id),
    isProcessed BOOLEAN default FALSE
);

CREATE OR REPLACE FUNCTION record_check() RETURNS TRIGGER AS $$
    BEGIN
        IF NOT EXISTS(SELECT 1 FROM games WHERE diffID = NEW.diffId and id != NEW.id and score >= NEW.score) THEN
            INSERT INTO records_history(gameId) VALUES (NEW.id);
        END IF;
        RETURN NEW;
    END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER RECORD_CHECK_TRIG
    AFTER INSERT OR UPDATE
    ON GAMES
    FOR EACH ROW
    execute procedure record_check();
    