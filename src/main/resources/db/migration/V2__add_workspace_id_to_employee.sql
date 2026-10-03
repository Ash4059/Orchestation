ALTER TABLE employee
    ADD COLUMN IF NOT EXISTS workspace_id BIGINT REFERENCES workspace(id);
