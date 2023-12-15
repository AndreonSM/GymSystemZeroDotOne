CREATE TABLE equipment (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    lastMaintenanceDate DATE,
    -- Other equipment attributes

    -- Add any additional constraints or indexes as needed
);
