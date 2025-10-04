-- ============================
-- Table: article
-- ============================
CREATE TABLE article (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    status VARCHAR(255),
    created_at DATE NOT NULL DEFAULT CURRENT_DATE,
    header VARCHAR(255),
    bio TEXT,
    thumbnail_id UUID,
    CONSTRAINT fk_article_thumbnail FOREIGN KEY (thumbnail_id)
        REFERENCES image(id)
        ON DELETE SET NULL
);

-- ============================
-- Table: section
-- ============================
CREATE TABLE section (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    header VARCHAR(255),
    cover_image_id UUID,
    image_url TEXT,
    content TEXT,
    article_id UUID NOT NULL,
    CONSTRAINT fk_section_cover_image FOREIGN KEY (cover_image_id)
        REFERENCES image(id)
        ON DELETE SET NULL,
    CONSTRAINT fk_section_article FOREIGN KEY (article_id)
        REFERENCES article(id)
        ON DELETE CASCADE
);

-- ============================
-- Table: comment
-- ============================
CREATE TABLE comment (
    id BIGSERIAL PRIMARY KEY,
    comment_content TEXT,
    comment_status VARCHAR(255),
    comment_date TIMESTAMP,
    user_id UUID,
    article_id UUID,
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE SET NULL,
    CONSTRAINT fk_comment_article FOREIGN KEY (article_id)
        REFERENCES article(id)
        ON DELETE CASCADE
);

-- ============================
-- Table: reaction
-- ============================
CREATE TABLE reaction (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reaction_type VARCHAR(255)
);
