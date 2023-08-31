CREATE DATABASE bank;

CREATE TABLE IF NOT EXISTS banks (

    id   BIGSERIAL PRIMARY KEY,
    bic  VARCHAR(4)  NOT NULL,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (

    id          BIGSERIAL PRIMARY KEY,
    last_name   VARCHAR(50) NOT NULL,
    first_name  VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50) NOT NULL,
    bank_id     BIGINT NOT NULL,
    CONSTRAINT fk_bank_user FOREIGN KEY (bank_id)
        REFERENCES banks (id) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS accounts (

    id        BIGSERIAL PRIMARY KEY,
    currency  VARCHAR(50) NOT NULL,
    balance   DOUBLE PRECISION NOT NULL,
    date_open TIMESTAMP(6) NOT NULL,
    user_id   BIGINT NOT NULL,
    bank_id   BIGINT NOT NULL,
    CONSTRAINT fk_bank_account FOREIGN KEY (bank_id)
        REFERENCES banks (id) ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_user_account FOREIGN KEY (user_id)
        REFERENCES users (id) ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS transactions (

    id         BIGSERIAL PRIMARY KEY,
    price      DOUBLE PRECISION NOT NULL,
    type       VARCHAR(50) NOT NULL,
    date       TIMESTAMP(6) NOT NULL,
    comment    VARCHAR(255),
    account_id BIGINT NOT NULL,
    CONSTRAINT fk_account_transaction FOREIGN KEY (account_id)
        REFERENCES accounts (id) ON DELETE CASCADE ON UPDATE NO ACTION
)