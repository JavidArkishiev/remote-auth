CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(25)        NOT NULL,
    surname  VARCHAR(35)        NOT NULL,
    email    VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(255)       NOT NULL,
    role     VARCHAR(50)        NOT NULL
);
