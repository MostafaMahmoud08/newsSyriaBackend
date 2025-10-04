ALTER TABLE article
ADD COLUMN category_id BIGINT,
ADD CONSTRAINT fk_article_category
    FOREIGN KEY (category_id) REFERENCES category(id)
    ON DELETE SET NULL;