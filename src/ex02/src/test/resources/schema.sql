DROP TABLE IF EXISTS user_table;

CREATE TABLE user_table(
    user_id int GENERATED ALWAYS AS IDENTITY(START WITH 1) NOT NULL,
    user_email varchar(10),
    user_password varchar(10),
    PRIMARY KEY (user_id)
);