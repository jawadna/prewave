CREATE TABLE IF NOT EXISTS node (
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS edge (
    id          SERIAL PRIMARY KEY,
    from_id     INT NOT NULL REFERENCES node(id) ON DELETE CASCADE,
    to_id       INT NOT NULL REFERENCES node(id) ON DELETE CASCADE,
    CONSTRAINT unique_edge UNIQUE (from_id, to_id),
    CONSTRAINT check_from_to_diff CHECK (from_id <> to_id)
);

CREATE INDEX idx_edge_from ON edge(from_id);
CREATE INDEX idx_edge_to ON edge(to_id);