CREATE TABLE membership (
    id SERIAL PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    -- Other membership attributes

    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user (id),

    -- Add any additional constraints or indexes as needed
);
