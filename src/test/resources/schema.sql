CREATE TABLE publication (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    branch_id UUID,
    author_id UUID,
    content VARCHAR(255),
    n_likes BIGINT,
    created_at TIMESTAMP
);

CREATE TABLE image (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    url VARCHAR(255),
    alt_text VARCHAR(255),
    uploaded_at TIMESTAMP
);

CREATE TABLE publication_image (
    publication_id UUID,
    image_id UUID,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    PRIMARY KEY (publication_id, image_id)
);
