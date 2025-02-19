CREATE MATERIALIZED VIEW node_edge_tree AS
WITH RECURSIVE tree AS (
  SELECT
    n.id                AS root_id,
    n.id                AS node_id,
    NULL::integer       AS parent_id,
    n.name              AS name,
    0                   AS depth
  FROM node n

  UNION ALL

  SELECT
    tree.root_id      AS root_id,
    child.id          AS node_id,
    tree.node_id      AS parent_id,
    child.name        AS name,
    tree.depth + 1    AS depth
  FROM tree
  JOIN edge e ON tree.node_id = e.from_id
  JOIN node child ON e.to_id = child.id
)
SELECT root_id, node_id, parent_id, name, depth
FROM tree;

CREATE UNIQUE INDEX idx_node_edge_tree ON node_edge_tree(root_id, depth, node_id);