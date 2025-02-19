package com.example.prewave.edge.repository;

import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class EdgeTable extends TableImpl<Record> {

    public static final EdgeTable EDGE = new EdgeTable();

    public final TableField<Record, Integer> ID;
    public final TableField<Record, Integer> FROM_ID;
    public final TableField<Record, Integer> TO_ID;

    private EdgeTable() {
        super(DSL.name("edge"));

        // Fix: Remove .identity() - let Flyway handle auto-increment
        this.ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false), this);
        this.FROM_ID = createField(DSL.name("from_id"), SQLDataType.INTEGER.nullable(false), this);
        this.TO_ID = createField(DSL.name("to_id"), SQLDataType.INTEGER.nullable(false), this);
    }
}
