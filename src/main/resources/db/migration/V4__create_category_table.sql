CREATE TABLE category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    name_ar VARCHAR(255),
    description_ar TEXT,
    type VARCHAR(50),
    UNIQUE (name, type)
);