CREATE TABLE IF NOT EXISTS images (
                                      id          BIGSERIAL PRIMARY KEY,
                                      name        VARCHAR(255) NOT NULL,
    file_data   BYTEA,
    thumbnail   BYTEA,
    created_at  TIMESTAMP DEFAULT NOW(),
    status      VARCHAR(255)
    );

ALTER TABLE images OWNER TO admin;
