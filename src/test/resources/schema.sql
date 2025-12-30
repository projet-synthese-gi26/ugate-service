CREATE TABLE users (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    name VARCHAR(255),
    phone_number VARCHAR(50),
    email_address VARCHAR(255)
);

CREATE TABLE syndicats (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    organization_id UUID,
    creator_id UUID,
    is_approved BOOLEAN,
    name VARCHAR(255),
    description VARCHAR(255),
    domain VARCHAR(255),
    type VARCHAR(255),
    charte_url VARCHAR(255),
    logo_url VARCHAR(255),
    status_url VARCHAR(255),
    members_list_url VARCHAR(255),
    commitment_certificate_url VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    is_active BOOLEAN
);

CREATE TABLE branches (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    syndicat_id UUID,
    name VARCHAR(255),
    location VARCHAR(255),
    contact VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);


CREATE TABLE publication (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    branch_id UUID,
    author_id UUID,
    content VARCHAR(255),
    n_likes BIGINT,
    created_at TIMESTAMP
);

CREATE TABLE images (
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

CREATE TABLE comment (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    author_id UUID,
    publication_id UUID,
    parent_id UUID,
    image_id UUID,
    content VARCHAR(255),
    created_at TIMESTAMP
);