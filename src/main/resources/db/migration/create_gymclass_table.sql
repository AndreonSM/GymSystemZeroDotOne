CREATE TABLE gym_class (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    schedule TIMESTAMP NOT NULL,
    maxCapacity INT NOT NULL,
    -- Other class attributes

    instructor_id BIGINT,
    FOREIGN KEY (instructor_id) REFERENCES user (id),

    -- Add any additional constraints or indexes as needed
);
