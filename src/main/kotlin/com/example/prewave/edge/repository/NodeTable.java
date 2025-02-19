package com.example.prewave.edge.repository;

import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import static org.jooq.impl.SQLDataType.*;

public class NodeTable extends TableImpl<Record> {

    public static final NodeTable NODE = new NodeTable();

    public final TableField<Record, Integer> ID;
    public final TableField<Record, String> NAME;

    private NodeTable() {
        super(DSL.name("node"));

        // Fields must be initialized inside the constructor in JOOQ 3.19+
        this.ID = createField(DSL.name("id"), INTEGER.nullable(false), this);
        this.NAME = createField(DSL.name("name"), VARCHAR(255).nullable(false), this);
    }
}
