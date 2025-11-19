CREATE TABLE reviews (
                         id             BIGSERIAL PRIMARY KEY,
                         reservation_id BIGINT NOT NULL UNIQUE,
                         reviewer_id    BIGINT NOT NULL,
                         owner_id       BIGINT NOT NULL,
                         rating         INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5),
                         comment        TEXT,
                         created_at     TIMESTAMP NOT NULL DEFAULT NOW()
);