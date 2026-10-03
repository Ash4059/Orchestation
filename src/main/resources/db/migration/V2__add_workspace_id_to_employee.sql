ALTER TABLE employee
    ADD COLUMN workspace_id BIGINT REFERENCES workspace(id);
