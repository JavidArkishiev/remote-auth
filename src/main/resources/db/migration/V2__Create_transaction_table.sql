CREATE TABLE transaction
(
    transaction_id BIGSERIAL PRIMARY KEY,
    balance        DECIMAL(19, 2),
    user_id        BIGINT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);
