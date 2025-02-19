package com.example.prewave.node.repository;

import com.example.prewave.common.utils.PaginatedResponse;
import com.example.prewave.node.domain.entity.TreeEntity;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("jooqRepository")
public class NodeRepositoryJooqImpl implements INodeRepository {

    private static final String GET_ALL_NODES_MATERIAL_VIEW_SQL = """
            SELECT node_id, parent_id, name, depth
                   FROM node_edge_tree
                   WHERE root_id = ?
                   ORDER BY depth, node_id
                   LIMIT ? OFFSET ?;
            """;

    private final DSLContext dslContext;

    public NodeRepositoryJooqImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }


    public PaginatedResponse<TreeEntity> getAllNodesDirectMaterializedView(int nodeId, int page, int size) {
        List<TreeEntity> result = dslContext.resultQuery(GET_ALL_NODES_MATERIAL_VIEW_SQL, nodeId, size + 1, page * size).fetchInto(TreeEntity.class);
        return new PaginatedResponse<TreeEntity>(result, page, size, result.size() > size);
    }

    @Override
    public void refreshMaterializedView() {
        dslContext.execute("REFRESH MATERIALIZED VIEW CONCURRENTLY node_edge_tree");
    }

}
