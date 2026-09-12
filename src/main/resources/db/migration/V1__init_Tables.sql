CREATE TABLE workspace (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE team (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    workspace_id BIGINT REFERENCES workspace(id)
);

CREATE TABLE employee (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255),
    role VARCHAR(50),
    date_of_birth DATE,
    date_of_joining DATE,
    team_id BIGINT REFERENCES team(id)
);

CREATE TABLE task (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    status VARCHAR(50),
    assigned_to_id BIGINT REFERENCES employee(id),
    creation_time TIMESTAMP
);

CREATE TABLE task_update (
    id BIGSERIAL PRIMARY KEY,
    employee_id BIGINT REFERENCES employee(id),
    status VARCHAR(50),
    task_id BIGINT REFERENCES task(id),
    update_time TIMESTAMP
);