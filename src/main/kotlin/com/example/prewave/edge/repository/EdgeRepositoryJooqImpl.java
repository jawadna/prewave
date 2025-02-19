package com.example.prewave.edge.repository;


import com.example.prewave.edge.domain.entity.EdgeEntity;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import static com.example.prewave.edge.repository.EdgeTable.EDGE;

@Repository
@Qualifier("jooqRepository")
public class EdgeRepositoryJooqImpl implements IEdgeRepository {

    private final DSLContext dslContext;

    public EdgeRepositoryJooqImpl(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public EdgeEntity create(EdgeEntity edge) {
        Record result = dslContext.insertInto(EDGE)
                .set(EDGE.FROM_ID, edge.getFromId())
                .set(EDGE.TO_ID, edge.getToId())
                .returning(EDGE.ID, EDGE.FROM_ID, EDGE.TO_ID)
                .fetchOne();
        return new EdgeEntity(Long.valueOf(result.get(EDGE.ID)), result.get(EDGE.FROM_ID), result.get(EDGE.TO_ID));
    }

    @Override
    public boolean delete(Integer fromId, Integer toId) {
        int numberOfAffectedRows = dslContext.deleteFrom(EDGE)
                .where(EDGE.FROM_ID.eq(fromId)
                        .and(EDGE.TO_ID.eq(toId)))
                .execute();
        return numberOfAffectedRows > 0;
    }
}
