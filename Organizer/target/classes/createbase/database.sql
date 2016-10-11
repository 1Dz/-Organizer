CREATE TABLE IF NOT EXISTS quests (
    name    TEXT    UNIQUE    NOT NULL,
    who     TEXT    NOT NULL,
    date    TEXT    NOT NULL,
    application     TEXT,
    selfApplication     TEXT,
    done    INTEGER NOT NULL,
    description     TEXT NOT NULL
);