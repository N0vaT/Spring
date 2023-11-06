DROP TABLE IF EXISTS user_table;

CREATE TABLE IF NOT EXISTS user_table(
    user_id serial PRIMARY KEY NOT NULL,
    user_email varchar(10),
    user_password varchar(10)
);