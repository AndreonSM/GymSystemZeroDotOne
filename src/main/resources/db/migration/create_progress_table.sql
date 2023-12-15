CREATE TABLE progress (
    id SERIAL PRIMARY KEY,
    weight DOUBLE PRECISION NOT NULL,
    bodyMeasurements DOUBLE PRECISION NOT NULL,
    -- Other progress attributes

    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES user (id),

    -- Add any additional constraints or indexes as needed
);
