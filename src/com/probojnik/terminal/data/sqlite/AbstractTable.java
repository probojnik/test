package com.probojnik.terminal.data.sqlite;

import java.util.Arrays;
import java.util.List;

/**
 * @author Stanislav Shamji
 */
public class AbstractTable {
    private final String name;
    private final List<String> columns;

    private AbstractTable(String name, List<String> columns) {
        this.name = name;
        this.columns = columns;
    }

    public AbstractTable(String name, String... columns) {
        this(name, Arrays.asList(columns));
    }

    public String generateCreateScript() {
        StringBuilder builder = new StringBuilder();
        builder.append("CREATE TABLE ").append(name).append(" ( ");
        for (String col : columns) {
            builder.append(col).append(", ");
        }
        builder.replace(builder.length() - 2, builder.length(), " )");
        return builder.toString();
    }

    public String generateDropScript() {
        return "DROP TABLE IF EXISTS " + name;
    }

    @Override
    public String toString() {
        return generateCreateScript();
    }
}
