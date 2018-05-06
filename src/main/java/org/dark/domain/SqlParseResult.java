package org.dark.domain;

import lombok.Data;

import java.util.List;

/**
 * @author xiaozefeng
 * @date 2018/5/5 下午11:58
 */
@Data
public class SqlParseResult {

    private String tableName;

    private List<Column> columnList;

    public SqlParseResult(String tableName, List<Column> columnList) {
        this.tableName = tableName;
        this.columnList = columnList;
    }
}
