CREATE TABLE token (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    token TEXT,
    created_at TIMESTAMP,
    expired_at TIMESTAMP,
    confirmed_at TIMESTAMP,
    user_id UUID NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);