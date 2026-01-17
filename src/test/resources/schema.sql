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


CREATE TABLE publications (
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

CREATE TABLE publication_images (
    publication_id UUID,
    image_id UUID,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    PRIMARY KEY (publication_id, image_id)
);

CREATE TABLE comments (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    author_id UUID,
    publication_id UUID,
    parent_id UUID,
    image_id UUID,
    content VARCHAR(255),
    created_at TIMESTAMP
);

CREATE TABLE reactions (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    publication_id UUID,
    user_id UUID,
    type VARCHAR(255),
    reacted_at TIMESTAMP
);

CREATE TABLE event (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    creator_id UUID,
    branch_id UUID,
    title VARCHAR(255),
    description TEXT,
    location VARCHAR(255),
    event_date DATE,
    start_time TIME,
    end_time TIME,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE event_images (
    event_id UUID,
    image_id UUID,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    PRIMARY KEY (event_id, image_id)
);

CREATE TABLE user_events (
    user_event_id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    user_id VARCHAR(255),
    event_id VARCHAR(255),
    timestamp TIMESTAMP
);

CREATE TABLE publication_vote (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    branch_id UUID,
    title VARCHAR(255),
    description VARCHAR(255),
    closing_at TIMESTAMP,
    type VARCHAR(255)
);

CREATE TABLE vote (
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    user_id UUID,
    publication_vote_id UUID,
    label VARCHAR(255)
);